package ru.stqa.pft.mantis.tests;

import java.io.File;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.List;

import javax.xml.rpc.ServiceException;

import org.openqa.selenium.remote.BrowserType;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;
import ru.stqa.pft.mantis.model.MailMessage;

public class TestBase {
   protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

   @BeforeSuite
   public void setUp() throws Exception {
      app.init();
      app.ftp().upload(new File("src/test/resources/config_inc.php"), "config/config_inc.php", "config/config_inc.php.bak");
   }

   public String findConfirmationLink(List<MailMessage> mailMessages, String email) {
      MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
      VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
      return regex.getText(mailMessage.text);
   }

   @AfterSuite
   public void tearDown() throws Exception {
      app.ftp().restore("config/config_inc.php.bak", "config/config_inc.php");
      app.stop();
   }

   public boolean isIssueOpen(int issueId) throws MalformedURLException, ServiceException, RemoteException {
      System.out.println("Check issue id="+issueId);
      MantisConnectPortType mc = app.soap().getMantisConnect();
      IssueData issueData = mc.mc_issue_get(app.getProperty("soap.adminLogin"),
                                            app.getProperty("soap.adminPassword"),
                                            BigInteger.valueOf(issueId));
      System.out.println(issueData.getResolution().getName());
      //System.out.println(issueData.getResolution().getName().equals("open"));
      return  issueData.getResolution().getName().equals("open"); //fixed open
   }

   public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
      if (isIssueOpen(issueId)) {
         throw new SkipException("Ignored because of issue " + issueId);
      }
   }

}
