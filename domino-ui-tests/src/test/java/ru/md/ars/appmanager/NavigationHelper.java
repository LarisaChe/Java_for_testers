package ru.md.ars.appmanager;

import static java.lang.Thread.sleep;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.google.gson.internal.bind.util.ISO8601Utils;

public class NavigationHelper extends HelperBase {

  // private final ApplicationManager app;

   /*public NavigationHelper(WebDriver wd) {
      super(wd);
   } */
   
   public NavigationHelper(ApplicationManager app) {
      super(app);
   }

  /* public NavigationHelper(ApplicationManager app) {
      this.app = app;
   }*/

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

   public void adminMenuItem(String locatorUpItem, int item, String partOfUrl) {
      if (!isElementPresent(By.cssSelector(".z-nav-open "+locatorUpItem))) {
         click(By.cssSelector(locatorUpItem)); //".z-icon-shield"
      }
      wd.findElements(By.cssSelector(".z-nav-open .z-navitem")).get(item).click();
      app.wdwait.until(ExpectedConditions.urlContains(partOfUrl));
      printInfoAboutPage();
   }

   public void changeToRussianLanguage() {
      System.out.println(" ---  " + wd.getTitle());
      if (wd.getTitle().equals("ARS | Main")) {
         app.go_to().userProfile();
         wd.findElement(By.cssSelector(".z-combobox-input")).click();
         //         wd.wait(2);
         wd.findElements(By.cssSelector(".z-comboitem")).get(0).click();
         //         wd.wait(3);
         app.go_to().homePage();
         //currentLanguage = "Русский";
         //System.out.println("dd");
      }
   }

   public void pageByUrl(String partOfUrl, String nameRu) throws InterruptedException { //, String inMenuGroup
      //if (level==1) {
         wd.get(app.baseURL+partOfUrl);
         sleep(5000);
      System.out.println("baseURL: " +app.baseURL+partOfUrl);
         app.wdwait.until(ExpectedConditions.urlContains(partOfUrl));
      printInfoAboutPage();
   }

   private void printInfoAboutPage() {   //String nameRu) {
      System.out.println(wd.getCurrentUrl());
      System.out.println(wd.getTitle());
      //waitTitleContains(nameRu);
      System.out.println("Breadcrams: "+wd.findElement(By.cssSelector(".z-window-header")).getText());
      //}
   }

   private void waitTitleContains(String nameRu) {
      String title;
      int i = 0;
      do {
          title = wd.getTitle();
          i++;
      } while (!title.contains(nameRu)&&(i<100));

   }

   public void toolsMenuItem(String locatorUpItem, int upItem, int item, String partOfUrl) throws InterruptedException {
      if (!isElementPresent(By.cssSelector(".z-nav-open "+locatorUpItem))) {
         click(By.cssSelector(locatorUpItem)); //".z-icon-shield"
      }
      WebElement menuLevel2 = wd.findElements(By.cssSelector(".z-nav-open .z-nav-content")).get(upItem);
     /* if (!isElementPresent(By.cssSelector(".z-nav-open"), menuLevel2)) {
         menuLevel2.click();
      } */
      WebElement el = wd.findElements(By.cssSelector(".z-navitem")).get(item);
      if (!isElementClickable(el)) {
         menuLevel2.click();

      }
      //wd.findElements(By.cssSelector(".z-nav-open .z-navitem')")).get(item).click();
      el.click();
      app.wdwait.until(ExpectedConditions.urlContains(partOfUrl));
      sleep(1000);
      printInfoAboutPage();

   }

   public void pageStructure() throws InterruptedException {
      pageByUrl("#entity.meta.entity_meta_list/", "entity_meta_list");
     // #entity.meta.entity_meta_list/
   }

   public void pageData() throws InterruptedException {
      pageByUrl("#entity.meta.entity_list/", "entity_list");
   }
}
