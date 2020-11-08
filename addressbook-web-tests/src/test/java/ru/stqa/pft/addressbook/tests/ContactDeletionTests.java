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
      app.getNavigationHelper().gotoHomePage();
      if (! app.getContactHelper().isThereAContact()) {
         app.getNavigationHelper().gotoGroupPage();
         app.getGroupHelper().createGroup(new GroupData("Test_D", "TestHeader D", "TestFooter D"));
         app.getNavigationHelper().gotoHomePage();
         app.getContactHelper().createContact(new ContactData("Contact test d", "test d", "test d", null, null,
                                                              null, null, null, null, null, "5", "May",
                                                              "2000", null, null, "Test_D"), true);
         app.getNavigationHelper().gotoHomePage();
      }
      app.getContactHelper().selectContact();
      app.getContactHelper().deleteSelectedContact();
      app.getNavigationHelper().gotoHomePage();
   }
}
