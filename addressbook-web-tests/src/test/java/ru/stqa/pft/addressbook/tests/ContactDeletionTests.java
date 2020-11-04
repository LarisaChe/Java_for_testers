package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Description.
 *
 * @author lchernaya
 */
public class ContactDeletionTests extends TestBase {
   @Test
   public void testContactDeletion() {
      app.getContactHelper().selectContact();
      app.getContactHelper().deleteSelectedContact();
      app.getNavigationHelper().gotoHomePage();
   }
}
