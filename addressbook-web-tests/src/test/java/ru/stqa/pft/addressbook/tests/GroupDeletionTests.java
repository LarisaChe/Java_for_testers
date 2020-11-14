package ru.stqa.pft.addressbook.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase {

   @Test
   public void testGroupDeletion() throws Exception {
      app.getNavigationHelper().gotoGroupPage();

      List<GroupData> before = app.getGroupHelper().getGroupList();
      if (!app.getGroupHelper().isThereAGroup()) {
         app.getGroupHelper().createGroup(new GroupData("Test D", "Test header D", "Test footer D"));
      }
      app.getGroupHelper().selectGroup(before.size() - 1);
      app.getGroupHelper().deleteSelectedGroup();
      app.getGroupHelper().returnToGroupPage();
      List<GroupData> after = app.getGroupHelper().getGroupList();
      Assert.assertEquals(after.size(), before.size() - 1);
   }

}
