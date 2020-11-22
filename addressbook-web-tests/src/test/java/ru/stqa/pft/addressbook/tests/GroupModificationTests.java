package ru.stqa.pft.addressbook.tests;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.internal.BaseTestMethod;

import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

/**
 * Description.
 *
 * @author lchernaya
 */
public class GroupModificationTests extends TestBase {

   @BeforeMethod
   public void ensurePreconditions() {
      app.goTo().groupPage();
      if (app.group().all().size() == 0) {
         app.group().create(new GroupData().withName("Test М")); //, "Test header М", "Test footer М"));
      }
   }

   @Test
   public void testGroupModification() {
      Groups before = app.group().all();
      GroupData modifiedGroup = before.iterator().next();
      GroupData group = new GroupData()
           .withId(modifiedGroup.getId())
           .withName("Test 1")
           .withHeader("Test header Modif1")
           .withFooter("Test footer Modif1");

      app.group().modify(group);

      Groups after = app.group().all();
      assertEquals(after.size(), before.size());
      assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
   }

}
