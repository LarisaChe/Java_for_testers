package ru.stqa.pft.addressbook;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class UserCreationTests {

   private WebDriver wd;

   @BeforeMethod(alwaysRun = true)
   public void setUp() throws Exception {
      wd = new FirefoxDriver();
      wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
      wd.get("http://localhost/addressbook/index.php");
      logon("admin", "secret");
   }

   private void logon(String username, String password) {
      wd.findElement(By.name("user")).clear();
      wd.findElement(By.name("user")).sendKeys(username);
      wd.findElement(By.name("pass")).click();
      wd.findElement(By.name("pass")).clear();
      wd.findElement(By.name("pass")).sendKeys(password);
      wd.findElement(By.xpath("//input[@value='Login']")).click();
   }

   @Test
   public void testUserCreation() throws Exception {
      initUserCreation();
      fillUserForm(
           new UserData("Антоний", "Васильевич", "Погорелов", "Domino", "Ant", "LTD", "Москва, ул.Нагорная 7", "8495555555555", "+7910555555555",
                        "test@mail.ru", "7", "November", "1991", "Москва, Сосновая ул. 3-205", "Новый пользователь"));
      submitUserCreation();
      gotoHomePage();
      logout();
   }

   private void fillUserForm(UserData userData) {
      wd.findElement(By.name("firstname")).click();
      wd.findElement(By.name("firstname")).clear();
      wd.findElement(By.name("firstname")).sendKeys(userData.getFirstname());
      wd.findElement(By.name("middlename")).click();
      wd.findElement(By.name("middlename")).clear();
      wd.findElement(By.name("middlename")).sendKeys(userData.getMiddlename());
      wd.findElement(By.name("lastname")).click();
      wd.findElement(By.name("lastname")).click();
      wd.findElement(By.name("lastname")).clear();
      wd.findElement(By.name("lastname")).sendKeys(userData.getLastname());
      wd.findElement(By.name("nickname")).click();
      wd.findElement(By.name("nickname")).clear();
      wd.findElement(By.name("nickname")).sendKeys(userData.getNickname());
      wd.findElement(By.name("title")).click();
      wd.findElement(By.name("title")).clear();
      wd.findElement(By.name("title")).sendKeys(userData.getTitle());
      wd.findElement(By.name("company")).click();
      wd.findElement(By.name("company")).clear();
      wd.findElement(By.name("company")).sendKeys(userData.getCompany());
      wd.findElement(By.name("address")).click();
      wd.findElement(By.name("address")).clear();
      wd.findElement(By.name("address")).sendKeys(userData.getAddress());

      wd.findElement(By.name("home")).click();
      wd.findElement(By.name("home")).clear();
      wd.findElement(By.name("home")).sendKeys(userData.getHomephone());
      wd.findElement(By.name("mobile")).click();
      wd.findElement(By.name("mobile")).clear();
      wd.findElement(By.name("mobile")).sendKeys(userData.getMobile());
      wd.findElement(By.name("email")).click();
      wd.findElement(By.name("email")).clear();
      wd.findElement(By.name("email")).sendKeys(userData.getEmail());
      wd.findElement(By.name("bday")).click();
      new Select(wd.findElement(By.name("bday"))).selectByVisibleText(userData.getBday());
      wd.findElement(By.xpath("//option[@value='7']")).click();
      wd.findElement(By.name("bmonth")).click();
      new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(userData.getBmonth());
      wd.findElement(By.xpath("//option[@value='November']")).click();
      wd.findElement(By.name("byear")).click();
      wd.findElement(By.name("byear")).clear();
      wd.findElement(By.name("byear")).sendKeys(userData.getByear());
      wd.findElement(By.xpath("(//option[@value='2'])[3]")).click();

      wd.findElement(By.name("address2")).click();
      wd.findElement(By.name("address2")).clear();
      wd.findElement(By.name("address2")).sendKeys(userData.getAddress2());
      wd.findElement(By.name("notes")).click();
      wd.findElement(By.name("notes")).clear();
      wd.findElement(By.name("notes")).sendKeys(userData.getNotes());
   }

   private void initUserCreation() {
      wd.findElement(By.linkText("add new")).click();
   }

   private void submitUserCreation() {
      wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
   }

   private void gotoHomePage() {
      wd.findElement(By.linkText("home page")).click();
   }

   private void logout() {
      wd.findElement(By.linkText("Logout")).click();
   }

   @AfterMethod(alwaysRun = true)
   public void tearDown() throws Exception {
      wd.quit();
   }

   private boolean isElementPresent(By by) {
      try {
         wd.findElement(by);
         return true;
      } catch (NoSuchElementException e) {
         return false;
      }
   }

}
