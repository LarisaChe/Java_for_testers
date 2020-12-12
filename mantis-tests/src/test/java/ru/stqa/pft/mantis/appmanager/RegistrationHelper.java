package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;

/**
 * Description.
 *
 * @author lchernaya
 */
public class RegistrationHelper {

   private  ApplicationManager app;
   private WebDriver wd;

   public RegistrationHelper(ApplicationManager app) {
      this.app = app;
      wd = app.getDrive();
   }

   public void start(String username, String email) {
      wd.get((app.getProperty("web.baseUrl") + "/signup_page.php"));
   }
}
