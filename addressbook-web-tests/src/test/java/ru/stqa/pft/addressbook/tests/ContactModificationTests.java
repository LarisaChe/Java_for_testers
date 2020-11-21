package ru.stqa.pft.addressbook.tests;

import java.util.Comparator;
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
public class ContactModificationTests extends TestBase {

   @Test(enabled = false)
   public void testContactModification1() {
      String groupName = "Test_M";
      app.goTo().gotoHomePage();
      if (! app.getContactHelper().checkGroupList()) {
         app.goTo().groupPage();
         app.group().create(new GroupData(groupName, "TestHeader A", "TestFooter A"));
         app.goTo().gotoHomePage();
      } else {
         groupName = app.getContactHelper().getFirstGroupName();
      }
      if (! app.getContactHelper().isThereAContact()) {
         app.getContactHelper().createContact(new ContactData("Contact test m", "test m", "test m", null, null,
                                                              null, null, null, null, null, "5", "May",
                                                              "2000", null, null, groupName), true);
         app.goTo().gotoHomePage();
      }
      List<ContactData> before = app.getContactHelper().getContactList();

      app.getContactHelper().initContactModification(before.size() - 1);
      String lastName = "Погорельский";
      String firstName = "Антоний";
      String address = "Москва, Нагорный проезд 9";
      app.getContactHelper().fillContactForm(
           new ContactData(firstName, "Алексеевич", lastName, "Black hen", "Ant", "LTD", address, "8495555555557", "+7910555555557",
                           "testM@mail.ru", "1", "December", "2001", "Москва, Сосновая ул. 205-3", "Модифицированный пользователь", null), false);
      app.getContactHelper().submitContactModification();
      app.goTo().gotoHomePage();

      List<ContactData> after = app.getContactHelper().getContactList();

      Assert.assertEquals(after.size(), before.size());

      ContactData contact = new ContactData(before.get(before.size()-1).getId(), firstName, lastName, address);
      before.remove(before.size()-1);
      before.add(contact);

      Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
      before.sort(byId);
      after.sort(byId);

      Assert.assertEquals(before, after);
   }

   @Test (enabled = false)
   public void testContactModification2() {
      app.goTo().gotoHomePage();
      app.getContactHelper().viewContactDetails();
      app.getContactHelper().initContactModificationInView();
      app.getContactHelper().fillContactForm(
           new ContactData("Антоний", "Максимович", "Погорельский", "Black hen", "Ant", "LTD", "Москва, Нагорный проезд 9", "8495555555557", "+7910555555557",
                           "testM@mail.ru", "1", "December", "2001", "Москва, Сосновая ул. 205-3", "Модифицированный пользователь", null), false);
      app.getContactHelper().submitContactModification();
      app.goTo().gotoHomePage();
   }

   @Test (enabled = false)
   public void testAddContactToGroup() {
      String groupName = "Test_M";
      app.goTo().gotoHomePage();
      if (! app.getContactHelper().checkGroupList()) {
         app.goTo().groupPage();
         app.group().create(new GroupData(groupName, "TestHeader A", "TestFooter A"));
         app.goTo().gotoHomePage();
      } else {
         groupName = app.getContactHelper().getFirstGroupName();
      }
      if (! app.getContactHelper().isThereAContact()) {
         app.getContactHelper().createContact(new ContactData("Contact test m", "test m", "test m", null, null,
                                                              null, null, null, null, null, "5", "May",
                                                              "2000", null, null, groupName), true);
         app.goTo().gotoHomePage();
      }
      app.getContactHelper().selectContact(0);
      app.getContactHelper().addContactToGroup();
      app.goTo().gotoHomePage();
   }
}
