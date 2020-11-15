package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;

import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactCreationTests extends TestBase {

   @Test
   public void testContactCreation() throws Exception {
      String groupName = "Test_С";
      app.getNavigationHelper().gotoHomePage();
      if (! app.getContactHelper().checkGroupList()) {
         app.getNavigationHelper().gotoGroupPage();
         app.getGroupHelper().createGroup(new GroupData(groupName, "TestHeader С", "TestFooter С"));
         app.getNavigationHelper().gotoHomePage();
      } else {
         groupName = app.getContactHelper().getFirstGroupName();
      }

      app.getContactHelper().initContactCreation();
      app.getContactHelper().fillContactForm(
           new ContactData("Антоний", "Васильевич", "Погорелов", "Domino", "Ant", "LTD", "Москва, ул.Нагорная 7", "8495555555555", "+7910555555555",
                           "test@mail.ru", "7", "November", "1991", "Москва, Сосновая ул. 3-205", "Новый пользователь", groupName), true);
      app.getContactHelper().submitContactCreation();
      app.getNavigationHelper().gotoHomePage();
   }

}
