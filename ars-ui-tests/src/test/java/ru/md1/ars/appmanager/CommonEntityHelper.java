package ru.md.ars.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CommonEntityHelper extends HelperBase {

   public CommonEntityHelper(WebDriver wd) {
      super(wd);
   }

   public void commonTest()  {
      // wait.until(titleIs("АРС | Роли"));
      System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!");
      System.out.println(wd.getTitle());
      System.out.println();
      System.out.println(wd.findElement(By.cssSelector(".z-window-header")).getText());
      countRows();
      addRow();
      addRowInModalWindow();
      editRow();
      countDeletedRows();
   }

   private void countDeletedRows() {
      click(By.cssSelector(".z-icon-eye"));
      System.out.println("Удаленные: "+wd.findElements(By.cssSelector(".z-grid-body .z-row")).size());
   }

   private void editRow() {
      if (wd.findElements(By.cssSelector(".z-icon-pencil")).size() > 0) {
         click(By.cssSelector(".z-icon-pencil"));
         clickButtonCancel();
      }
   }

   private void addRowInModalWindow() {
      click(By.cssSelector(".z-icon-window-maximize"));
      click(By.cssSelector(".z-window-modal .z-icon-times"));
   }

   private void addRow() {
      click(By.cssSelector(".z-icon-plus"));
      //.z-icon-check   button[title='Отклонить']
      clickButtonCancel();
   }

   private void countRows() {
      System.out.println("Записи: "+wd.findElements(By.cssSelector(".z-grid-body .z-row")).size());
      System.out.println(wd.findElement(By.cssSelector(".z-paging-text")).getText());
      if (isElementPresent(By.cssSelector(".z-paging-info"))) {
         System.out.println(wd.findElement(By.cssSelector(".z-paging-info")).getText());
      }
   }

}
