package ru.md.ars.appmanager;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class NavigationHelper extends HelperBase{

   public NavigationHelper(WebDriver wd) {
      super(wd);
   }

   public void homePage() {
      if ((wd.getTitle().equals("АРС|Главная")) || (wd.getTitle().equals("ARS|Main"))) {
         return;
      }
      click (By.cssSelector(".image-logo.z-image"));
   }

   public void userProfile() {
      click (By.cssSelector(".z-icon-user"));
      System.out.println(wd.getTitle());
//      Assert.assertEquals(wd.getTitle(), "АРС|Карточка пользователя");
   }

   public void pageUsers() {
      adminMenuItem(1);
   }

   private void adminMenuItem(int item) {
      if (!isElementPresent(By.cssSelector(".z-nav-open .z-icon-shield"))) {
         System.out.println("-Open menu admin!-");
         click(By.cssSelector(".z-icon-shield"));
      }
      System.out.println("-click role!-");
      wd.findElements(By.cssSelector(".z-nav-open .z-navitem")).get(item).click();
      System.out.println("-Open-");
   }

}
