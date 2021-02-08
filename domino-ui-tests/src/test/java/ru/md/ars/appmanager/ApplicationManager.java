package ru.md.ars.appmanager;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ApplicationManager {

   private final Properties properties;
   private WebDriver wd;
   private String browser;
   public WebDriverWait wdwait;
   private NavigationHelper go_to;
   private CommonEntityHelper entityData;
   private EntityHelper entityMeta;
   public String baseURL;

   public ApplicationManager(String browser) {
      this.browser = browser;
      properties = new Properties();
   }

   public void init() throws IOException {
      String target = System.getProperty("target", "local");
      properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
   }

   public void stop() {
      if (wd != null) {
         wd.quit();
      }
   }

   public String getProperty(String key) {
      return properties.getProperty(key);
   }

   public WebDriver getDriver() {
      if (wd == null) {
         if (browser.equals(BrowserType.FIREFOX)) {
            wd = new FirefoxDriver();
         } else if (browser.equals(BrowserType.CHROME)) {
            wd = new ChromeDriver();
         } else if (browser.equals(BrowserType.IE)) {
            wd = new InternetExplorerDriver();
         }
         wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
         String firstURL = properties.getProperty("web.http")
                       +properties.getProperty("web.adminLogin")+":"+properties.getProperty("web.adminPassword")
                       +"@"+ properties.getProperty("web.baseUrl");
        System.out.println("firstURL: "+firstURL);
        wd.get(firstURL);

         baseURL = properties.getProperty("web.http") + properties.getProperty("web.baseUrl");
        
      }
        wdwait = new WebDriverWait(wd, 5000);
      return wd;
   }

   public  NavigationHelper go_to() {
      if (go_to == null) {
         go_to = new NavigationHelper(this);
      }
      return go_to;
   }

   public CommonEntityHelper entityData() {
      if (entityData == null) {
         entityData = new CommonEntityHelper(this);
      }
      return entityData;
   }

   public EntityHelper entityMeta() {
      if (entityMeta == null) {
         entityMeta = new EntityHelper(this);
      }
      return entityMeta;
   }
   /*public void changeToRussianLanguage() {
      System.out.println(" ---  " + wd.getTitle());
      if (wd.getTitle().equals("ARS | Main")) {
         go_to().userProfile();
         wd.findElement(By.cssSelector(".z-combobox-input")).click();
         //         wd.wait(2);
         wd.findElements(By.cssSelector(".z-comboitem")).get(0).click();
         //         wd.wait(3);
         go_to().homePage();
         //currentLanguage = "Русский";
      }
   } */

}
