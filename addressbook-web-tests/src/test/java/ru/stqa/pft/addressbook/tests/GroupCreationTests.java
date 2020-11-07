package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().initGroupCreation();
    //app.getGroupHelper().fillGroupForm(new GroupData("Test 1", "Test header 1", "Test footer 1"));
    app.getGroupHelper().fillGroupForm(new GroupData("Test 1", null, null));
    app.getGroupHelper().submitGroupCreation();
    app.getGroupHelper().returnToGroupPage();
    //app.logout();
  }

}
