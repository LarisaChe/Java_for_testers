package ru.stqa.pft.addressbook.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase {

   @BeforeMethod
   public void ensurePreconditions() {
      app.goTo().groupPage();
      if (app.group().list().size() == 0) {
         app.group().create(new GroupData("Test D", "Test header D", "Test footer D"));
      }
   }

   @Test
   public void testGroupDeletion() throws Exception {
      List<GroupData> before = app.group().list();
      int index = before.size() - 1;
      app.group().delete(index);
      List<GroupData> after = app.group().list();
      Assert.assertEquals(after.size(), index);

      before.remove(index);
      Assert.assertEquals(before, after);
   }
}
