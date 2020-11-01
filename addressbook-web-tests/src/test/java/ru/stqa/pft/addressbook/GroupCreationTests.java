package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    gotoGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("Test 1", "Test header 1", "Test footer 1"));
    submitGroupCreation();
    returnToGroupPage();
    logout();
  }

}
