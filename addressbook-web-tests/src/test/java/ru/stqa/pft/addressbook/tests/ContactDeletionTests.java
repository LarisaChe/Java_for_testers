package ru.stqa.pft.addressbook.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

/**
 * Description.
 *
 * @author lchernaya
 */
public class ContactDeletionTests extends TestBase {
   @Test(enabled = false)
   public void testContactDeletion() {

      app.goTo().gotoHomePage();
      List<ContactData> before = app.contact().getContactList();
      if (before.size() == 0) {
         String groupName = "Test_D";
         if (app.contact().isGroupListEmpty()) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName(groupName).withHeader("TestHeader D").withFooter("TestFooter D"));
            app.goTo().gotoHomePage();
         } else {
            groupName = app.contact().groupNameFirstInList();
         }

         //if (! app.getContactHelper().isThereAContact()) {
         app.contact().create(new ContactData().withFirstname("Contact test d").withMiddlename("test d").withLastname("test d")
                              .withBday("5").withBmonth("May").withByear("2000").withGroup(groupName), true);
         app.goTo().gotoHomePage();
         //}
         before = app.contact().getContactList();
      }
      app.contact().selectContact(before.size() - 1);
      app.contact().deleteSelectedContact();
      app.contact().waitMsg();
      app.goTo().gotoHomePage();
      List<ContactData> after = app.contact().getContactList();

      Assert.assertEquals(after.size(), before.size()-1);

      before.remove(before.size() - 1);
      Assert.assertEquals(before, after);
   }
}
