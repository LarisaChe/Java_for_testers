package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;
import org.openqa.selenium.*;

import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

   @Test
   public void testContactCreation() throws Exception {
      initContactCreation();
      fillContactForm(
           new ContactData("Антоний", "Васильевич", "Погорелов", "Domino", "Ant", "LTD", "Москва, ул.Нагорная 7", "8495555555555", "+7910555555555",
                           "test@mail.ru", "7", "November", "1991", "Москва, Сосновая ул. 3-205", "Новый пользователь"));
      submitContactCreation();
      gotoHomePage();

   }

}
