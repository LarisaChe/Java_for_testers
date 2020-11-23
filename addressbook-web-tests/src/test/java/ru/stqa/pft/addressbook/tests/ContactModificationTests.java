package ru.stqa.pft.addressbook.tests;

import java.util.Comparator;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
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

   @Test(enabled = false)
   public void testContactModification1() {
      String groupName = "Test_M";
      app.goTo().gotoHomePage();
      if (app.contact().isGroupListEmpty()) {
         app.goTo().groupPage();
         app.group().create(new GroupData().withName(groupName).withHeader("TestHeader A").withFooter("TestFooter A"));
         app.goTo().gotoHomePage();
      } else {
         groupName = app.contact().groupNameFirstInList();
      }
      if (! app.contact().isThereAContact()) {
         app.contact().create(new ContactData().withFirstname("Contact test m").withMiddlename("test m").withLastname("test m").withGroup(groupName), true);
         app.goTo().gotoHomePage();
      }
      Contacts before = app.contact().all();

      app.contact().initContactModification(before.size() - 1);
      String lastName = "Погорельский";
      String firstName = "Антоний";
      String address = "Москва, Нагорный проезд 9";

      ContactData contact = new ContactData()
           .withFirstname(firstName) //"Антоний")
           .withMiddlename("Максимович")
           .withLastname(lastName) //"Погорельский")
           .withTitle("Black hen")
           .withNickname("Ant")
           .withCompany("LTD")
           .withAddress(address)//"Москва, Нагорный проезд 9")
           .withHomephone("8499555555557")
           .withMobile("+7910555555558")
           .withEmail("mtest@mail.ru")
           .withBday("1")
           .withBmonth("December")
           .withByear("2002")
           .withAddress2("Москва, Сосновая ул. 205-3")
           .withNotes("Модифицированный пользователь");

      app.contact().fillContactForm(contact, false);
      app.contact().submitContactModification();
      app.goTo().gotoHomePage();

      Contacts after = app.contact().all();

      Assert.assertEquals(after.size(), before.size());
/*
      ContactData contact = new ContactData(before.get(before.size()-1).getId(), firstName, lastName, address);
      before.remove(before.size()-1);
      before.add(contact);

      Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
      before.sort(byId);
      after.sort(byId); */

      Assert.assertEquals(before, after);
      MatcherAssert.assertThat(after, CoreMatchers.equalTo(before)); //ff
   }

   @Test (enabled = false)
   public void testContactModification2() {
      app.goTo().gotoHomePage();
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


      app.contact().viewContactDetails();
      app.contact().initContactModificationInView();
      app.contact().fillContactForm(contact, false);
      app.contact().submitContactModification();
      app.goTo().gotoHomePage();
   }

   @Test (enabled = false)
   public void testAddContactToGroup() {
      String groupName = "Test_M";
      app.goTo().gotoHomePage();
      if (! app.contact().isGroupListEmpty()) {
         app.goTo().groupPage();
         app.group().create(new GroupData().withName(groupName).withHeader("TestHeader A").withFooter("TestFooter A"));
         app.goTo().gotoHomePage();
      } else {
         groupName = app.contact().groupNameFirstInList();
      }
      if (! app.contact().isThereAContact()) {
         app.contact().create(new ContactData().withFirstname("Contact test m").withMiddlename("test m").withLastname("test m").withGroup(groupName), true);
         app.goTo().gotoHomePage();
      }
      app.contact().selectContact(0);
      app.contact().addContactToGroup();
      app.goTo().gotoHomePage();
   }
}
