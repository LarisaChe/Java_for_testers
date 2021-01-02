package ru.stqa.pft.addressbook.tests;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

public class GroupModificationTests extends TestBase {

   @BeforeMethod
   public void ensurePreconditions() {
    //  if (app.group().all().size() == 0) {
      if (app.db().groups().size() == 0) {
         app.goTo().groupPage();
         app.group().create(new GroupData().withName("Test М").withHeader("Test header М").withFooter("Test footer М"));
      }
   }

   @Test
   public void testGroupModification() {
      app.goTo().groupPage();
      //Groups before = app.group().all();
      Groups before = app.db().groups();
      GroupData modifiedGroup = before.iterator().next();
      GroupData group = new GroupData()
           .withId(modifiedGroup.getId())
           .withName("Test 1")
           .withHeader("Test header Modif1")
           .withFooter("Test footer Modif1");

      app.group().modify(group);

      //Groups after = app.group().all();
      Groups after = app.db().groups();
      assertEquals(after.size(), before.size());
      assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));

      verifyGroupListInUI();
   }
}
