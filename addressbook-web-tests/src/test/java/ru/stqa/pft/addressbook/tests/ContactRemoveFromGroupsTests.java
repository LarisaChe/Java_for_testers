package ru.stqa.pft.addressbook.tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

public class ContactRemoveFromGroupsTests extends TestBase {

   ContactData contactsWithGroups;

   @BeforeMethod
   public void ensurePreconditions() {
      app.goTo().gotoHomePage();
      if (app.db().contacts().size() == 0) {
         app.contact().create(new ContactData().withFirstname("Contact test Group").withMiddlename("test Group").withLastname("test Group")
                                               .withBday("5").withBmonth("May").withByear("2000"), true);
         //contactsWithGroups = app.db().contacts().iterator().next();
      }

      Contacts contacts = app.db().contacts();
      contactsWithGroups = contacts.iterator().next();
      int maxQuantityGroups = contactsWithGroups.getGroups().size();
      for (ContactData contact : contacts) {
         if (contact.getGroups().size() > maxQuantityGroups) {
            contactsWithGroups = contact;
            maxQuantityGroups = contactsWithGroups.getGroups().size();
         }
      }
      if (maxQuantityGroups < app.groupsCount()) {
         Groups groups = app.db().groups();
         for (GroupData group : groups) {
            if (contactsWithGroups.getGroups().size() >= app.groupsCount()) {
               break;
            }
            app.goTo().gotoHomePage();
            app.contact().addToGroup(contactsWithGroups.getId(), group.getId());
         }
         app.goTo().groupPage();
         int i = app.db().groups().size();
         while (app.db().groups().size() < app.groupsCount()) {
            i++;
            app.goTo().groupPage();
            app.group()
               .create(new GroupData().withName("TestGroupRemove " + Integer.toString(i)).withHeader("TestHeader A").withFooter("TestFooter A"));
            app.goTo().gotoHomePage();
            int groupId = app.db().groups().stream().mapToInt((g) -> g.getId()).max().getAsInt();
            app.contact().addToGroup(contactsWithGroups.getId(), groupId);
         }
      }
      System.out.println("Контакт: "+contactsWithGroups.getId());
   }

   @Test (enabled = false)
   public void testRemoveContactFromGroup() {
      app.goTo().gotoHomePage();
      Groups before = contactsWithGroups.getGroups();

      GroupData group = app.db().contact(contactsWithGroups.getId()).getGroups().iterator().next();
      app.contact().removeFromGroup(contactsWithGroups.getId(), group.getId());

      Groups after = app.db().contact(contactsWithGroups.getId()).getGroups();
      assertThat(after.size(), equalTo(before.size() - 1));

      assertThat(after, equalTo(before.without(group)));
   }

   @Test
   public void testRemoveContactFromAllGroups() {
      Groups before = contactsWithGroups.getGroups();
      Groups groupsContact = app.db().contact(contactsWithGroups.getId()).getGroups();
      for (GroupData group : groupsContact) {
         app.goTo().gotoHomePage();
         app.contact().removeFromGroup(contactsWithGroups.getId(), group.getId());
         before = before.without(group);
         app.goTo().gotoHomePage();
      }
      Groups after = app.db().contact(contactsWithGroups.getId()).getGroups();
      assertThat(after.size(), equalTo(before.size()));
      assertThat(after.size(), equalTo(0));
      assertThat(after, equalTo(before));
   }

}
