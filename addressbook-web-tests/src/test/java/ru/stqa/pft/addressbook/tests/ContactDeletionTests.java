package ru.stqa.pft.addressbook.tests;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

/**
 * Description.
 *
 * @author lchernaya
 */
public class ContactDeletionTests extends TestBase {

   @BeforeMethod
   public void ensurePreconditions() {
      app.goTo().gotoHomePage();
      String groupName = "Test_D";
      if (app.contact().all().size() == 0) {
         if (app.contact().isGroupListEmpty()) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName(groupName).withHeader("TestHeader D").withFooter("TestFooter D"));
            app.goTo().gotoHomePage();
         } else {
            groupName = app.contact().groupNameFirstInList();
         }
         app.contact().create(new ContactData().withFirstname("Contact test d").withMiddlename("test d").withLastname("test d")
                                               .withBday("5").withBmonth("May").withByear("2000").withGroup(groupName), true);
      }
   }

   @Test
   public void testContactDeletion() {
      app.goTo().gotoHomePage();
      Contacts before = app.contact().all();
      ContactData deletedContact = before.iterator().next();
      app.contact() .delete(deletedContact);
      app.goTo().gotoHomePage();
      Contacts after = app.contact().all();
      //assertEquals(after.size(), before.size()-1);
      assertThat(after, equalTo(before.without(deletedContact)));
   }
}
