package ru.stqa.pft.addressbook.model;

import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "addressbook")
public class ContactData {
   @Id
   @Column(name = "id")
   private int id;
   @Expose
   @Column(name = "firstname")
   private  String firstname;
   @Expose
   @Column(name = "middlename")
   private  String middlename;
   @Expose
   @Column(name = "lastname")
   private  String lastname;
   @Column(name = "title")
   private  String title;
   @Expose
   @Column(name = "nickname")
   private  String nickname;
   @Column(name = "company")
   private  String company;
   @Expose
   @Column(name = "address")
   @Type(type = "text")
   private  String address;
   @Column(name = "home")
   @Type(type = "text")
   private  String homePhone;
   @Expose
   @Column(name = "mobile")
   @Type(type = "text")
   private  String mobilePhone;
   @Column(name = "work")
   @Type(type = "text")
   private  String workPhone;
   @Expose
   @Column(name = "email")
   @Type(type = "text")
   private  String email;
   @Column(name = "email2")
   @Type(type = "text")
   private  String email2;
   @Column(name = "email3")
   @Type(type = "text")
   private  String email3;
   @Column(name = "bday", columnDefinition = "tinyint")
   private  String bday;
   @Column(name = "bmonth")
   private  String bmonth;
   @Column(name = "byear")
   private  String byear;
   @Column(name = "address2")
   @Type(type = "text")
   private  String address2;
   @Column(name = "notes")
   @Type(type = "text")
   private  String notes;
   @Transient
   private String allPhones;
   @Transient
   private String allEmails;
   @Column(name = "photo")
   @Type(type = "text")
   private String photo;

   /* @Transient
   private String group; */
   @ManyToMany(fetch = FetchType.EAGER)
   @JoinTable(name = "address_in_groups", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name="group_id"))
   private Set<GroupData> groups= new HashSet<GroupData>();

   public int getId() {
      return id;
   }

   public ContactData withId(int id) {
      this.id = id;
      return this;
   }

   @Override
   public String toString() {
      return "ContactData{" +
             "id=" + id +
             ", firstname='" + firstname + '\'' +
             ", middlename='" + middlename + '\'' +
             ", lastname='" + lastname + '\'' +
             ", nickname='" + nickname + '\'' +
             ", address='" + address + '\'' +
             ", homePhone='" + homePhone + '\'' +
             ", mobilePhone='" + mobilePhone + '\'' +
             ", workPhone='" + workPhone + '\'' +
             ", email='" + email + '\'' +
             ", bday='" + bday + '\'' +
             ", bmonth='" + bmonth + '\'' +
             ", byear='" + byear + '\'' +
             '}';
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      ContactData that = (ContactData) o;
      return id == that.id &&
             Objects.equals(firstname, that.firstname) &&
             Objects.equals(middlename, that.middlename) &&
             Objects.equals(lastname, that.lastname) &&
             Objects.equals(nickname, that.nickname) &&
             Objects.equals(address, that.address) &&
             Objects.equals(homePhone, that.homePhone) &&
             Objects.equals(mobilePhone, that.mobilePhone) &&
             Objects.equals(workPhone, that.workPhone) &&
             Objects.equals(email, that.email) &&
             Objects.equals(bday, that.bday) &&
             Objects.equals(bmonth, that.bmonth) &&
             Objects.equals(byear, that.byear);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, firstname, middlename, lastname, nickname, address, homePhone, mobilePhone, workPhone, email, bday, bmonth, byear);
   }

   public String getFirstname() {
      return firstname;
   }

   public ContactData withFirstname (String firstname) {
      this.firstname = firstname;
      return this;
   }

   public ContactData withMiddlename(String middlename) {
      this.middlename = middlename;
      return this;
   }

   public ContactData withLastname(String lastname) {
      this.lastname = lastname;
      return this;
   }

   public ContactData withTitle(String title) {
      this.title = title;
      return this;
   }

   public ContactData withNickname(String nickname) {
      this.nickname = nickname;
      return this;
   }

   public ContactData withCompany(String company) {
      this.company = company;
      return this;
   }

   public ContactData withAddress(String address) {
      this.address = address;
      return this;
   }

   public ContactData withHomePhone(String homePhone) {
      this.homePhone = homePhone;
      return this;
   }

   public ContactData withMobilePhone(String mobilePhone) {
      this.mobilePhone = mobilePhone;
      return this;
   }

   public ContactData withWorkPhone(String workPhone) {
      this.workPhone = workPhone;
      return this;
   }

   public ContactData withEmail(String email) {
      this.email = email;
      return this;
   }

   public ContactData withBday(String bday) {
      this.bday = bday;
      return this;
   }

   public ContactData withBmonth(String bmonth) {
      this.bmonth = bmonth;
      return this;
   }

   public ContactData withByear(String byear) {
      this.byear = byear;
      return this;
   }

   public ContactData withAddress2(String address2) {
      this.address2 = address2;
      return this;
   }

   public ContactData withNotes(String notes) {
      this.notes = notes;
      return this;
   }

   public String getMiddlename() {
      return middlename;
   }

   public String getLastname() {
      return lastname;
   }

   public String getTitle() {
      return title;
   }

   public String getNickname() {
      return nickname;
   }

   public String getCompany() {
      return company;
   }

   public String getAddress() {
      return address;
   }

   public String getHomePhone() {
      return homePhone;
   }

   public String getMobilePhone() {
      return mobilePhone;
   }

   public String getWorkPhone() {
      return workPhone;
   }

   public String getEmail() {
      return email;
   }

   public String getBday() {
      return bday;
   }

   public String getBmonth() {
      return bmonth;
   }

   public String getByear() {
      return byear;
   }

   public String getAddress2() {
      return address2;
   }

   public String getNotes() {
      return notes;
   }

  /* // контакт может быть ассоциирован не с одной группой, а с нескольски ми
   public String getGroup() {
      return group;
   }
   public ContactData withGroup(String group) {
      this.group = group;
      return this;
   } */

   public Groups getGroups() {
      return new Groups(groups);
   }

   public String getAllPhones() { return allPhones; }

   public ContactData withAllPhones(String allPhones) {
      this.allPhones = allPhones;
      return this;
   }

   public String getAllEmails() {
      return allEmails;
   }

   public ContactData withAllEmails(String allEmails) {
      this.allEmails = allEmails;
      return this;
   }

   public String getEmail2() { return email2;}

   public ContactData withEmail2(String email2) {
      this.email2 = email2;
      return this;
   }

   public String getEmail3() { return email3; }

    public ContactData withEmail3(String email3) {
      this.email3 = email3;
       return this;
   }

   public File getPhoto() { return new File(photo); }

   public ContactData withPhoto(File photo) {
      this.photo = photo.getPath();
      return this;
   }

   public ContactData inGroup(GroupData group) {
      groups.add(group);
      return this;
   }
}
