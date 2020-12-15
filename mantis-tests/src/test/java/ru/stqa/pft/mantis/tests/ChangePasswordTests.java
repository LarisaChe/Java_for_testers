package ru.stqa.pft.mantis.tests;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;


public class ChangePasswordTests extends TestBase{

   @BeforeMethod
   public void startMailServer() {
      app.mail().start();
   }

   @Test
   public void testChangePassword() throws IOException, MessagingException {
      String password = "change";
      app.uiSession().loginUI(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
      app.uiSession().menuItem("Управление").click();
      app.uiSession().menuItem("Управление пользователями").click();
      UserData user = app.user().getNonAdminActiveUser();
      app.uiSession().openCard(user.getId());
      app.uiSession().clickButton("Сбросить пароль");

      List<MailMessage> mailMessages =  app.mail().waitForMail(2, 10000);
      String confirmationLink = findConfirmationLink(mailMessages, user.getEmail());
     // app.registration().finish(confirmationLink, user, password);
      app.registration().finish(confirmationLink, password);
      Assert.assertTrue(app.newSession().login(user.getLogin(), password));
   }

   @AfterMethod(alwaysRun = true)
   public void stopMailServer() {
      app.mail().stop();
   }
}
