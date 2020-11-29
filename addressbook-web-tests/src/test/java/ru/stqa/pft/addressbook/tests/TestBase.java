package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Description.
 *
 * @author lchernaya
 */
public class TestBase  {

   protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

   @BeforeSuite
   public void setUp() throws Exception {
      app.init();
   }

   @AfterSuite
   public void tearDown() throws Exception {
      app.stop();
   }

}
