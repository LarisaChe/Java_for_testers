package ru.md.ars.tests;

import org.testng.annotations.Test;


public class EmbeddedEntitiesCommonTests extends TestBase {

   @Test
   public void testUsers() {
      app.goTo().pageUsers();
      app.commonEntity().commonTest();
      //app.entity().commonTest();
     // app.goTo().commonTest();
      //app.embeddedEntity().commonTest();
   }
   // AdministrationMenuTests
}
