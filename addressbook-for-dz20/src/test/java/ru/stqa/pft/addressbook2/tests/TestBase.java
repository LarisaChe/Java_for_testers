package ru.stqa.pft.addressbook2.tests;

import java.io.IOException;
import java.lang.reflect.Method;

import java.util.Arrays;

import java.util.Set;
import java.util.stream.Collectors;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import ru.stqa.pft.addressbook2.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook2.model.ContactData;
import ru.stqa.pft.addressbook2.model.Contacts;
import ru.stqa.pft.addressbook2.model.GroupData;
import ru.stqa.pft.addressbook2.model.Groups;
import ru.stqa.pft.addressbook2.model.Issue;

public class TestBase  {

   protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

   Logger logger = LoggerFactory.getLogger(GroupCreationTests.class);

   @BeforeSuite
   public void setUp() throws Exception {
      app.init();
   }

   @AfterSuite
   public void tearDown() throws Exception {
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

   public void verifyGroupListInUI() {
      if (Boolean.getBoolean("verifyUI")) {
         logger.info("¡ Compare with list Groups in UI ! ");
         Groups dbGroups = app.db().groups();
         Groups uiGroups = app.group().all();
         MatcherAssert.assertThat(uiGroups, CoreMatchers.equalTo(dbGroups.stream()
                                  .map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
                                  .collect(Collectors.toSet())));
      }
   }

   public void verifyContactListInUI() {
      if (Boolean.getBoolean("verifyUI")) {
         logger.info("¡ Compare with list Contacts in UI ! ");
         Contacts dbContacts = app.db().contacts();
         Contacts uiContacts = app.contact().all();
         MatcherAssert.assertThat(uiContacts, CoreMatchers.equalTo(dbContacts.stream()
                             .map((g) -> new ContactData().withId(g.getId())
                                                          .withFirstname(g.getFirstname())
                                                          .withLastname(g.getLastname())
                                                          .withAddress(g.getAddress()))
                             .collect(Collectors.toSet())));
      }
   }

   public boolean isIssueOpen(int issueId) throws IOException {
      System.out.println("Check issue id: "+issueId);
      Set<Issue> issues = app.rest().getIssue(issueId);
      Issue issue = issues.iterator().next();
      System.out.println("State_name: "+issue.getState_name());
      return  !issue.getState_name().equals("Closed"); //Closed Open
   }

   public void skipIfNotFixed(int issueId) throws IOException {
      if (isIssueOpen(issueId)) {
         throw new SkipException("Ignored because of issue " + issueId);
      }
   }

}
