package ru.stqa.pft.addressbook.tests;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

/**
 * Description.
 *
 * @author lchernaya
 */
public class ContactModificationTests extends TestBase {
   String groupName = "Test_M";
   ContactData contact = new ContactData()
        .withFirstname("Антоний")
        .withMiddlename("Максимович")
        .withLastname("Погорельский")
        .withTitle("Black hen")
        .withNickname("Ant")
        .withCompany("LTD")
        .withAddress("Москва, Нагорный проезд 9")
        .withHomephone("8499555555557")
        .withMobile("+7910555555558")
        .withEmail("mtest@mail.ru")
        .withBday("1")
        .withBmonth("December")
        .withByear("2002")
        .withAddress2("Москва, Сосновая ул. 205-3")
        .withNotes("Модифицированный пользователь");

   @BeforeMethod
   public void ensurePreconditions() {
      app.goTo().gotoHomePage();
      if (app.contact().isGroupListEmpty()) {
         app.goTo().groupPage();
         app.group().create(new GroupData().withName(groupName).withHeader("TestHeader A").withFooter("TestFooter A"));
         app.goTo().gotoHomePage();
      } else {
         groupName = app.contact().groupNameFirstInList();
      }
      if (app.contact().all().size() == 0) {
         app.contact().create(new ContactData().withFirstname("Contact test m").withMiddlename("test m").withLastname("test m").withGroup(groupName), true);
      }
   }

   @Test 
   public void testContactModification1() {
      app.goTo().gotoHomePage();
      Contacts before = app.contact().all();
      ContactData modifiedContact = before.iterator().next();

      app.contact().modify(modifiedContact.getId(), contact);
      app.goTo().gotoHomePage();

      Contacts after = app.contact().all();

      assertEquals(after.size(), before.size());

      assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact))); //ff
   }

   @Test
   public void testContactModification2() {
      app.goTo().gotoHomePage();
      Contacts before = app.contact().all();

      ContactData modifiedContact = before.iterator().next();
      app.contact().view(modifiedContact.getId());
      app.contact().modifyOnViewPage(contact);

      app.goTo().gotoHomePage();
      Contacts after = app.contact().all();

      assertEquals(after.size(), before.size());
      assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
   }

   @Test
   public void testAddContactToGroup() {
      app.goTo().gotoHomePage();
      Contacts before = app.contact().all();

      ContactData modifiedContact = before.iterator().next();
      app.contact().selectContact(modifiedContact.getId());
      app.contact().addToFirstGroupInList();

      app.goTo().gotoHomePage();
      Contacts after = app.contact().all();

      assertEquals(after.size(), before.size());
      assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
   }
}
