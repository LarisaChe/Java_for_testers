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
public class ContactHelper extends HelperBase {

   public ContactHelper(WebDriver wd) {
      super(wd);
   }

   public void fillContactForm(ContactData userData) {
      type(By.name("firstname"), userData.getFirstname());
      type(By.name("middlename"), userData.getMiddlename());
      type(By.name("lastname"), userData.getLastname());
      type(By.name("nickname"), userData.getNickname());
      type(By.name("title"), userData.getTitle());
      type(By.name("company"), userData.getCompany());
      type(By.name("address"), userData.getAddress());
      type(By.name("home"), userData.getHomephone());
      type(By.name("mobile"), userData.getMobile());
      type(By.name("email"), userData.getEmail());

      click(By.name("bday"));
      new Select(wd.findElement(By.name("bday"))).selectByVisibleText(userData.getBday());
      click(By.xpath("//option[@value='7']"));
      click(By.name("bmonth"));
      new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(userData.getBmonth());
      click(By.xpath("//option[@value='November']"));
      type(By.name("byear"),userData.getByear());
      //wd.findElement(By.xpath("(//option[@value='2'])[3]")).click();

      type(By.name("address2"), userData.getAddress2());
      type(By.name("notes"), userData.getNotes());
   }

   public void initContactCreation() {
      click(By.linkText("add new"));
   }

   public void submitContactCreation() {
      click(By.xpath("(//input[@name='submit'])[2]"));
   }

   public void selectContact() {
      click(By.name("selected[]"));
   }

   public void deleteSelectedContact() {
     click(By.xpath("//input[@value='Delete']"));
      wd.switchTo().alert().accept();
   }

   public void initContactModification() {
      click(By.xpath("//img[@alt='Edit']"));
   }

   public void submitContactModification() {
      click(By.name("update"));
   }

   public void viewContactDetails() {
      click(By.xpath("//img[@alt='Details']"));
   }

   public void initContactModificationInView() {
      click(By.name("modifiy"));
   }

   public void addContactToGroup() {
      click(By.name("add"));
   }
}
