package ru.stqa.pft.mantis.appmanager;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ru.stqa.pft.mantis.model.UserData;

public class UserHelper extends HelperBase {

   public UserHelper(ApplicationManager app) {
      super(app);
   }

   public List<UserData> getUserList() {
      List<UserData> users = new ArrayList<UserData>();
      List<WebElement> elements = wd.findElements(By.cssSelector("table tbody tr"));
      int i = ("manage_user_edit_page.php?user_id=").length();
      String str;
      for(WebElement e : elements) {
         str = e.findElements(By.tagName("td")).get(0).findElement(By.cssSelector("a")).getAttribute("href");
         int id = Integer.parseInt(str.substring(i));
         String login = e.findElements(By.tagName("td")).get(0).getText();
         String name = e.findElements(By.tagName("td")).get(1).getText();
         String email = e.findElements(By.tagName("td")).get(2).getText();
         String role = e.findElements(By.tagName("td")).get(3).getText();
         boolean active = e.findElements(By.tagName("td")).get(4).findElement(By.cssSelector(".fa-lg")).isDisplayed();
         UserData user = new UserData(id, login, name, email, role, active);
         users.add(user);
      }
      return users;
   }

   public UserData getNonAdminActiveUser() {
      List<UserData> users = getUserList();
      for (UserData user : users) {
         if ((user.getActive()) && !(user.getRole().equals("администратор"))) {
            return user;
         }
      }
      System.out.println("User non admin and active don't find");
      return null;
   }
}
