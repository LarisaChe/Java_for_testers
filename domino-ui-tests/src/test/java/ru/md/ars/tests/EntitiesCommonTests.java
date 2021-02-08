package ru.md.ars.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ru.md.ars.model.MenuData;

public class EntitiesCommonTests extends TestBase{

 /*  @BeforeTest()
   public void changeToRusLang() {
      app.go_to().changeToRussianLanguage();
   }
web.http = https://
web.baseUrl = ars-test.masterdm.ru/

web.http = http://
web.baseUrl = 192.128.0.20:8080/

http://192.128.0.20:8080/#entity.edit_users/USER
   */

   @DataProvider
   public Iterator<Object[]> validMenuAdminItemFromCSV() throws IOException {
      List<Object[]> list = new ArrayList<Object[]>();
      try(BufferedReader reader = new BufferedReader(new FileReader(new File(app.getProperty("data.fileForAdminMenu"))))) { // "src/test/resources/groups2.csv"
         String line = reader.readLine();   //fileDataForGroup()
         while (line != null) {
            String[] split = line.split(";");
            list.add(new Object[] {new MenuData().withId(Integer.parseInt(split[0]))
                                                 .withNameRU(split[1])
                                                 .withNameEN(split[2])
                                                 .withUrl(split[3])
                                                 .withLevel(Integer.parseInt(split[4]))
                                                 .withInMenuGroup(split[5])
                                                 .withOrderInMenuGroup(Integer.parseInt(split[6]))});
            line = reader.readLine();
         }
         return list.iterator();
      }
   }

   @Test (dataProvider = "validMenuAdminItemFromCSV")
   public void commonTestEmbeddedEntity(MenuData menuItem) throws InterruptedException {
      System.out.println("Admin menu: "+menuItem.getNameRU()+" "+menuItem.getNameEN()+" "+menuItem.getUrl()+" order in menu: "+menuItem.getOrderInMenuGroup());
      app.go_to().adminMenuItem(menuItem.getInMenuGroup(), menuItem.getOrderInMenuGroup(), menuItem.getUrl());
      app.entityData().commonTest();
   }

}
