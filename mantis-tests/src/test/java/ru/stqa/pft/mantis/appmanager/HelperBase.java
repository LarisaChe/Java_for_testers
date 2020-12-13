package ru.stqa.pft.mantis.appmanager;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class HelperBase {
   protected ApplicationManager app;
   protected WebDriver wd;

   //public HelperBase(WebDriver wd) {
   public HelperBase(ApplicationManager app) {
      this.app = app;
      this.wd = app.getDriver(); //wd;
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
      catch (Exception e) {
         System.out.println("В "+locator.toString() +" возникло исключение, но мы его обошли: "+e.getMessage());
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
