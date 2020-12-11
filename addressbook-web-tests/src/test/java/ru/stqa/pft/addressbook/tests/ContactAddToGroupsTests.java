package ru.stqa.pft.addressbook.tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

import java.time.LocalDateTime;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

public class ContactAddToGroupsTests extends TestBase {

   ContactData contactsWithoutGroups;

   @BeforeMethod
   public void ensurePreconditions() {
      app.goTo().gotoHomePage();
      if (app.db().contacts().size() == 0) {
         app.contact().create(new ContactData().withFirstname("Contact test Group").withMiddlename("test Group").withLastname("test Group")
                                               .withBday("5").withBmonth("May").withByear("2000"), true);
      }
      contactsWithoutGroups = app.db().contacts().iterator().next();

      if ((app.db().groups().size() == 0) || (contactsWithoutGroups.getGroups().size() == app.db().groups().size())) {
         app.goTo().groupPage();
         app.group().create(new GroupData().withName("TestGroup " + String.format("%1$tY%1$tm%1$td%1$tH%1$tM%1$tS", LocalDateTime.now()))
                                           .withHeader("TestHeader A").withFooter("TestFooter A"));
      }
   }

   @Test
   public void testAddContactInGroup() {
      app.goTo().gotoHomePage();
      System.out.println("Выбран контакт: " + contactsWithoutGroups.getId() + " " + contactsWithoutGroups.getLastname());
      GroupData groupForContact = new GroupData();
      Groups groups = app.db().groups();
      for (GroupData group : groups) {
         if (!contactsWithoutGroups.getGroups().contains(group)) {
            groupForContact = group;
         }
      }
      System.out.println("Выбрана группа: " + groupForContact.getId() + " " + groupForContact.getName());

      Contacts contactsBefore = app.db().group(groupForContact.getId()).getContacts();
      System.out.println("контакты группы до: ");
      System.out.println(contactsBefore);
      Groups before = app.db().contact(contactsWithoutGroups.getId()).getGroups();

      app.contact().addToGroup(contactsWithoutGroups.getId(), groupForContact.getId());

      Groups after = app.db().contact(contactsWithoutGroups.getId()).getGroups();
      Contacts contactsAfter = app.db().group(groupForContact.getId()).getContacts();
      System.out.println("контакты группы после: ");
      System.out.println(contactsAfter);
      //assertThat(after.size(), equalTo(before.size() + 1));
      assertEquals(after.size(), before.size() + 1);
      assertThat(after, equalTo(before.withAdded(groupForContact)));

      assertThat(contactsAfter.size(), equalTo(contactsBefore.size() + 1));
      assertThat(contactsAfter, equalTo(contactsBefore.withAdded(contactsWithoutGroups)));
   }

   @Test
   public void testAddContactInSomeGroups() {

      while ((app.db().groups().size() - app.db().contact(contactsWithoutGroups.getId()).getGroups().size())
             < app.groupsCount()) {
         app.group().create(new GroupData().withName("TestGroup " + String.format("%1$tY%1$tm%1$td%1$tH%1$tM%1$tS", LocalDateTime.now()))
                                           .withHeader("TestHeader A").withFooter("TestFooter A"));
         app.goTo().groupPage();
      }

      Groups groupsAddingToContact = new Groups();
      app.goTo().gotoHomePage();
      Groups before = app.db().contact(contactsWithoutGroups.getId()).getGroups();
      Groups groups = app.db().groups();
      int i = 0;
      for (GroupData group : groups) {
         if (!before.contains(group)) {
            groupsAddingToContact.add(group);
            app.contact().addToGroup(contactsWithoutGroups.getId(), group.getId());
            app.goTo().gotoHomePage();
            before = before.withAdded(group);
            i++;
            if (i >= app.groupsCount()) {
               break;
            }
         }
      }
      Groups after = app.db().contact(contactsWithoutGroups.getId()).getGroups();

      assertThat(after.size(), equalTo(before.size()));
      assertThat(after, equalTo(before));
      System.out.println("groupsAddingToContact.size(): " + groupsAddingToContact.size());
      for (GroupData group : groupsAddingToContact) {
         System.out.println("Проверка группы " + group.getId() + " " + group.getName());
         assertThat(app.db().group(group.getId()).getContacts().size(), equalTo(group.getContacts().size() + 1));
         assertThat(app.db().group(group.getId()).getContacts(), equalTo(group.getContacts().withAdded(contactsWithoutGroups)));
      }
   }
}
