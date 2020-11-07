package ru.stqa.pft.addressbook.tests;

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
      app.getGroupHelper().selectGroup();
      app.getGroupHelper().initGroupModification();
      //app.getGroupHelper().fillGroupForm(new GroupData("Test Modif1", "Test header Modif1", "Test footer Modif1"));
      app.getGroupHelper().fillGroupForm(new GroupData("Test 1", "Test header 1", "Test footer 1"));
      app.getGroupHelper().submitGroupModification();
      app.getGroupHelper().returnToGroupPage();
   }

}
