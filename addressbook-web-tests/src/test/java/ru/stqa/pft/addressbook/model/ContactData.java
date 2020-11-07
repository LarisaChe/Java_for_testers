package ru.stqa.pft.addressbook.model;

public class ContactData {

   private final String firstname;
   private final String middlename;
   private final String lastname;
   private final String title;
   private final String nickname;
   private final String company;
   private final String address;
   private final String homephone;
   private final String mobile;
   private final String email;
   private final String bday;
   private final String bmonth;
   private final String byear;
   private final String address2;
   private final String notes;
   private String group;
   private boolean creation;

   public ContactData(String firstname, String middlename, String lastname, String title, String nickname,
                      String company, String address,
                      String homephone, String mobile, String email, String bday, String bmonth,
                      String byear, String address2, String notes, String group, boolean creation) {
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
      this.creation = creation;
   }

   /**
    * Returns .
    *
    * @return
    */
   public String getFirstname() {
      return firstname;
   }

   /**
    * Returns .
    *
    * @return
    */
   public String getMiddlename() {
      return middlename;
   }

   /**
    * Returns .
    *
    * @return
    */
   public String getLastname() {
      return lastname;
   }

   /**
    * Returns .
    *
    * @return
    */
   public String getTitle() {
      return title;
   }

   /**
    * Returns .
    *
    * @return
    */
   public String getNickname() {
      return nickname;
   }

   /**
    * Returns .
    *
    * @return
    */
   public String getCompany() {
      return company;
   }

   /**
    * Returns .
    *
    * @return
    */
   public String getAddress() {
      return address;
   }

   /**
    * Returns .
    *
    * @return
    */
   public String getHomephone() {
      return homephone;
   }

   /**
    * Returns .
    *
    * @return
    */
   public String getMobile() {
      return mobile;
   }

   /**
    * Returns .
    *
    * @return
    */
   public String getEmail() {
      return email;
   }

   /**
    * Returns .
    *
    * @return
    */
   public String getBday() {
      return bday;
   }

   /**
    * Returns .
    *
    * @return
    */
   public String getBmonth() {
      return bmonth;
   }

   /**
    * Returns .
    *
    * @return
    */
   public String getByear() {
      return byear;
   }

   /**
    * Returns .
    *
    * @return
    */
   public String getAddress2() {
      return address2;
   }

   /**
    * Returns .
    *
    * @return
    */
   public String getNotes() {
      return notes;
   }

   /**
    * Returns .
    *
    * @return
    */
   public String getGroup() {
      return group;
   }

   /**
    * Returns .
    *
    * @return
    */
   public boolean isCreation() {
      return creation;
   }
}
