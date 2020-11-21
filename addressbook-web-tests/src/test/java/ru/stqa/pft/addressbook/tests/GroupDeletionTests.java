package ru.stqa.pft.addressbook.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase {

   @BeforeMethod
   public void ensurePreconditions() {
      app.getNavigationHelper().gotoGroupPage();
      if (!app.getGroupHelper().isThereAGroup()) {
         app.getGroupHelper().createGroup(new GroupData("Test D", "Test header D", "Test footer D"));
      }
   }

   @Test
   public void testGroupDeletion() throws Exception {
      List<GroupData> before = app.getGroupHelper().getGroupList();
      int index = before.size() - 1;
      app.getGroupHelper().selectGroup(index);
      app.getGroupHelper().deleteSelectedGroup();
      app.getGroupHelper().returnToGroupPage();
      List<GroupData> after = app.getGroupHelper().getGroupList();
      Assert.assertEquals(after.size(), index);

      before.remove(index);
      Assert.assertEquals(before, after);
   }

}
