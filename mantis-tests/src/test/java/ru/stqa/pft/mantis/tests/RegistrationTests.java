package ru.stqa.pft.mantis.tests;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

public class RegistrationTests extends TestBase {

 //  @BeforeMethod
   public void startMailServer() {
      app.mail().start();
   }

   @Test
   public void testRegistration() throws IOException, MessagingException {
      long now = System.currentTimeMillis();
     // String email = String.format("us_%s@mail.ru", now); //для локальной почты
      String email = String.format("us_%s@localhost", now); // для james
      String user = String.format("us_%s", now);
      String password = "password";
      app.james().createUser(user, password);
      app.registration().start(user, email);
      System.out.println("Старт сделали");
      //List<MailMessage> mailMessages =  app.mail().waitForMail(2, 10000);   //для локальной почты
      List<MailMessage> mailMessages =  app.james().waitForMail(user, password,60000);  // для james
      String confirmationLink = findConfirmationLink(mailMessages, email);
      app.registration().finish(confirmationLink, password);
      Assert.assertTrue(app.newSession().login(user, password));
   }

  /* public String findConfirmationLink(List<MailMessage> mailMessages, String email) {
      MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
      VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
      return regex.getText(mailMessage.text);
   } */

   //@AfterMethod (alwaysRun = true)
   public void stopMailServer() {
      app.mail().stop();
   }
}
