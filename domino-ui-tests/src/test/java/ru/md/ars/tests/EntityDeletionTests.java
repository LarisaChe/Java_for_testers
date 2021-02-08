package ru.md.ars.tests;

import static ru.md.ars.tests.TestBase.app;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class EntityDeletionTests {

   @DataProvider
   public Iterator<Object[]> validEntityKeysList() throws IOException, InterruptedException {
      //List<String> entityKeys;
      //try () {
      app.go_to().pageStructure();
      app.entityMeta().contextSearch("DictjsonG");
      app.entityMeta().openEntitiesTreeBranches();
      List<String> entityKeys = app.entityMeta().entityKeysList();
      System.out.println("entityKeys.size(): "+entityKeys.size());
      System.out.println(entityKeys);
         return entityKeys.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
     // }
   }

   @Test (dataProvider = "validEntityKeysList")
   public void testEntityDeletion(String entityKey) throws InterruptedException {
      app.go_to().pageStructure();
      app.entityMeta().delete(entityKey);

   }
}
