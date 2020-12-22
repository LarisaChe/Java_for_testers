package ru.stqa.pft.addressbook2.appmanager;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

/**
 * Description.
 *
 * @author lchernaya
 */
public class HelperBase {

   protected WebDriver wd;

   public HelperBase(WebDriver wd) {
      this.wd = wd;
   }

   protected void click(By locator) {
      wd.findElement(locator).click();
   }

   protected void type(By locator, String text) {
      click(locator);
      wd.findElement(locator).clear();
      try {
         wd.findElement(locator).sendKeys(text);
      }
      // catch (IllegalArgumentException e) {
      catch (Exception e) {
         System.out.println("Возникло исключение, но мы его обошли: "+e.getMessage());
      }
   }

   protected void attach(By locator, File file) {
      if (file != null) {
         wd.findElement(locator).sendKeys(file.getAbsolutePath());
      }
   }

   public boolean isElementPresent(By by) {
      try {
         wd.findElement(by);
         return true;
      } catch (NoSuchElementException e) {
         return false;
      }
   }

   public boolean isAlertPresent() {
      try {
         wd.switchTo().alert();
         return true;
      } catch (NoAlertPresentException e) {
         return false;
      }
   }
}
