package ru.md.ars.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ru.md.ars.model.EntityData;

public class EntityCreationTests extends TestBase {

   @DataProvider
   public Iterator<Object[]> validEntitiesFromJSON() throws IOException {
      try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/entities.json")))) { //   app.fileDataForGroup()
         String json = "";
         String line = reader.readLine();
         while (line != null) {
            json += line;
            line = reader.readLine();
         }
         Gson gson = new Gson();
         List<EntityData> entities = gson.fromJson(json, new TypeToken<List<EntityData>>() {}.getType()); // это сложный способ только для списков объектов
         return entities.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
      }
   }

   @Test (dataProvider = "validEntitiesFromJSON")
   public void testEntityCreation(EntityData entity) throws InterruptedException {
      app.go_to().pageStructure();
      app.entityMeta().create(entity);
      System.out.println("new entity key: "+app.entityMeta().newKey);
      app.go_to().pageData();
      app.entityMeta().openEntityInDataView(app.entityMeta().newKey);
      app.entityData().fillWithSimpleData(entity.isHi());
      app.entityData().commonTest();
      //app.entityData().fillWithData();
   }
   @Test (enabled = false)
   public  void testFillout() throws InterruptedException {
      app.go_to().pageData();
      app.entityMeta().openEntityInDataView("DICTJSONG11612559019568");
      app.entityData().fillWithSimpleData(false);
   }
}
