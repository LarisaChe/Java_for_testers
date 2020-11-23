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
   private  String homephone;
   private  String mobile;
   private  String email;
   private  String bday;
   private  String bmonth;
   private  String byear;
   private  String address2;
   private  String notes;
   private String group;

   /*public ContactData(int id, String firstname, String lastname, String address) {
      this.id = id;
      this.firstname = firstname;
      this.lastname = lastname;
      this.address = address;
      this.middlename = null;
      this.title = null;
      this.nickname = null;
      this.company = null;
      this.homephone = null;
      this.mobile = null;
      this.email = null;
      this.bday = null;
      this.bmonth = null;
      this.byear = null;
      this.address2 = null;
      this.notes = null;
      this.group = null;
   }
*/
   /*public ContactData(String firstname, String middlename, String lastname, String title, String nickname,
                      String company, String address,
                      String homephone, String mobile, String email, String bday, String bmonth,
                      String byear, String address2, String notes, String group) {
      this.id = Integer.MAX_VALUE;
      this.firstname = firstname;
      this.middlename = middlename;
      this.lastname = lastname;
      this.title = title;
      this.nickname = nickname;
      this.company = company;
      this.address = address;
      this.homephone = homephone;
      this.mobile = mobile;
      this.email = email;
      this.bday = bday;
      this.bmonth = bmonth;
      this.byear = byear;
      this.address2 = address2;
      this.notes = notes;
      this.group = group;
   } */

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
      return Objects.equals(firstname, that.firstname) &&
             Objects.equals(lastname, that.lastname);
   }

   @Override
   public int hashCode() {
      return Objects.hash(firstname, lastname);
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

   public ContactData withHomephone(String homephone) {
      this.homephone = homephone;
      return this;
   }

   public ContactData withMobile(String mobile) {
      this.mobile = mobile;
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

   public String getHomephone() {
      return homephone;
   }

   public String getMobile() {
      return mobile;
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

}
