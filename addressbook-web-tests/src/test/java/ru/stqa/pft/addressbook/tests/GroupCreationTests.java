package ru.stqa.pft.addressbook.tests;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

   @Test
   public void testGroupCreation() throws Exception {
      app.goTo().groupPage();

      Set<GroupData> before = app.group().all();
      GroupData group = new GroupData().withName("Test Cr1").withHeader("Test header Cr1").withFooter("Test footer Cr1");
      app.group().create(group);
      Set<GroupData> after = app.group().all();
      Assert.assertEquals(after.size(), before.size() + 1);

      //before.add(group);
      //Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
      group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
      before.add(group);
      //before.sort(byId);
      //after.sort(byId);

      Assert.assertEquals(before, after);

      //app.logout();
   }

}
