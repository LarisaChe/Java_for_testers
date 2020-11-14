package ru.stqa.pft.addressbook.tests;

import java.util.HashSet;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

   @Test
   public void testGroupCreation() throws Exception {
      app.getNavigationHelper().gotoGroupPage();

      List<GroupData> before = app.getGroupHelper().getGroupList();
      GroupData group = new GroupData("Test with maxId", "Test header Cr1", "Test footer Cr1");
      //app.getGroupHelper().createGroup(new GroupData("Test 1", "Test header 1", "Test footer 1"));
      //app.getGroupHelper().createGroup(new GroupData("Test 1", null, null));
      app.getGroupHelper().createGroup(group);
      List<GroupData> after = app.getGroupHelper().getGroupList();
      Assert.assertEquals(after.size(), before.size() + 1);

      /*int max = 0;
      for (GroupData g : after) {
         if (g.getId() > max) {
            max = g.getId();
         }
      } */

     //Comparator<? super GroupData> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
     //after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId();
      group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
      before.add(group);
      Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
      //app.logout();
   }

}
