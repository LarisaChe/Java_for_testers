package ru.stqa.pft.addressbook.appmanager;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ru.stqa.pft.addressbook.model.GroupData;

/**
 * Description.
 *
 * @author lchernaya
 */
public class GroupHelper extends HelperBase {

   public GroupHelper(WebDriver wd) {
      super(wd);
   }

   public void returnToGroupPage() {
      click(By.linkText("group page"));
   }

   public void submitGroupCreation() {
      click(By.name("submit"));
   }

   public void fillGroupForm(GroupData groupData) {
      type(By.name("group_name"), groupData.getName());
      type(By.name("group_header"), groupData.getHeader());
      type(By.name("group_footer"), groupData.getFooter());
   }

   public void initGroupCreation() {
      click(By.name("new"));
   }

   public void deleteSelectedGroup() {
      click(By.name("delete"));
   }

   public void selectGroup(int index) {
      wd.findElements(By.name("selected[]")).get(index).click();
      //click(By.name("selected[]"));
   }

   public void initGroupModification() {
      click(By.name("edit"));
   }

   public void submitGroupModification() {
      click(By.name("update"));
   }

   public void create(GroupData groupData) {
      initGroupCreation();
      fillGroupForm(groupData);
      submitGroupCreation();
      returnToGroupPage();
   }

   public void modify(int index, GroupData group) {
      selectGroup(index);
      initGroupModification();
      fillGroupForm(group);
      submitGroupModification();
      returnToGroupPage();
   }

   public void delete(int index) {
      selectGroup(index);
      deleteSelectedGroup();
      returnToGroupPage();
   }

   public boolean isThereAGroup() {
      return isElementPresent(By.name("selected[]"));
   }

   public int getGroupCount() {
      return wd.findElements(By.name("selected[]")).size();
      //return wd.findElements(By.className("group")).size();
   }

   public List<GroupData> list() {
      List<GroupData> groups = new ArrayList<GroupData>();
      List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
      for(WebElement element : elements) {
         String name = element.getText();
         int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
         groups.add(new GroupData().withId(id).withName(name));
      }
      return groups;
   }
}
