package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Description.
 *
 * @author lchernaya
 */
public class ContactModificationTests extends TestBase {

   @Test
   public void testContactModification1() {
      app.getContactHelper().initContactModification();
      app.getContactHelper().fillContactForm(
           new ContactData("Антоний", "Алексеевич", "Погорельский", "Black hen", "Ant", "LTD", "Москва, Нагорный проезд 9", "8495555555557", "+7910555555557",
                           "testM@mail.ru", "1", "December", "2001", "Москва, Сосновая ул. 205-3", "Модифицированный пользователь"));
      app.getContactHelper().submitContactModification();
      app.getNavigationHelper().gotoHomePage();
   }

   @Test
   public void testContactModification2() {
      app.getContactHelper().viewContactDetails();
      app.getContactHelper().initContactModificationInView();
      app.getContactHelper().fillContactForm(
           new ContactData("Антоний", "Максимович", "Погорельский", "Black hen", "Ant", "LTD", "Москва, Нагорный проезд 9", "8495555555557", "+7910555555557",
                           "testM@mail.ru", "1", "December", "2001", "Москва, Сосновая ул. 205-3", "Модифицированный пользователь"));
      app.getContactHelper().submitContactModification();
      app.getNavigationHelper().gotoHomePage();
   }

   @Test
   public void testAddContactToGroup() {
      app.getContactHelper().selectContact();
      app.getContactHelper().addContactToGroup();
      app.getNavigationHelper().gotoHomePage();
   }
}
