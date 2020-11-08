package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

/**
 * Description.
 *
 * @author lchernaya
 */
public class ContactModificationTests extends TestBase {

   @Test
   public void testContactModification1() {
      String groupName = "Test_M";
      app.getNavigationHelper().gotoHomePage();
      if (! app.getContactHelper().checkGroupList()) {
         app.getNavigationHelper().gotoGroupPage();
         app.getGroupHelper().createGroup(new GroupData(groupName, "TestHeader A", "TestFooter A"));
         app.getNavigationHelper().gotoHomePage();
      } else {
         groupName = app.getContactHelper().getFirstGroupName();
      }
      if (! app.getContactHelper().isThereAContact()) {
         app.getContactHelper().createContact(new ContactData("Contact test m", "test m", "test m", null, null,
                                                              null, null, null, null, null, "5", "May",
                                                              "2000", null, null, groupName), true);
         app.getNavigationHelper().gotoHomePage();
      }
      app.getContactHelper().initContactModification();
      app.getContactHelper().fillContactForm(
           new ContactData("Антоний", "Алексеевич", "Погорельский", "Black hen", "Ant", "LTD", "Москва, Нагорный проезд 9", "8495555555557", "+7910555555557",
                           "testM@mail.ru", "1", "December", "2001", "Москва, Сосновая ул. 205-3", "Модифицированный пользователь", null), false);
      app.getContactHelper().submitContactModification();
      app.getNavigationHelper().gotoHomePage();
   }

   @Test
   public void testContactModification2() {
      app.getNavigationHelper().gotoHomePage();
      app.getContactHelper().viewContactDetails();
      app.getContactHelper().initContactModificationInView();
      app.getContactHelper().fillContactForm(
           new ContactData("Антоний", "Максимович", "Погорельский", "Black hen", "Ant", "LTD", "Москва, Нагорный проезд 9", "8495555555557", "+7910555555557",
                           "testM@mail.ru", "1", "December", "2001", "Москва, Сосновая ул. 205-3", "Модифицированный пользователь", null), false);
      app.getContactHelper().submitContactModification();
      app.getNavigationHelper().gotoHomePage();
   }

   @Test
   public void testAddContactToGroup() {
      String groupName = "Test_M";
      app.getNavigationHelper().gotoHomePage();
      if (! app.getContactHelper().checkGroupList()) {
         app.getNavigationHelper().gotoGroupPage();
         app.getGroupHelper().createGroup(new GroupData(groupName, "TestHeader A", "TestFooter A"));
         app.getNavigationHelper().gotoHomePage();
      } else {
         groupName = app.getContactHelper().getFirstGroupName();
      }
      if (! app.getContactHelper().isThereAContact()) {
         app.getContactHelper().createContact(new ContactData("Contact test m", "test m", "test m", null, null,
                                                              null, null, null, null, null, "5", "May",
                                                              "2000", null, null, groupName), true);
         app.getNavigationHelper().gotoHomePage();
      }
      app.getContactHelper().selectContact();
      app.getContactHelper().addContactToGroup();
      app.getNavigationHelper().gotoHomePage();
   }
}
