package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
   private int id;
   private  String firstname;
   private  String middlename;
   private  String lastname;
   private  String title;
   private  String nickname;
   private  String company;
   private  String address;
   private  String homePhone;
   private  String mobilePhone;
   private  String workPhone;
   private  String email;
   private  String email2;
   private  String email3;
   private  String bday;
   private  String bmonth;
   private  String byear;
   private  String address2;
   private  String notes;
   private String group;
   private String allPhones;
   private String allEmails;

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
             ", lastname='" + lastname + '\'' +
             '}';
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      ContactData that = (ContactData) o;
      return id == that.id &&
             Objects.equals(firstname, that.firstname) &&
             Objects.equals(lastname, that.lastname);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, firstname, lastname);
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

   public ContactData withGroup(String group) {
      this.group = group;
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

   public String getGroup() {
      return group;
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
}
