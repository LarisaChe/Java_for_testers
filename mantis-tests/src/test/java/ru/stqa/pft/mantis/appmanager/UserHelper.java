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
      int i = (app.getProperty("web.baseUrl")+"/manage_user_edit_page.php?user_id=").length(); //http://localhost/mantisbt-2.24.3/manage_user_edit_page.php?user_id=1
      String str;
      for(WebElement e : elements) {
         str = e.findElements(By.tagName("td")).get(0).findElement(By.cssSelector("a")).getAttribute("href");
        // System.out.println(str);
         int id = Integer.parseInt(str.substring(i));
         String login = e.findElements(By.tagName("td")).get(0).getText();
         String name = e.findElements(By.tagName("td")).get(1).getText();
         String email = e.findElements(By.tagName("td")).get(2).getText();
         String role = e.findElements(By.tagName("td")).get(3).getText();
         boolean active = e.findElements(By.tagName("td")).get(4).findElement(By.cssSelector(".fa-lg")).isDisplayed();
         UserData user = new UserData().withId(id).withUser(login).withName(name).withEmail(email).withRole(role).withActive(active);
         users.add(user);
      }
      return users;
   }

   public UserData getNonAdminActiveUser() {
      List<UserData> users = getUserList();
 System.out.println(users.toString());
      for (UserData user : users) {
         if ((user.getActive()) && !(user.getRole().equals("администратор")||user.getRole().equals("administrator"))) {
            return user;
         }
      }
      System.out.println("User non admin and active don't find");
      return null;
   }
}
