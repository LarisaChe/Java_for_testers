package ru.stqa.pft.addressbook.tests;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

public class GroupDeletionTests extends TestBase {

   @BeforeMethod
   public void ensurePreconditions() {
      app.goTo().groupPage();
      //if (app.group().all().size() == 0) {
      if (app.db().groups().size() == 0) {
         app.group().create(new GroupData().withName("Test D").withHeader("Test header D").withFooter("Test footer D"));
      }
   }

   @Test
   public void testGroupDeletion() throws Exception {
      app.goTo().groupPage();
      Groups before = app.db().groups();
      GroupData deletedGroup = before.iterator().next();
      app.group().delete(deletedGroup);
      Groups after = app.db().groups();
      assertEquals(after.size(), before.size());// -1

      assertThat(after, equalTo(before.without(deletedGroup)));

      verifyGroupListInUI();
   }
}
