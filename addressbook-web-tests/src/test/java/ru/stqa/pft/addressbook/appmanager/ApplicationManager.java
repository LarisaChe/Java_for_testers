package ru.stqa.pft.addressbook.appmanager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
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

   private final Properties properties;
   private ContactHelper contactHelper;
   private SessionHelper sessionHelper;
   private NavigationHelper navigationHelper;
   private GroupHelper groupHelper;
   private WebDriver wd;
   private String browser;
   private DbHelper dbHelper;

   public ApplicationManager(String browser) {
      this.browser = browser;
      properties = new Properties();
         }

   public void init() throws IOException {
      String target = System.getProperty("target", "local");
      properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
      dbHelper = new DbHelper();

      if (browser.equals(BrowserType.FIREFOX)) {
        wd = new FirefoxDriver();
     } else if (browser.equals(BrowserType.CHROME)) {
        wd = new ChromeDriver();
     } else if (browser.equals(BrowserType.IE)) {
        wd = new InternetExplorerDriver();
     }
      wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
      //wd.get("http://localhost/addressbook/index.php");
      wd.get(properties.getProperty("web.baseUrl"));
      groupHelper = new GroupHelper(wd);
      contactHelper = new ContactHelper(wd);
      navigationHelper = new NavigationHelper(wd);
      sessionHelper = new SessionHelper(wd);
      //sessionHelper.login("admin", "secret");
      sessionHelper.login(properties.getProperty("web.adminLogin"),properties.getProperty("web.adminPassword"));

   }

   public String formatDataForGroup() {
      return properties.getProperty("data.formatForGroup");
   }

   public  String fileDataForGroup() {
      return "src/test/resources/"+properties.getProperty("data.fileForGroup");
   }

   public String formatDataForContact() {
      return properties.getProperty("data.formatForContact");
   }

   public  String fileDataForContact() {
      return "src/test/resources/"+properties.getProperty("data.fileForContact");
   }

   public int groupsCount() {return Integer.parseInt(properties.getProperty("groups.count"));}

   public void stop() {
      wd.quit();
   }

   public GroupHelper group() {
      return groupHelper;
   }

   public NavigationHelper goTo() {
      return navigationHelper;
   }

   public ContactHelper contact() { return contactHelper; }

   public DbHelper db() { return dbHelper;}
}
