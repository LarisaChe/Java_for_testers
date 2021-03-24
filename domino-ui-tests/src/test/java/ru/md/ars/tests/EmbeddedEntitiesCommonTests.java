package ru.md.ars.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ru.md.ars.model.EntityData;
import ru.md.ars.model.MenuData;

public class EmbeddedEntitiesCommonTests extends TestBase{

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
 public Iterator<Object[]> validMenuItemsFromJSON() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/menuAdminItems.json")))) { //   app.fileDataForGroup()
       String json = "";
       String line = reader.readLine();
       while (line != null) {
          json += line;
          line = reader.readLine();
       }
       Gson gson = new Gson();
       //List<EntityData> entities = gson.fromJson(json, new TypeToken<List<EntityData>>() {}.getType()); // это сложный способ только для списков объектов
       List<MenuData> menuItems = gson.fromJson(json, new TypeToken<List<MenuData>>() {}.getType()); // это сложный способ только для списков объектов
       return menuItems.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }
 }

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

   @Test (dataProvider = "validMenuItemsFromJSON")
   public void commonTestEmbeddedEntity(MenuData menuItem) throws InterruptedException {
      System.out.println("Admin menu: "+menuItem.getNameRU()+" "+menuItem.getNameEN()+" "+menuItem.getUrl()+" order in menu: "+menuItem.getOrderInMenuGroup());
      app.go_to().adminMenuItem(menuItem.getInMenuGroup(), menuItem.getOrderInMenuGroup(), menuItem.getUrl());
      app.entityData().commonTest();
   }

}
