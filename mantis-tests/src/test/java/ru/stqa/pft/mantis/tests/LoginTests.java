package ru.stqa.pft.mantis.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import ru.stqa.pft.mantis.appmanager.HttpSession;

/**
 * Description.
 *
 * @author lchernaya
 */
public class LoginTests extends TestBase {

   @Test
   public  void testLogin() throws IOException {
      HttpSession session = app.newSession();
      Assert.assertTrue(session.login("administrator", "root"));
      Assert.assertTrue(session.isLoggedInAs("administrator")); //ADMINISTRATOR
   }

}
