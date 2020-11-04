package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Description.
 *
 * @author lchernaya
 */
public class NavigationHelper extends HelperBase{

   //private WebDriver wd;

   public NavigationHelper(WebDriver wd) {
      super(wd);
   }

   public void gotoGroupPage() {
     //wd.findElement(By.linkText("groups")).click();
      click(By.linkText("groups"));
   }
   public void gotoHomePage() {
      //click (By.linkText("home page"));
      click (By.linkText("home"));
   }
}
