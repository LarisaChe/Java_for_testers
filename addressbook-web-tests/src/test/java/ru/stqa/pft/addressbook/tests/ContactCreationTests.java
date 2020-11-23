package ru.stqa.pft.addressbook.tests;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

import org.testng.annotations.*;

import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactCreationTests extends TestBase {

   @Test(enabled = false)
   public void testContactCreation() throws Exception {
      String groupName = "Test_С";

      app.goTo().gotoHomePage();
      Contacts before = app.contact().all();

      if (app.contact().isGroupListEmpty()) {
         app.goTo().groupPage();
         app.group().create(new GroupData().withName(groupName).withHeader("TestHeader С").withFooter("TestFooter С"));
         app.goTo().gotoHomePage();
      } else {
         groupName = app.contact().groupNameFirstInList();
      }

      ContactData contact = new ContactData()
           .withFirstname("Антоний")
           .withMiddlename("Васильевич")
           .withLastname("Погорелов")
           .withTitle("Domino")
           .withNickname("Ant")
           .withCompany("LTD")
           .withAddress("Москва, ул.Нагорная 7")
           .withHomephone("8495555555555")
           .withMobile("+7910555555555")
           .withEmail("test@mail.ru")
           .withBday("7")
           .withBmonth("November")
           .withByear("1991")
           .withAddress2("Москва, Сосновая ул. 3-205")
           .withNotes("Новый пользователь")
           .withGroup(groupName);

      app.contact().create(contact, true);

      app.goTo().gotoHomePage();

      Contacts after = app.contact().all();

      assertEquals(after.size(), before.size() + 1);
     // assertThat(after.size(), equalTo(before.size() + 1));
      assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
   }

}
