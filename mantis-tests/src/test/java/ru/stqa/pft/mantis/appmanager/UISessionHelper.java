package ru.stqa.pft.mantis.appmanager;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ru.stqa.pft.mantis.model.UserData;

public class UISessionHelper extends HelperBase {

   public UISessionHelper(ApplicationManager app) {
      super(app);
   }

   public void loginUI(String username, String password)  {
      wd.get(app.getProperty("web.baseUrl"));
      type (By.name("username"), username);
      click(By.cssSelector("input[type='submit'].btn"));
      app.wdwait.until(ExpectedConditions.urlContains("login_password_page.php")); //presenceOfElementLocated(By.name("password")));
      type (By.name("password"), password);
      click(By.cssSelector("input[type='submit'].btn"));
      app.wdwait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".menu-text")));
   }

   public void tabManageUser () {
      click(By.cssSelector("[href='/mantisbt-2.24.3/manage_user_page.php']"));
   }

   public void menuManage () {
      click(By.cssSelector(".fa-gears"));
   }

   public void openCard(int userId) {
      click(By.cssSelector(String.format("[href='manage_user_edit_page.php?user_id=%s']", userId)));
   }

   public void clickBtnResetPassword() {
      click(By.cssSelector("#manage-user-reset-form"));
      app.wdwait.until(ExpectedConditions.stalenessOf(wd.findElement(By.cssSelector(".alert"))));
   }

   /*  public void logoutUI() {
      wd.findElement(By.linkText("Logout")).click();
      wd.findElement(By.name("user")).clear();
   } */
}
