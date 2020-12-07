package ru.stqa.pft.addressbook.tests;

import static org.hamcrest.MatcherAssert.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

/**
 * Description.
 *
 * @author lchernaya
 */
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
         Groups dbGroups = app.db().groups();
         Groups uiGroups = app.group().all();
         MatcherAssert.assertThat(uiGroups, CoreMatchers.equalTo(dbGroups.stream()
                                  .map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
                                  .collect(Collectors.toSet())));
      }
   }
}
