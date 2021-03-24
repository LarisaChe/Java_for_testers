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

   public void create(EntityData entity, boolean isKeySuffix, boolean isCreationAttributeInModalWindow) throws InterruptedException {
      addEntity();
      filloutEntityParams(entity, isKeySuffix);
      int row = 0;
      Iterator<AttributeData> enityIterator = entity.getAttributes().stream().iterator();
      filloutAttributeInRow(enityIterator.next(), row);

      while (enityIterator.hasNext()) {
         row++;
         if (isCreationAttributeInModalWindow) {
            addAttributeInModalWindows();
            filloutAttributeInModalWindows(enityIterator.next(), row);
         }
         else {
            addAttribute();
            filloutAttributeInRow(enityIterator.next(), row);
         }
      }
      sleep(500);
      save();
   }

   private void filloutAttributeInModalWindows(AttributeData attribute, int row) throws InterruptedException {
      while (wd.findElements(By.cssSelector(".z-window-modal .z-combobox-input")).get(0)
               .getAttribute("value").isEmpty()) {
         fillAttributeType(attribute, 0, ".z-window-modal ");
      }
      //filloutParamsOfAttibuteInRow(".z-window-modal", attribute, row, 0);
      filloutParamsOfAttibuteInModalWindows(attribute, row);
      sleep(500);
      saveAndCloseModalWindows();
   }

   private void saveAndCloseModalWindows() {
      wd.findElements(By.cssSelector(".z-window-modal .z-button")).get(1).click();
      app.wdwait.until(ExpectedConditions.stalenessOf(wd.findElement(By.cssSelector(".z-window-modal"))));
      System.out.println("закрыли Модальное окно");
   }

   private void addAttributeInModalWindows() {
      wd.findElement(By.cssSelector(".z-icon-window-maximize")).click();
      app.wdwait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".z-window-modal")));
   }

   private void addAttribute() {
      int i = wd.findElements(By.cssSelector(".z-row")).size();
      wd.findElement(By.cssSelector(".z-icon-plus")).click();
      app.wdwait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(".z-row"), i));
   }

   private void save() {
      wd.findElement(By.cssSelector(".z-icon-save")).click();
      app.wdwait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".z-notification")));
      //wd.findElement(By.cssSelector(".z-notification")).clear();
   }

   private void filloutAttributeInRow(AttributeData attribute, int row) throws InterruptedException {

      while (wd.findElements(By.cssSelector(".z-combobox-input")).get(row * 3)
               .getAttribute("value").isEmpty()) {
         fillAttributeType(attribute, row, "");
      }

      //filloutParamsOfAttibuteInRow(".table-attributes.z-row", attribute, row, row);
      filloutParamsOfAttibuteInRow(attribute, row);
   }

   private void filloutParamsOfAttibuteInRow( AttributeData attribute, int row) {
      WebElement el = wd.findElements(By.cssSelector(".table-attributes.z-row")).get(row);
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

   private void filloutParamsOfAttibuteInModalWindows(AttributeData attribute, int row) {

      wd.findElements(By.cssSelector(".z-window-modal input.z-textbox")).get(0).sendKeys(attribute.getKey());

      wd.findElements(By.cssSelector(".z-window-modal input.z-textbox")).get(1).sendKeys(attribute.getNameRu());
      wd.findElements(By.cssSelector(".z-window-modal input.z-textbox")).get(2).sendKeys(attribute.getNameEn());

      if (attribute.isFilter()) {
         wd.findElements(By.cssSelector(".z-window-modal .z-checkbox")).get(2).click();
      }

      if (attribute.isMultilang()) {
         wd.findElements(By.cssSelector(".z-window-modal .z-checkbox")).get(1).click();
      }
      if (attribute.isNullable()) {
         wd.findElements(By.cssSelector(".z-window-modal .z-checkbox")).get(0).click();
      }
      wd.findElements(By.cssSelector(".z-window-modal .z-spinner input")).get(0).sendKeys(String.valueOf(row));
   }

   private void fillAttributeType( AttributeData attribute, int row, String local) throws InterruptedException {
      int winModCount = wd.findElements(By.cssSelector(".z-window-modal")).size();
      wd.findElements(By.cssSelector(local+".z-icon-caret-down")).get(row * 3).click();
      app.wdwait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(local+".z-combobox-open")));
      if (app.typesList.size()==0) {
         //app.wdwait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".z-combobox-open")));
         List<WebElement> comboboxItems = wd.findElements(By.cssSelector(local+".z-combobox-popup .z-comboitem"));
         for (WebElement comboboxItem : comboboxItems) {
            System.out.println("comboboxItem: "+comboboxItem.getAttribute("textContent"));
            String ss = comboboxItem.getAttribute("textContent").replaceAll("\u00A0", " ");
            app.typesList.add(ss.toUpperCase());
         }
         System.out.println("app.typesList.size(): "+app.typesList.size());
         System.out.println(app.typesList);
      }
      System.out.println(attribute.getType()+"  --  "+attribute.getType().toUpperCase());
      for (int i=0;i<app.typesList.size();i++) {
         if (app.typesList.get(i).equals(attribute.getType().toUpperCase())) {
            System.out.println("Нашли!!!");
            wd.findElements(By.cssSelector(".z-combobox-popup .z-comboitem")).get(i).click();
            break;
         }
      }
      if (!(attribute.getRef()==null)) {
         sleep(1000);
         //app.wdwait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".z-window-modal")));
         app.wdwait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(".z-window-modal"), winModCount+1));
         String[] split = attribute.getRef().split("#");
         System.out.println(" ------------------ "+attribute.getRef());
         System.out.println("split[0]: "+split[0]);
         System.out.println("split[1]: "+split[1]);

         int i = wd.findElements(By.cssSelector(".z-listbox-body tr")).size();

         System.out.println("-------0--1-------- i: "+i);
         //wd.findElements(By.cssSelector(".z-window-modal input")).get(0).sendKeys(split[0] + Keys.ENTER);
         wd.findElements(By.cssSelector(".z-window-modal")).get(winModCount-1).findElements(By.cssSelector("input")).get(0).sendKeys(split[0] + Keys.ENTER);
         sleep(100);
         app.wdwait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector(".z-listbox-body tr"), i));

         System.out.println("-------0--2-------- i: "+wd.findElements(By.cssSelector(".z-listbox-body tr")).size());
         for (int j=0;j<i;j++) {

            System.out.println(wd.findElement(By.xpath(String.format("//div[@class='z-listbox-body'][1]//tr[%s]/td[1]", j+1))).getText());

            if (wd.findElement(By.xpath(String.format("//div[@class='z-listbox-body'][1]//tr[%s]/td[1]", j+1))).getText().equals(split[0])) {

               wd.findElement(By.xpath(String.format("//div[@class='z-listbox-body'][1]//tr[%s]/td[1]", j+1))).click();
               System.out.println("Нашли!!!");
               break;
            }
         }
         sleep(100);
         app.wdwait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(".z-listbox-body tr"), 1));
         i = wd.findElements(By.cssSelector(".z-listbox-body tr")).size();

         System.out.println("--------1--1------- i: "+i);
         wd.findElements(By.cssSelector(".z-window-modal input")).get(1).sendKeys(split[1] + Keys.ENTER);
         sleep(100);
         app.wdwait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector(".z-listbox-body tr"), i));

         for (int j=0;j<i;j++) {
           WebElement el = wd.findElements(By.cssSelector(".z-listbox-body")).get(1);
            System.out.println(el.findElements(By.cssSelector("tr.z-listitem")).get(j).findElements(By.cssSelector("td")).get(0).getText());
              if (el.findElements(By.cssSelector("tr.z-listitem")).get(j).findElements(By.cssSelector("td")).get(0).getText().contains(split[1])) {
                 el.findElements(By.cssSelector("tr.z-listitem")).get(j).findElements(By.cssSelector("td")).get(0).click();
               System.out.println("Нашли!!!");
               break;
            }
         }
         wd.findElements(By.cssSelector(".z-window-modal .z-button")).get(0).click();
         app.wdwait.until(ExpectedConditions.stalenessOf(wd.findElement(By.cssSelector(".z-window-modal"))));
         sleep(1000);
      }
   }

   private void filloutEntityParams(EntityData entity, boolean isKeySuffix) throws InterruptedException {
      String suffix = "";
      if (isKeySuffix) {
         //long now = System.currentTimeMillis();
         suffix = String.valueOf(System.currentTimeMillis());
      }
      newKey = entity.getKey() + suffix; // String.valueOf(now);
      wd.findElements(By.cssSelector(".z-row input.z-textbox")).get(0).sendKeys(newKey);
      if (!entity.isHi() == wd.findElements(By.cssSelector("tbody .z-checkbox")).get(0).getAttribute("className").contains("z-checkbox-on")) {
         wd.findElements(By.cssSelector("tbody .z-checkbox")).get(0).click();
      }
      WebElement el = wd.findElements(By.cssSelector(".z-row input.z-textbox")).get(1);
      el.sendKeys(entity.getNameRu()+suffix);

      el = wd.findElements(By.cssSelector(".z-row input.z-textbox")).get(2);
             el.sendKeys(entity.getNameEn()+suffix);
   }

   private void addEntity() {
      WebElement el = wd.findElements(By.cssSelector(".z-icon-file-o")).get(1);
      el.click();
      app.wdwait.until(ExpectedConditions.stalenessOf(el));
      app.wdwait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(".z-icon-angle-right"), 1));
   }

   public void contextSearch(String searchString) throws InterruptedException {
     // int i = wd.findElements(By.cssSelector(".z-treechildren tr")).size();
      sleep(200);
      if (isElementPresent(By.cssSelector(".panel-filter input"))&&(wd.findElement(By.cssSelector(".panel-filter input")).isEnabled())) {
         sleep(200);
      }
      wd.findElement(By.cssSelector(".panel-filter input")).click();
      wd.findElement(By.cssSelector(".panel-filter input")).sendKeys(searchString+Keys.ENTER);
      sleep(100);
      app.wdwait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".button-clear")));
     // app.wdwait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector(".z-treechildren tr"), i));
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
      checkAndClearHierarchyFlag();
      deleteEntityStructure();
      //contextSearchClear();
   }

   private void checkAndClearHierarchyFlag() {
     // WebElement el = wd.findElements(By.cssSelector(".z-checkbox")).get(0);
      //if (isElementPresent(By.cssSelector(".z-checkbox-on"), el)) {
         wd.findElements(By.cssSelector(".z-checkbox-on")).get(0).click();
         save();
      //}
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
      if (wd.findElement(By.cssSelector(".button-clear")).isDisplayed()) {
         wd.findElement(By.cssSelector(".button-clear")).click();
         sleep(100);
      }
      wd.findElements(By.cssSelector(".z-tree-close")).get(0).click();
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
