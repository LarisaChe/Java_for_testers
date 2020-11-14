package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.GroupData;

/**
 * Description.
 *
 * @author lchernaya
 */
public class GroupModificationTests extends TestBase {

   @Test
   public void testGroupModification() {
      app.getNavigationHelper().gotoGroupPage();
      if (!app.getGroupHelper().isThereAGroup()) {
         app.getGroupHelper().createGroup(new GroupData("Test М", "Test header М", "Test footer М"));
      }
      int before = app.getGroupHelper().getGroupCount();
      app.getGroupHelper().selectGroup();
      app.getGroupHelper().initGroupModification();
      app.getGroupHelper().fillGroupForm(new GroupData("Test Modif1", "Test header Modif1", "Test footer Modif1"));
      app.getGroupHelper().submitGroupModification();
      app.getGroupHelper().returnToGroupPage();
      int after = app.getGroupHelper().getGroupCount();
      Assert.assertEquals(after, before);
   }

}
