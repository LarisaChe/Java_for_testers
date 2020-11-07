package ru.stqa.pft.addressbook.appmanager;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

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
   private String browser;

   public ApplicationManager(String browser) {
      this.browser = browser;
   }

   public void init() {
     if (browser.equals(BrowserType.FIREFOX)) {
        wd = new FirefoxDriver();
     } else if (browser.equals(BrowserType.CHROME)) {
        wd = new ChromeDriver();
     } else if (browser.equals(BrowserType.IE)) {
        wd = new InternetExplorerDriver();
     }
      wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
