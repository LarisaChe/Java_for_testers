package ru.stqa.pft.addressbook.tests;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

public class GroupCreationTests extends TestBase {

   @Test
   public void testGroupCreation() throws Exception {
      app.goTo().groupPage();

      Groups before = app.group().all();
      GroupData group = new GroupData().withName("Test Cr1").withHeader("Test header Cr1").withFooter("Test footer Cr1");
      app.group().create(group);
      Groups after = app.group().all();
      assertThat(after.size(), equalTo(before.size() + 1));

      assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
      //app.logout();
   }

}
