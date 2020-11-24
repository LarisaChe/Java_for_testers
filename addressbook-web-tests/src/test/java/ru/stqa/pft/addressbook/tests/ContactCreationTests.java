package ru.stqa.pft.addressbook.tests;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

import org.testng.annotations.*;

import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactCreationTests extends TestBase {

   String groupName = "Test_Cr";

   @BeforeMethod
   public void ensurePreconditions() {
      app.goTo().gotoHomePage();
      if (app.contact().isGroupListEmpty()) {
         app.goTo().groupPage();
         app.group().create(new GroupData().withName(groupName).withHeader("TestHeader Cr").withFooter("TestFooter Cr"));
         app.goTo().gotoHomePage();
      } else {
         groupName = app.contact().groupNameFirstInList();
      }
   }

   @Test
   public void testContactCreation() throws Exception {

      app.goTo().gotoHomePage();
      Contacts before = app.contact().all();

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
      System.out.println("ContactCreation");
      System.out.println("after.size(): "+after.size());
      System.out.println("before.size(): "+before.size());
      assertEquals(after.size(), before.size() + 1);
      // assertThat(after.size(), equalTo(before.size() + 1));
      assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
   }

}
