package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

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

   public void fillContactForm(ContactData contactData, boolean creation) {
      type(By.name("firstname"), contactData.getFirstname());
      type(By.name("middlename"), contactData.getMiddlename());
      type(By.name("lastname"), contactData.getLastname());
      type(By.name("nickname"), contactData.getNickname());
      type(By.name("title"), contactData.getTitle());
      type(By.name("company"), contactData.getCompany());
      type(By.name("address"), contactData.getAddress());
      type(By.name("home"), contactData.getHomephone());
      type(By.name("mobile"), contactData.getMobile());
      type(By.name("email"), contactData.getEmail());

      click(By.name("bday"));
      new Select(wd.findElement(By.name("bday"))).selectByVisibleText(contactData.getBday());
      click(By.xpath("//option[@value='7']"));
      click(By.name("bmonth"));
      new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(contactData.getBmonth());
      click(By.xpath("//option[@value='November']"));
      type(By.name("byear"),contactData.getByear());
      //wd.findElement(By.xpath("(//option[@value='2'])[3]")).click();
      if (creation) {
         new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
      } else {
         Assert.assertFalse(isElementPresent(By.name("new_group")));
      }
      type(By.name("address2"), contactData.getAddress2());
      type(By.name("notes"), contactData.getNotes());
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

   public void createContact(ContactData contactData, boolean b) {
      initContactCreation();
      fillContactForm(contactData, b);
      submitContactCreation();
      //app.getNavigationHelper().gotoHomePage();
   }

   public boolean isThereAContact() {
      return isElementPresent(By.name("selected[]"));
   }

   public boolean checkGroupList() {
      int n = wd.findElements(By.cssSelector("select[name='to_group'] option")).size();
      System.out.println("n: "+n);
      return (n>0);
   }
}
