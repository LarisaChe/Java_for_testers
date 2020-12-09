package ru.stqa.pft.addressbook.tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

public class ContactAddToGroupsTests extends TestBase{

   ContactData contactsWithoutGroups;

   @BeforeMethod
   public void ensurePreconditions() {
      app.goTo().gotoHomePage();
      if (app.db().contacts().size() == 0) {
         app.contact().create(new ContactData().withFirstname("Contact test Group").withMiddlename("test Group").withLastname("test Group")
                                               .withBday("5").withBmonth("May").withByear("2000"), true);
         contactsWithoutGroups = app.db().contacts().iterator().next();
      }
      else {
         Contacts contacts = app.db().contacts();
         Boolean isContactWithoutGroup = false;
         for (ContactData contact : contacts) {
            if (contact.getGroups().size() == 0) {
               isContactWithoutGroup = true;
               contactsWithoutGroups = contact;
               break;
            }
         }
         if (!isContactWithoutGroup) {
            app.contact().create(new ContactData().withFirstname("Contact test Group").withMiddlename("test Group").withLastname("test Group")
                                                  .withBday("5").withBmonth("May").withByear("2000"), true);
            contactsWithoutGroups = app.db().contacts().iterator().next();
         }
      }

      app.goTo().groupPage();
      int i=app.db().groups().size();
      while (app.db().groups().size() < app.groupsCount()) {
         i++;
         app.group().create(new GroupData().withName("TestGroup "+Integer.toString(i)).withHeader("TestHeader A").withFooter("TestFooter A"));
         app.goTo().groupPage();
      }

      while (app.db().groups().size() > app.groupsCount()) {
         app.group().delete(app.db().groups().iterator().next());
         app.goTo().groupPage();
      }
   }

   @Test(enabled = false)
   public void testAddContactInGroup() {
      app.goTo().gotoHomePage();
      GroupData group = app.db().groups().iterator().next();
      Groups before = contactsWithoutGroups.getGroups();
      app.contact().addToGroup(contactsWithoutGroups.getId(), group.getId());
      Groups after = app.db().contact(contactsWithoutGroups.getId()).getGroups();
      assertThat(after.size(), equalTo(before.size() + 1));
      assertThat(after, equalTo(before.withAdded(group)));
   }

   @Test
   public void testAddContactInAllGroups() {
      app.goTo().gotoHomePage();
      Groups before = contactsWithoutGroups.getGroups();
      Groups groups = app.db().groups();
      for (GroupData group : groups) {
         app.contact().addToGroup(contactsWithoutGroups.getId(), group.getId());
         app.goTo().gotoHomePage();
         before = before.withAdded(group);
      }
      Groups after = app.db().contact(contactsWithoutGroups.getId()).getGroups();

      assertThat(after.size(), equalTo(before.size()));
      assertThat(after, equalTo(before));
   }

}
