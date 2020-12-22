package ru.stqa.pft.addressbook2.tests;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.testng.annotations.*;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ru.stqa.pft.addressbook2.model.ContactData;
import ru.stqa.pft.addressbook2.model.Contacts;
import ru.stqa.pft.addressbook2.model.GroupData;
import ru.stqa.pft.addressbook2.model.Groups;

public class ContactCreationTests extends TestBase {

   String groupName = "Test_Cr";

   @BeforeMethod
   public void ensurePreconditions() {
      app.goTo().gotoHomePage();
      if (app.db().groups().size() == 0) {
         app.goTo().groupPage();
         app.group().create(new GroupData().withName(groupName).withHeader("TestHeader Cr").withFooter("TestFooter Cr"));
         app.goTo().gotoHomePage();
      } else {
         //groupName = app.contact().groupNameFirstInList();
         groupName = app.db().groups().iterator().next().getName();
      }
   }

   @DataProvider
   public Iterator<Object[]> validContacts() throws IOException {
      if (app.formatDataForContact().equals("json"))   return validContactsFromJSON();
      else System.out.println("Неправильно указан формат данных для групп");
      return null;
   }

   @DataProvider
   public Iterator<Object[]> validContactsFromJSON() throws IOException {
      try (BufferedReader reader = new BufferedReader(new FileReader(new File(app.fileDataForContact())))) { //"src/test/resource/contacts.json"
         String json = "";
         String line = reader.readLine();
         while (line != null) {
            json += line;
            line = reader.readLine();
         }
         Gson gson = new Gson();
         List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {}.getType()); // это сложный способ только для списков объектов
         return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
      }
   }

   @Test (dataProvider = "validContacts")
   public void testContactCreationWithDataProvider(ContactData contact) throws Exception {
      app.goTo().gotoHomePage();
      //Contacts before = app.contact().all();
      Contacts before = app.db().contacts();
      app.contact().create(contact, true);

      app.goTo().gotoHomePage();

      Contacts after = app.db().contacts();
      System.out.println("ContactCreation");
      System.out.println("after.size(): "+after.size());
      System.out.println("before.size(): "+before.size());
      assertEquals(after.size(), before.size() + 1);
      // assertThat(after.size(), equalTo(before.size() + 1));
      assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
      verifyContactListInUI();
   }

   @Test //(enabled = false)
   public void testContactCreation() throws Exception {
      app.goTo().gotoHomePage();
      Groups groups = app.db().groups();
      Contacts before = app.db().contacts();  //app.contact().all();

      File photo = new File("src/test/resources/fluke.png");
      ContactData contact = new ContactData()
           .withFirstname("Антоний")
           .withMiddlename("Васильевич")
           .withLastname("Погорелов")
           .withTitle("Domino")
           .withNickname("Ant")
           .withCompany("LTD")
           .withAddress("Москва, ул.Нагорная 7")
           .withHomePhone("8495555555555")
           .withMobilePhone("+7910555555555")
           .withWorkPhone("84955554455")
           .withEmail("test@mail.ru")
           .withBday("7")
           .withBmonth("November")
           .withByear("1991")
           .withAddress2("Москва, Сосновая ул. 3-205")
           .withNotes("Новый пользователь")
           .withPhoto(photo)
           .inGroup(groups.iterator().next());

      app.contact().create(contact, true);

      app.goTo().gotoHomePage();

      Contacts after = app.db().contacts();  //app.contact().all();
      System.out.println("ContactCreation");
      System.out.println("after.size(): "+after.size());
      System.out.println("before.size(): "+before.size());
      logger.info("before: ");
      logger.info(before.toString());
      logger.info("after: ");
      logger.info(after.toString());
      assertEquals(after.size(), before.size() + 1);
      // assertThat(after.size(), equalTo(before.size() + 1));
      assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
      verifyContactListInUI();
   }


   /*public void testCurrentDir() {
      File currentDir = new File(".");
      File photo = new File("src/resource/fluke.png");
      System.out.println(currentDir.getAbsolutePath());
      System.out.println(photo.getAbsolutePath());
      System.out.println(photo.exists());
   } */
}
