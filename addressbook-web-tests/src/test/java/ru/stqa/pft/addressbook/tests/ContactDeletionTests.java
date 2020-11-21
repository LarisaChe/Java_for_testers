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

      app.getNavigationHelper().gotoHomePage();
      List<ContactData> before = app.getContactHelper().getContactList();
      if (before.size() == 0) {
         String groupName = "Test_D";
         if (!app.getContactHelper().checkGroupList()) {
            app.getNavigationHelper().gotoGroupPage();
            app.getGroupHelper().createGroup(new GroupData(groupName, "TestHeader D", "TestFooter D"));
            app.getNavigationHelper().gotoHomePage();
         } else {
            groupName = app.getContactHelper().getFirstGroupName();
         }

         //if (! app.getContactHelper().isThereAContact()) {
         app.getContactHelper().createContact(new ContactData("Contact test d", "test d", "test d", null, null,
                                                              null, null, null, null, null, "5", "May",
                                                              "2000", null, null, groupName), true);
         app.getNavigationHelper().gotoHomePage();
         //}
         before = app.getContactHelper().getContactList();
      }
      app.getContactHelper().selectContact(before.size()-1);
      app.getContactHelper().deleteSelectedContact();
      app.getContactHelper().waitMsg();
      app.getNavigationHelper().gotoHomePage();
      List<ContactData> after = app.getContactHelper().getContactList();

      Assert.assertEquals(after.size(), before.size()-1);

      before.remove(before.size() - 1);
      Assert.assertEquals(before, after);
   }
}
