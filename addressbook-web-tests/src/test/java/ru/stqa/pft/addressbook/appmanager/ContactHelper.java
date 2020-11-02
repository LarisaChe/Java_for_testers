package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Description.
 *
 * @author lchernaya
 */
public class ContactHelper {

   private WebDriver wd;

   public ContactHelper(WebDriver wd) {
      this.wd = wd;
   }

   public void fillContactForm(ContactData userData) {
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

   public void initContactCreation() {
      wd.findElement(By.linkText("add new")).click();
   }

   public void submitContactCreation() {
      wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
   }
}
