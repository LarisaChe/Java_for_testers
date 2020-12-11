package ru.stqa.pft.addressbook.tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.time.LocalDateTime;
import java.util.function.DoubleToIntFunction;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

public class ContactRemoveFromGroupsTests extends TestBase {

   ContactData contactsWithGroups;

   @BeforeClass
   public void ensurePreconditions() {
      app.goTo().gotoHomePage();
      if (app.db().contacts().size() == 0) {
         app.contact().create(new ContactData().withFirstname("Contact test Group").withMiddlename("test Group").withLastname("test Group")
                                               .withBday("5").withBmonth("May").withByear("2000"), true);
      }
      contactsWithGroups = app.db().contacts().iterator().next();
      if (app.db().groups().size() == 0) {
         app.goTo().groupPage();
         app.group()
            .create(new GroupData().withName("TestGroupRemove " + String.format("%1$tY%1$tm%1$td%1$tH%1$tM%1$tS", LocalDateTime.now()))
                                   .withHeader("TestHeader A").withFooter("TestFooter A"));
         app.goTo().gotoHomePage();
         int groupId = app.db().groups().stream().mapToInt((g) -> g.getId()).max().getAsInt();
         app.contact().addToGroup(contactsWithGroups.getId(), groupId);
      } else if (contactsWithGroups.getGroups().size() == 0) {
         app.goTo().gotoHomePage();
         app.contact().addToGroup(contactsWithGroups.getId(), app.db().groups().iterator().next().getId());
      }
      System.out.println("Контакт: " + contactsWithGroups.getId());
   }

      @Test
      public void testRemoveContactFromGroup () {
         app.goTo().gotoHomePage();
         Groups before = app.db().contact(contactsWithGroups.getId()).getGroups();
         GroupData group = app.db().contact(contactsWithGroups.getId()).getGroups().iterator().next();
         System.out.println("Контакт " + contactsWithGroups.getId() + " " + contactsWithGroups.getLastname());
         System.out.println("Будет удален из группы " + group.getId() + " " + group.getName());
         Contacts contactsBefore = app.db().group(group.getId()).getContacts();
         app.contact().removeFromGroup(contactsWithGroups.getId(), group.getId());

         Groups after = app.db().contact(contactsWithGroups.getId()).getGroups();
         Contacts contactsAfter = app.db().group(group.getId()).getContacts();
         assertThat(after.size(), equalTo(before.size() - 1));
         assertThat(after, equalTo(before.without(group)));

         assertThat(contactsAfter.size(), equalTo(contactsBefore.size() - 1));
         assertThat(contactsAfter, equalTo(contactsBefore.without(contactsWithGroups)));
      }

      @Test
      public void testRemoveContactFromSomeGroups () {

         Contacts contacts = app.db().contacts();
         contactsWithGroups = contacts.iterator().next();
         int maxQuantityGroups = contactsWithGroups.getGroups().size();
         for (ContactData contact : contacts) {
            if (contact.getGroups().size() > maxQuantityGroups) {
               contactsWithGroups = app.db().contact(contact.getId());
               maxQuantityGroups = contactsWithGroups.getGroups().size();
            }
         }
         if (maxQuantityGroups <= app.groupsCount()) {
            Groups groups = app.db().groups();
            for (GroupData group : groups) {
               if (!contactsWithGroups.getGroups().contains(group)) {
                  app.goTo().gotoHomePage();
                  app.contact().addToGroup(contactsWithGroups.getId(), group.getId());
                  if (app.db().contact(contactsWithGroups.getId()).getGroups().size() >= app.groupsCount()) {
                     break;
                  }
               }
            }
            app.goTo().groupPage();
            while (app.db().groups().size() < app.groupsCount()) {
               app.goTo().groupPage();
               app.group()
                  .create(new GroupData().withName("TestGroupRemove " + String.format("%1$tY%1$tm%1$td%1$tH%1$tM%1$tS", LocalDateTime.now()))
                                         .withHeader("TestHeader A").withFooter("TestFooter A"));
               app.goTo().gotoHomePage();
               int groupId = app.db().groups().stream().mapToInt((g) -> g.getId()).max().getAsInt();
               app.contact().addToGroup(contactsWithGroups.getId(), groupId);
            }
         }
         System.out.println("Контакт: " + contactsWithGroups.getId());

         Groups groupsRemovingFromContact = new Groups();
         Groups before = app.db().contact(contactsWithGroups.getId()).getGroups();
         Groups groupsContact = app.db().contact(contactsWithGroups.getId()).getGroups();
         int i = 0;
         for (GroupData group : groupsContact) {
            System.out.println("Контакт " + contactsWithGroups.getId() + " " + contactsWithGroups.getLastname());
            System.out.println("Будет удален из группы " + group.getId() + " " + group.getName());
            app.goTo().gotoHomePage();
            groupsRemovingFromContact.add(group);
            app.contact().removeFromGroup(contactsWithGroups.getId(), group.getId());
            before = before.without(group);

            app.goTo().gotoHomePage();
            i++;
            if (i >= app.groupsCount()) {
               break;
            }
         }
         Groups after = app.db().contact(contactsWithGroups.getId()).getGroups();
         assertThat(after.size(), equalTo(before.size()));
         assertThat(after, equalTo(before));

         System.out.println("groupsRemovingFromContact.size(): " + groupsRemovingFromContact.size());
         for (GroupData group : groupsRemovingFromContact) {
            System.out.println("Проверка группы " + group.getId() + " " + group.getName());
            assertThat(app.db().group(group.getId()).getContacts().size(), equalTo(group.getContacts().size() - 1));
            assertThat(app.db().group(group.getId()).getContacts(), equalTo(group.getContacts().without(contactsWithGroups)));
         }
      }
   }
