package ru.stqa.pft.mantis.appmanager;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ru.stqa.pft.mantis.model.UserData;

public class UISessionHelper extends HelperBase {

   //private WebDriver wd;
   //WebDriverWait wait;

   public UISessionHelper(ApplicationManager app) {
     // this.wd = wd;
      super(app);
   }

   public void loginUI(String username, String password)  {
      wd.get(app.getProperty("web.baseUrl"));
      type (By.name("username"), username);
      click(By.cssSelector("input[type='submit'].btn"));
      app.wdwait.until(ExpectedConditions.urlContains("login_password_page.php")); //presenceOfElementLocated(By.name("password")));
      type (By.name("password"), password);
      click(By.cssSelector("input[type='submit'].btn"));
      //click(By.xpath("//input[@type='submit']"));
      app.wdwait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".menu-text")));
   }

   public WebElement menuItem (String menuName) {
      return findElement(By.cssSelector(".menu-text"), menuName, "menu");
   }

   public WebElement tabItem (String tabName) {
      return findElement(By.cssSelector(".row ul.nav li a"), tabName, "tabs");
   }

   private WebElement findElement (By locator, String itemName, String itemType) {
      List<WebElement> tabItems = wd.findElements(locator);
      for (WebElement e : tabItems) {
         if (e.getText().trim().equals(itemName)) {
            return e;
         }
      }
      System.out.println(String.format("The '%s' item don't find in %s!", itemName, itemType));
      return null;
   }

   public void openCard(int userId) {
      click(By.cssSelector(String.format("[href='manage_user_edit_page.php?user_id=%s']")));
   }

   public void clickButton(String buttonName) {
      click(By.cssSelector(String.format("span input[value = '%s']",buttonName)));
   }

   /*  public void logoutUI() {
      wd.findElement(By.linkText("Logout")).click();
      wd.findElement(By.name("user")).clear();
   } */
}
