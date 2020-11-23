package ru.stqa.pft.addressbook.appmanager;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

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

   public void selectContact(int index) {
      wd.findElements(By.name("selected[]")).get(index).click();
      //click(By.name("selected[]"));
   }

   public void deleteSelectedContact() {
     click(By.xpath("//input[@value='Delete']"));
      wd.switchTo().alert().accept();
   }

   public void initContactModification(int index) {
      //click(By.xpath("//img[@alt='Edit']"));
      wd.findElements(By.cssSelector("[title='Edit']")).get(index).click();
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

   public void create(ContactData contactData, boolean b) {
      initContactCreation();
      fillContactForm(contactData, b);
      submitContactCreation();
      waitMsg();
      //app.getNavigationHelper().gotoHomePage();
   }

   public boolean isThereAContact() {
      return isElementPresent(By.name("selected[]"));
   }

   public boolean isGroupListEmpty() {
      int n = wd.findElements(By.cssSelector("select[name='to_group'] option")).size();
      //System.out.println("n: "+n);
      return (n == 0);
   }

   public String groupNameFirstInList() {
      System.out.println(wd.findElements(By.cssSelector("select[name='to_group'] option")).get(0).getText());
      return wd.findElements(By.cssSelector("select[name='to_group'] option")).get(0).getText();
   }

   public List<ContactData> getContactList() {
      List<ContactData> contacts = new ArrayList<>();
      List<WebElement> elements  = wd.findElements(By.cssSelector("[name='entry']"));
      for (WebElement e : elements) {
         int id = Integer.parseInt(e.findElements(By.cssSelector("td input")).get(0).getAttribute("id"));
         String lastName = e.findElements(By.tagName("td")).get(1).getText();
         String firstName = e.findElements(By.tagName("td")).get(2).getText();
         ContactData contact = new ContactData().withId(id).withFirstname(firstName).withLastname(lastName);
         contacts.add(contact);
      }
      return contacts;
   }

   public Contacts all() {
      Contacts contacts = new Contacts();
      List<WebElement> elements  = wd.findElements(By.cssSelector("[name='entry']"));
      for (WebElement e : elements) {
         int id = Integer.parseInt(e.findElements(By.cssSelector("td input")).get(0).getAttribute("id"));
         System.out.println("id: "+id);
         String lastName = e.findElements(By.tagName("td")).get(1).getText();
         String firstName = e.findElements(By.tagName("td")).get(2).getText();
         //ContactData contact = new ContactData(id,  firstName, lastName, null);
         //contacts.add(contact);
         contacts.add(new ContactData().withId(id).withFirstname(firstName).withLastname(lastName));
      }
      return contacts;
   }

   public void waitMsg() {
      /*int i = 0;
      while (isElementPresent(By.className("msgbox"))) {
         i++;
      }
      System.out.println("Ожидание: "+i);*/
      wd.findElement(By.cssSelector("div.msgbox"));
   }
}
