package ru.stqa.pft.addressbook.tests;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

import java.io.IOException;

import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactModificationTests extends TestBase {
   String groupName = "Test_M";
   ContactData contact = new ContactData()
        .withFirstname("Антоний")
        .withMiddlename("Максимович")
        .withLastname("Погорельский")
        .withTitle("Black hen")
        .withNickname("Ant")
        .withCompany("LTD")
        .withAddress("Москва, Нагорный проезд 9")
        .withHomePhone("8499555555557")
        .withMobilePhone("+7910555555558")
        .withWorkPhone("84955557788")
        .withEmail("mtest@mail.ru")
        .withBday("1")
        .withBmonth("December")
        .withByear("2002")
        .withAddress2("Москва, Сосновая ул. 205-3")
        .withNotes("Модифицированный пользователь");

 /*  @BeforeClass
   public void initRest() {
      app.rest().init();
   } */

   @BeforeMethod
   public void ensurePreconditions() {
      app.goTo().gotoHomePage();
      //if (app.contact().isGroupListEmpty()) {
      if (app.db().contacts().size() == 0 ) {
         app.goTo().groupPage();
         app.group().create(new GroupData().withName(groupName).withHeader("TestHeader A").withFooter("TestFooter A"));
         app.goTo().gotoHomePage();
      } else {
         //groupName = app.contact().groupNameFirstInList();
         groupName = app.db().groups().iterator().next().getName();
      }
      if (app.contact().all().size() == 0) {
         app.contact().create(new ContactData().withFirstname("Contact test m").withMiddlename("test m").withLastname("test m"), true); //.withGroup(groupName)
      }
   }

   @Test
   public void testContactModification1() throws IOException {
      try {
         skipIfNotFixed(369);
         app.goTo().gotoHomePage();
         Contacts before = app.db().contacts();
         ContactData modifiedContact = before.iterator().next();
         contact.withId(modifiedContact.getId());
         app.contact().modify(modifiedContact.getId(), contact);
         app.goTo().gotoHomePage();

         Contacts after = app.db().contacts();
         System.out.println("ContactModification1");
         System.out.println("after.size(): " + after.size());
         System.out.println("before.size(): " + before.size());
         assertEquals(after.size(), before.size());

         assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact))); //ff
         verifyContactListInUI();
      } catch (SkipException e) {
         e.printStackTrace();
      }
   }

   @Test
   public void testContactModification2() {
      app.goTo().gotoHomePage();
      Contacts before = app.db().contacts();
      ContactData modifiedContact = before.iterator().next();
      contact.withId(modifiedContact.getId());
      app.contact().view(modifiedContact.getId());
      app.contact().modifyOnViewPage(contact);

      app.goTo().gotoHomePage();
      Contacts after = app.db().contacts();
      System.out.println("ContactModification2");
      System.out.println("after.size(): "+after.size());
      System.out.println("before.size(): "+before.size());
      assertEquals(after.size(), before.size());
      assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
      verifyContactListInUI();
   }

   @Test
   public void testAddContactToGroup() {
      app.goTo().gotoHomePage();
      Contacts before = app.db().contacts();

      ContactData modifiedContact = before.iterator().next();
      app.contact().selectContact(modifiedContact.getId());
      app.contact().submitAddToGroup();

      app.goTo().gotoHomePage();
      Contacts after = app.db().contacts();
      System.out.println("ContactAddContactToGroups");
      System.out.println("after.size(): "+after.size());
      System.out.println("before.size(): "+before.size());
      assertEquals(after.size(), before.size());
      assertThat(after, equalTo(before));
      verifyContactListInUI();
   }
}
