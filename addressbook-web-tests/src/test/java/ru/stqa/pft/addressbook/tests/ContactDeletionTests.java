package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

/**
 * Description.
 *
 * @author lchernaya
 */
public class ContactDeletionTests extends TestBase {
   @Test
   public void testContactDeletion() {
      String groupName = "Test_D";
      app.getNavigationHelper().gotoHomePage();
      if (! app.getContactHelper().checkGroupList()) {
         app.getNavigationHelper().gotoGroupPage();
         app.getGroupHelper().createGroup(new GroupData(groupName, "TestHeader D", "TestFooter D"));
         app.getNavigationHelper().gotoHomePage();
      } else {
         groupName = app.getContactHelper().getFirstGroupName();
      }

      if (! app.getContactHelper().isThereAContact()) {
         app.getContactHelper().createContact(new ContactData("Contact test d", "test d", "test d", null, null,
                                                              null, null, null, null, null, "5", "May",
                                                              "2000", null, null, groupName), true);
         app.getNavigationHelper().gotoHomePage();
      }
      app.getContactHelper().selectContact();
      app.getContactHelper().deleteSelectedContact();
      app.getNavigationHelper().gotoHomePage();
   }
}
