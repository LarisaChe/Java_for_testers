package ru.md.ars.appmanager;

import static java.lang.Thread.sleep;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import ru.md.ars.model.AttributeData;
import ru.md.ars.model.EntityData;

public class EntityHelper extends HelperBase {
   public String  newKey;

   public EntityHelper(ApplicationManager app) {
      super(app);
   }

   public void create(EntityData entity) throws InterruptedException {
      addEntity();
      filloutEntityParams(entity);
      int row = 0;
      Iterator<AttributeData> enityIterator = entity.getAttributes().stream().iterator();
      filloutAttribute(enityIterator.next(), row);
  //    save();
      while (enityIterator.hasNext()) {
         row++;
         addAttribute();
         filloutAttribute(enityIterator.next(), row);

      }
      save();
   }

   private void addAttribute() {
      int i = wd.findElements(By.cssSelector(".z-row")).size();
      wd.findElement(By.cssSelector(".z-icon-plus")).click();
      app.wdwait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(".z-row"), i));
   }

   private void save() {
      wd.findElement(By.cssSelector(".z-icon-save")).click();
    /*  wd.findElements(By.cssSelector("textarea")).get(0).click();
      wd.findElements(By.cssSelector("textarea")).get(0).clear();
      app.wdwait.until(ExpectedConditions.stalenessOf(wd.findElement(By.cssSelector(".z-notification"))));
      */
   }

   private void filloutAttribute(AttributeData attribute, int row) throws InterruptedException {
      while (!wd.findElements(By.cssSelector(".z-combobox-input")).get(row * 3)
                .getAttribute("value").equals(attribute.getType())) {
         fillAttributeType(attribute, row);
      }
      WebElement el = wd.findElements(By.cssSelector(".z-grid-body")).get(1).findElements(By.cssSelector(".z-row")).get(row);
      el.findElements(By.cssSelector("input.z-textbox")).get(0).sendKeys(attribute.getKey());

      el.findElements(By.cssSelector("input.z-textbox")).get(1).sendKeys(attribute.getNameRu());
      el.findElements(By.cssSelector("input.z-textbox")).get(2).sendKeys(attribute.getNameEn());

      if (attribute.isFilter()) {
         el.findElements(By.cssSelector(".z-checkbox")).get(3).click();
      }

      if (attribute.isMultilang()) {
         el.findElements(By.cssSelector(".z-checkbox")).get(2).click();
      }
      if (attribute.isNullable()) {
         el.findElements(By.cssSelector(".z-checkbox")).get(1).click();
      }
      el.findElements(By.cssSelector(".z-spinner input")).get(0).sendKeys(String.valueOf(row));
   }

   //private void fillAttributeType( String type, int row) throws InterruptedException {
   private void fillAttributeType( AttributeData attribute, int row) throws InterruptedException {
      wd.findElements(By.cssSelector(".z-icon-caret-down")).get(row * 3).click();
      app.wdwait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".z-combobox-open")));
      List<WebElement> comboboxItems = wd.findElements(By.cssSelector(".z-combobox-popup .z-comboitem"));
      for (WebElement comboboxItem : comboboxItems) {
         //System.out.println("menuitem: "+menuItem.getAttribute("textContent"));
         String ss = comboboxItem.getAttribute("textContent").replaceAll("\u00A0", " ");
         if (ss.equals(attribute.getType())) {
            comboboxItem.click();
            break;
         }
      }
      if (!(attribute.getRef()==null)) {
         sleep(1000);
         app.wdwait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".z-window-modal")));
         String[] split = attribute.getRef().split("#");
         System.out.println(" ------------------ "+attribute.getRef());
         System.out.println("split[0]: "+split[0]);
         System.out.println("split[1]: "+split[1]);
         //wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a", id))).click();
         int i = wd.findElements(By.cssSelector(".z-listbox-body tr")).size();
         //int i = wd.findElements(By.xpath("//div[@class='z-listbox-body'][1]//tr")).size();
         System.out.println("-------0--1-------- i: "+i);
         wd.findElements(By.cssSelector(".z-window-modal input")).get(0).sendKeys(split[0] + Keys.ENTER);
         sleep(100);
         app.wdwait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector(".z-listbox-body tr"), i));
          //                 By.xpath("//div[@class='z-listbox-body'][1]//tr"), i));
         System.out.println("-------0--2-------- i: "+wd.findElements(By.cssSelector(".z-listbox-body tr")).size());
         for (int j=0;j<i;j++) {
            //if (wd.findElements(By.cssSelector(".z-listbox-body tr")).get(j).findElements(By.cssSelector("td")).get(0).getText().equals(split[0])) {
            System.out.println(wd.findElement(By.xpath(String.format("//div[@class='z-listbox-body'][1]//tr[%s]/td[1]", j+1))).getText());
            //if (wd.findElement(By.xpath(String.format("//tr[./[@class='z-listbox-body'][0]][%s]/td[0]", j))).getText().equals(split[0])) {
            if (wd.findElement(By.xpath(String.format("//div[@class='z-listbox-body'][1]//tr[%s]/td[1]", j+1))).getText().equals(split[0])) {
               //wd.findElements(By.cssSelector(".z-listbox-body tr")).get(j).click();
               //wd.findElements(By.xpath("//div[@class='z-listbox-body'][1]//tr")).get(j).click();
               wd.findElement(By.xpath(String.format("//div[@class='z-listbox-body'][1]//tr[%s]/td[1]", j+1))).click();
               System.out.println("Нашли!!!");
               break;
            }
         }
         sleep(100);
         app.wdwait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(".z-listbox-body tr"), 1));
         i = wd.findElements(By.cssSelector(".z-listbox-body tr")).size();
        // i = wd.findElements(By.xpath("//div[@class='z-listbox-body'][2]//tr")).size();
         System.out.println("--------1--1------- i: "+i);
         wd.findElements(By.cssSelector(".z-window-modal input")).get(1).sendKeys(split[1] + Keys.ENTER);
         sleep(100);
         app.wdwait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector(".z-listbox-body tr"), i));
                              // By.xpath("//div[@class='z-listbox-body'][2]//tr"), i));
         for (int j=0;j<i;j++) {
            //if (wd.findElements(By.cssSelector(".z-listbox-body tr")).get(j).findElements(By.cssSelector("td")).get(0).getText().equals(split[0])) {
           WebElement el = wd.findElements(By.cssSelector(".z-listbox-body")).get(1);
            System.out.println(el.findElements(By.cssSelector("tr.z-listitem")).get(j).findElements(By.cssSelector("td")).get(0).getText());
            // System.out.println(wd.findElement(By.xpath(String.format("//div[@class='z-listbox-body'][2]//tr[@class='z-listitem'][%s]/td[1]", j+1))).getText());
            //if (wd.findElement(By.xpath(String.format("//div[@class='z-listbox-body'][2]//tr[@class='z-listitem'][%s]/td[1]", j+1))).getText().contains(split[1])) {
            if (el.findElements(By.cssSelector("tr.z-listitem")).get(j).findElements(By.cssSelector("td")).get(0).getText().contains(split[1])) {
               //wd.findElements(By.cssSelector(".z-listbox-body tr")).get(j).click();
               //wd.findElements(By.xpath("//div[@class='z-listbox-body'][2]//tr")).get(j).click();
               //wd.findElement(By.xpath(String.format("//div[@class='z-listbox-body'][2]//tr[%s]/td[1]", j+1))).click();
               el.findElements(By.cssSelector("tr.z-listitem")).get(j).findElements(By.cssSelector("td")).get(0).click();
               System.out.println("Нашли!!!");
               break;
            }
         }
         wd.findElements(By.cssSelector(".z-window-modal .z-button")).get(0).click();
      }
   }

   private void filloutEntityParams(EntityData entity) throws InterruptedException {
      long now = System.currentTimeMillis();
      newKey = entity.getKey() + String.valueOf(now);
      wd.findElements(By.cssSelector(".z-row input.z-textbox")).get(0).sendKeys(newKey);
      if (!entity.isHi() == wd.findElements(By.cssSelector("tbody .z-checkbox")).get(0).getAttribute("className").contains("z-checkbox-on")) {
         wd.findElements(By.cssSelector("tbody .z-checkbox")).get(0).click();
      }
      WebElement el = wd.findElements(By.cssSelector(".z-row input.z-textbox")).get(1);
      el.sendKeys(entity.getNameRu()+now);

      el = wd.findElements(By.cssSelector(".z-row input.z-textbox")).get(2);
             el.sendKeys(entity.getNameEn()+now);
   }

   private void addEntity() {
      WebElement el = wd.findElements(By.cssSelector(".z-icon-file-o")).get(1);
      el.click();
      app.wdwait.until(ExpectedConditions.stalenessOf(el));
      app.wdwait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(".z-icon-angle-right"), 1));
   }

   public void contextSearch(String searchString) throws InterruptedException {
      int i = wd.findElements(By.cssSelector(".z-treechildren tr")).size();
      wd.findElement(By.cssSelector(".panel-filter input")).click();
      wd.findElement(By.cssSelector(".panel-filter input")).sendKeys(searchString);
      sleep(100);
      app.wdwait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector(".z-treechildren tr"), i));
   }

   public void openEntitiesTreeBranches() {
      while (isElementPresent(By.cssSelector(".z-tree-close"))) {
         wd.findElement(By.cssSelector(".z-tree-close")).click();
      }
   }

   public List<String> entityKeysList() {
      List<String> keysList = new ArrayList<>();
   List<WebElement> entitiesList = wd.findElements(By.cssSelector(".z-tree-body tr"));
   for (WebElement entity : entitiesList) {
      if (entity.findElements(By.cssSelector(".z-tree-open")).size()==0) {
         if (entity.findElements(By.cssSelector(".z-tree-spacer")).size() ==2) {
            //String ss = entity.findElements(By.cssSelector("td")).get(0).findElement(By.cssSelector(".z-treecell-text")).getText();
            //System.out.println("ss: "+ss);
            keysList.add(new String(entity.findElements(By.cssSelector("td")).get(0).findElement(By.cssSelector(".z-treecell-text")).getText())); // &nbsp;TEST_3851  <span class="z-treecell-text">&nbsp;TEST_3851</span>
            //keysList.add(ss);
            System.out.println("keysList: "+keysList);
         }
      }
   }
      return keysList;
   }

   public void delete(String entityKey) throws InterruptedException {
      contextSearchClear();
      contextSearch(entityKey);
      openEntitiesTreeBranches();
      findAndOpenEntityInView(entityKey);
      deleteEntityStructure();
      //contextSearchClear();
   }

   private void deleteEntityStructure() {
      click(By.cssSelector(".z-icon-remove"));
      app.wdwait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".z-messagebox-window")));
      wd.findElements(By.cssSelector(".z-messagebox-window .z-button")).get(0).click();
      app.wdwait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(".z-icon-angle-right"), 1));
   }

   private void findAndOpenEntityInView(String entityKey) {
      List<WebElement> entitiesList = wd.findElements(By.cssSelector(".z-treechildren tr")); //.z-tree-body tr
      for (WebElement entity : entitiesList) {
         //System.out.println(" -------  !"+entity.findElements(By.cssSelector("td")).get(0).findElement(By.cssSelector(".z-treecell-text")).getText()+"!");
         if ((entity.findElements(By.cssSelector(".z-tree-open")).size()==0)
             && (entity.findElements(By.cssSelector(".z-tree-spacer")).size() ==2)
             && (entity.findElements(By.cssSelector("td")).get(0).findElement(By.cssSelector(".z-treecell-text")).getText()
                       .contains(entityKey.trim().toUpperCase(Locale.ROOT)))) {
            //System.out.println("entity нашли!");
            entity.findElements(By.cssSelector("td")).get(1).click();
            app.wdwait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(".z-icon-angle-right"), 1));
            break;
         }
      }
   }

   private void contextSearchClear() throws InterruptedException {
      if (!wd.findElement(By.cssSelector(".panel-filter input")).getAttribute("value").equals("")) {
         int i = wd.findElements(By.cssSelector(".z-treechildren tr")).size();
         wd.findElement(By.cssSelector(".panel-filter input")).clear();
         sleep(100);
         app.wdwait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(".z-treechildren tr"), i));
      }
   }

   public void openEntityInDataView(String newKey) throws InterruptedException {
      contextSearchClear();
      contextSearch(newKey);
      openEntitiesTreeBranches();
      //System.out.println("!"+newKey+"!");
      findAndOpenEntityInView(newKey);
     // addEntityDataInLine();

   }

   private void addEntityDataInLine() {

   }

}
