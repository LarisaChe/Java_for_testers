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
   public void testContactModification() {
      app.getContactHelper().initContactModification();
      app.getContactHelper().fillContactForm(
           new ContactData("Антоний", "Алексеевич", "Погорельский", "Black hen", "Ant", "LTD", "Москва, Нагорный проезд 9", "8495555555557", "+7910555555557",
                           "testM@mail.ru", "1", "December", "2001", "Москва, Сосновая ул. 205-3", "Модифицированный пользователь"));
      app.getContactHelper().submitContactModification();
      app.getNavigationHelper().gotoHomePage();
   }
}
