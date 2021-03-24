package ru.md.ars.tests;

import static ru.md.ars.tests.TestBase.app;

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

public class OpenAllPagesTests {

   @DataProvider
   public Iterator<Object[]> validMenuItemsFromCSV() throws IOException {
      List<Object[]> list = new ArrayList<Object[]>();
      try(BufferedReader reader = new BufferedReader(new FileReader(new File(app.getProperty("data.fileMenuList"))))) { // "src/test/resources/groups2.csv"
         String line = reader.readLine();   //fileDataForGroup()
         while (line != null) {
            String[] split = line.split(";");
            list.add(new Object[] {new MenuData().withId(Integer.parseInt(split[0]))
                                                 .withNameRU(split[1])
                                                 .withNameEN(split[2])
                                                 .withUrl(split[3])
                                                 .withLevel(Integer.parseInt(split[4]))
                                                 .withInMenuGroup(split[5])
                                                 .withOrderInMenuGroup(Integer.parseInt(split[6]))
                                                 .withTestable(Boolean.valueOf(split[7]))});
            line = reader.readLine();
         }
         return list.iterator();
      }
   }

   @DataProvider
   public Iterator<Object[]> validMenuItemsFromJSON() throws IOException {
      try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/menuItems.json")))) { //   app.fileDataForGroup()
         String json = "";
         String line = reader.readLine();
         while (line != null) {
            json += line;
            line = reader.readLine();
         }
         Gson gson = new Gson();
         List<MenuData> menuItems = gson.fromJson(json, new TypeToken<List<MenuData>>() {}.getType()); // это сложный способ только для списков объектов
         return menuItems.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
      }
   }

   @Test  (dataProvider = "validMenuItemsFromJSON")  //
   public void testOpenAllEmbeddedPagesByMenu(MenuData menuItem) throws InterruptedException {
      System.out.println("Opening by menu: "+menuItem.getNameRU()+" "+menuItem.getNameEN()+" "+menuItem.getUrl()+" order in menu: "+menuItem.getOrderInMenuGroup()+" "+menuItem.isTestable());
      if (menuItem.isTestable()) {
            if (menuItem.getInMenuGroup().equals(".z-icon-shield")) {
              app.go_to().adminMenuItem(menuItem.getInMenuGroup(), menuItem.getOrderInMenuGroup(), menuItem.getUrl());
            }
            if (menuItem.getInMenuGroup().equals(".z-icon-gears")) {
                  app.go_to().toolsMenuItem(menuItem.getInMenuGroup(), menuItem.getLevel(),  menuItem.getOrderInMenuGroup(), menuItem.getUrl());
                }
      }
      else System.out.println("Pass!");
   }

   @Test (dataProvider = "validMenuItemsFromJSON")
   public void testOpenAllEmbeddedPagesByURL(MenuData menuItem) throws InterruptedException {
      if ((menuItem.getLevel()>0) && menuItem.isTestable()) {
         System.out.println("Opening by url: "+menuItem.getNameRU()+" "+menuItem.getNameEN()+" "+menuItem.getUrl());
         app.go_to().pageByUrl(menuItem.getUrl(), menuItem.getNameRU());  //, menuItem.getInMenuGroup()
      }
   }
}
