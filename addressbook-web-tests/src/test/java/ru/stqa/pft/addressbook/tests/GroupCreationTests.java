package ru.stqa.pft.addressbook.tests;

import java.util.Comparator;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

   @Test
   public void testGroupCreation() throws Exception {
      app.goTo().groupPage();

      List<GroupData> before = app.group().list();
      GroupData group = new GroupData("Test with maxId", "Test header Cr1", "Test footer Cr1");
      //app.getGroupHelper().createGroup(new GroupData("Test 1", null, null));
      app.group().create(group);
      List<GroupData> after = app.group().list();
      Assert.assertEquals(after.size(), before.size() + 1);

      //group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
      before.add(group);

      Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
      before.sort(byId);
      after.sort(byId);

      Assert.assertEquals(before, after);
      //Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
      //app.logout();
   }

}
