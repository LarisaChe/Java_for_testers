package ru.md.ars.appmanager;

import static java.lang.Thread.sleep;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CommonEntityHelper extends HelperBase {

  /* public CommonEntityHelper(WebDriver wd) {
      super(wd);
   }*/

   public CommonEntityHelper(ApplicationManager app) {
      super(app);
   }
   
   public void commonTest() throws InterruptedException {

      System.out.println(wd.getTitle());

      System.out.println(wd.findElement(By.cssSelector(".z-window-header")).getText());
      editRow();
      countRows();
      if (addRow()) clickButtonCancel();
      addRowInModalWindow();
      countDeletedRows();
   }

   private void countDeletedRows() {
      click(By.cssSelector(".z-icon-eye"));
      System.out.println("Deleted rows: "+wd.findElements(By.cssSelector(".z-grid-body .z-row")).size());
   }

   private void editRow()  {
      int i = wd.findElements(By.cssSelector(".z-icon-pencil")).size();
      if (i == 0) {
         System.out.println("There isn't rows");
      }
      else {
         //int k =  i==1 ? 0 : i - 2;
         int k = 0; // i-1
         if (i > 5) k = 5;

         if (i > 0) {
            wd.findElements(By.cssSelector(".z-icon-pencil")).get(k).click();
            app.wdwait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".z-icon-check")));

            click(By.cssSelector(".z-icon-check"));
         }
      }
   }

   private void addRowInModalWindow()  {
      wd.findElement(By.cssSelector(".z-icon-window-maximize")).click();
      if (!isElementPresent(By.cssSelector(".z-window-modal")))
         {click(By.cssSelector(".z-icon-window-maximize"));}
      app.wdwait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".z-window-modal")));
      click(By.cssSelector(".z-window-modal .z-icon-times"));
   }

   private boolean addRow() throws InterruptedException {
      //click(By.cssSelector(".z-icon-plus"));
      if (wd.findElements(By.cssSelector(".z-icon-plus")).size()>0) {
         System.out.println("Create new row for data");
         wd.findElement(By.cssSelector(".z-icon-plus")).click();
         sleep(100);
         //.z-icon-check   button[title='Отклонить']
         // clickButtonCancel();
         return true;
      }
      else {
         System.out.println("Button ADD have not found");
         return false;
      }

   }

   private void countRows() {
      System.out.println("Count rows: "+wd.findElements(By.cssSelector(".z-grid-body .z-row")).size());
      if (isElementPresent(By.cssSelector(".z-paging-text"))) {
         System.out.println(wd.findElement(By.cssSelector(".z-paging-text")).getText());
      }
      if (isElementPresent(By.cssSelector(".z-paging-info"))) {
         System.out.println(wd.findElement(By.cssSelector(".z-paging-info")).getText());
      }
   }

   public void fillWithSimpleData(boolean isHi) throws InterruptedException {
      addRow();
      app.wdwait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(".z-icon-times"),0));
      if (isHi) fillFieldsInRow(".z-treerow td input", 1);
      else fillFieldsInRow(".z-row td input", 2);

      click(By.cssSelector(".z-icon-check"));
   }

   private void fillFieldsInRow(String locate, int k) {
      int i = wd.findElements(By.cssSelector(locate)).size();
      System.out.println("i= "+i);
      for (int j=k;j<i; j++) {
         WebElement el = wd.findElements(By.cssSelector(locate)).get(j);
         if (!el.getAttribute("name").equals("file")) {
            //wd.findElements(By.cssSelector(locate)).get(j).sendKeys("1");
            el.sendKeys("1");
         }
         /*WebElement el = wd.findElements(By.cssSelector(locate)).get(j);
         el.click();
         el.sendKeys("1"); */
      }
   }
}
