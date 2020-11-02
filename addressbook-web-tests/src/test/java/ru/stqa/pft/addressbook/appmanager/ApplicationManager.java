package ru.stqa.pft.addressbook.appmanager;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Description.
 *
 * @author lchernaya
 */
public class ApplicationManager  {

   private ContactHelper contactHelper;
   private SessionHelper sessionHelper;
   private NavigationHelper navigationHelper;
   private GroupHelper groupHelper;
   private WebDriver wd;

   public void init() {

      wd = new FirefoxDriver();
      wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
      wd.get("http://localhost/addressbook/index.php");
      groupHelper = new GroupHelper(wd);
      contactHelper = new ContactHelper(wd);
      navigationHelper = new NavigationHelper(wd);
      sessionHelper = new SessionHelper(wd);
      sessionHelper.login("admin", "secret");
   }

   public void stop() {
      wd.quit();
   }

   public GroupHelper getGroupHelper() {
      return groupHelper;
   }

   public NavigationHelper getNavigationHelper() {
      return navigationHelper;
   }

   public ContactHelper getContactHelper() { return contactHelper; }
}
