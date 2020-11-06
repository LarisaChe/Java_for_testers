package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Description.
 *
 * @author lchernaya
 */
public class TestBase  {

   protected final ApplicationManager app = new ApplicationManager(BrowserType.FIREFOX);//(BrowserType.CHROME); //BrowserType.FIREFOX); //BrowserType.IE

   @BeforeMethod(alwaysRun = true)
   public void setUp() throws Exception {
      app.init();
   }

   @AfterMethod(alwaysRun = true)
   public void tearDown() throws Exception {
      app.stop();
   }

}
