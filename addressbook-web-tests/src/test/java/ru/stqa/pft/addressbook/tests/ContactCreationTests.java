package ru.stqa.pft.addressbook.tests;

import java.util.Comparator;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.*;

import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactCreationTests extends TestBase {

   @Test
   public void testContactCreation() throws Exception {
      String groupName = "Test_С";
      app.getNavigationHelper().gotoHomePage();
      List<ContactData> before = app.getContactHelper().getContactList();
      if (! app.getContactHelper().checkGroupList()) {
         app.getNavigationHelper().gotoGroupPage();
         app.getGroupHelper().createGroup(new GroupData(groupName, "TestHeader С", "TestFooter С"));
         app.getNavigationHelper().gotoHomePage();
      } else {
         groupName = app.getContactHelper().getFirstGroupName();
      }

      app.getContactHelper().initContactCreation();

      ContactData contact = new ContactData("Антоний", "Васильевич", "Погорелов", "Domino", "Ant", "LTD", "Москва, ул.Нагорная 7", "8495555555555", "+7910555555555",
                      "test@mail.ru", "7", "November", "1991", "Москва, Сосновая ул. 3-205", "Новый пользователь", groupName);
      app.getContactHelper().fillContactForm(contact, true);
           app.getContactHelper().submitContactCreation();
      app.getNavigationHelper().gotoHomePage();
      List<ContactData> after = app.getContactHelper().getContactList();

      Assert.assertEquals(after.size(), before.size() + 1);

      before.add(contact);

      Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
      before.sort(byId);
      after.sort(byId);
      Assert.assertEquals(before, after);
   }

}
