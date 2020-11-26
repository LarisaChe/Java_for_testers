package ru.stqa.pft.addressbook.appmanager;

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
      type(By.name("home"), contactData.getHomePhone());
      type(By.name("mobile"), contactData.getMobilePhone());
      type(By.name("work"), contactData.getWorkPhone());
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

   public void selectContact(int id) {
     // wd.findElements(By.name("selected[]")).get(id).click();
      wd.findElement(By.cssSelector("input[value='"+id+"']")).click();
      //click(By.name("selected[]"));
   }

   public void deleteSelectedContact() {
     click(By.xpath("//input[@value='Delete']"));
      wd.switchTo().alert().accept();
   }

   public void initContactModification(int id) {
      wd.findElement(By.cssSelector("[href='edit.php?id="+id+"']")).click();
   }

   public void submitContactModification() {
      click(By.name("update"));
   }

   public void view(int id) {
      //click(By.xpath("//img[@alt='Details']"));
      wd.findElement(By.cssSelector("[href='view.php?id="+id+"']")).click();
   }

   public void initModificationInView() {
      click(By.name("modifiy"));
   }

   public void addToFirstGroupInList() {
      click(By.name("add"));
   }

   public void create(ContactData contactData, boolean b) {
      initContactCreation();
      fillContactForm(contactData, b);
      submitContactCreation();
      waitMsg();
      //app.getNavigationHelper().gotoHomePage();
   }

   public void modify(int id, ContactData contact) {
      initContactModification(id);
      fillContactForm(contact, false);
      submitContactModification();
      waitMsg();
   }

   public void modifyOnViewPage(ContactData contact) {
      initModificationInView();
      fillContactForm(contact, false);
      submitContactModification();
      waitMsg();
   }

   public void delete(ContactData contact) {
      selectContact(contact.getId());
      deleteSelectedContact();
      waitMsg();
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

   public Contacts all() {
      Contacts contacts = new Contacts();
      //System.out.println("all ");
      List<WebElement> elements  = wd.findElements(By.cssSelector("[name='entry']"));
      for (WebElement e : elements) {
         int id = Integer.parseInt(e.findElements(By.cssSelector("td input")).get(0).getAttribute("id"));
        // System.out.println("id: "+id);
         String lastName = e.findElements(By.tagName("td")).get(1).getText();
         String firstName = e.findElements(By.tagName("td")).get(2).getText();
         //String[] phones = e.findElements(By.tagName("td")).get(5).getText().split("\n");
         String allPhones = e.findElements(By.tagName("td")).get(5).getText();
         contacts.add(new ContactData().withId(id).withFirstname(firstName).withLastname(lastName)
                      .withAllPhones(allPhones));
               // .withHomePhone(phones[0]).withMobilePhone(phones[1]).withWorkPhone(phones[2]));
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

   public ContactData infoFromEditForm(ContactData contact) {
      initContactModificationByID(contact.getId());
      String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
      String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
      String homePhone = wd.findElement(By.name("home")).getAttribute("value");
      String mobilePhone = wd.findElement(By.name("mobile")).getAttribute("value");
      String workPhone = wd.findElement(By.name("work")).getAttribute("value");

      wd.navigate().back();
      return new ContactData().withId(contact.getId()).withFirstname(firstName).withLastname(lastName)
           .withHomePhone(homePhone).withMobilePhone(mobilePhone).withWorkPhone(workPhone);
   }

   private void initContactModificationByID(int id) {
     /* WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
      WebElement row = checkbox.findElement(By.xpath("./../.."));
      List<WebElement> cells = row.findElements(By.tagName("td"));
      cells.get(7).findElement(By.tagName("a")).click(); */

      //wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a", id))).click();
      wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a", id))).click();
   }

}
