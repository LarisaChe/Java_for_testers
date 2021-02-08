package ru.md.ars.tests;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import ru.md.ars.appmanager.ApplicationManager;

@Listeners (MyTestListener.class)
public class TestBase {

   protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

   Logger logger = LoggerFactory.getLogger(EmbeddedEntitiesCommonTests.class);

   @BeforeSuite
   public void setUp(ITestContext context) throws Exception {
      app.init();
      context.setAttribute("app", app);
      app.changeToRussianLanguage();
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

   /*public void verifyGroupListInUI() {
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
   } */

}
