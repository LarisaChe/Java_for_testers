package ru.md.ars.tests;

import java.io.File;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import javax.xml.rpc.ServiceException;

import org.openqa.selenium.remote.BrowserType;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import ru.md.ars.appmanager.ApplicationManager;

import java.lang.reflect.Method;


//@Listeners (MyTestListener.class)
public class TestBase {
   protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX)); // BrowserType.CHROME));
 
   Logger logger = LoggerFactory.getLogger(EntitiesCommonTests.class);

   @BeforeSuite
   public void setUp() throws Exception {
      app.init();
      app.go_to().changeToRussianLanguage();
       //changeToRussianLanguage();
      //app.ftp().upload(new File("src/test/resources/config_inc.php"), "config/config_inc.php", "config/config_inc.php.bak");
   }

   @AfterSuite
   public void tearDown() throws Exception {
      //app.ftp().restore("config/config_inc.php.bak", "config/config_inc.php");
      app.stop();
   }

   @BeforeMethod (alwaysRun = true)
   public void logTestStart(Method m, Object[] p) {
      logger.info("Start test " + m.getName() + " with parameters " + Arrays.asList(p));
   }

   @AfterMethod (alwaysRun = true)
   public void logTestStop(Method m) {
      logger.info("Stop test " + m.getName());
   }
   
 /*
   public String findConfirmationLink(List<MailMessage> mailMessages, String email) {
      MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
      VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
      return regex.getText(mailMessage.text);
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
   } */

}
