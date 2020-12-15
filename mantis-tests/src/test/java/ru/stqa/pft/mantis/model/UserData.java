package ru.stqa.pft.mantis.model;


import java.util.Objects;

public class UserData {

   private int id;
   private String login;
   private  String name;
   private  String email;
   private  String role;
   private boolean active;

   public UserData(int id, String login, String name, String email, String role, boolean active) {
   }

   public int getId() {
      return id;
   }

   public UserData withId(int id) {
      this.id = id;
      return this;
   }

   public String getLogin() {
      return login;
   }

   public UserData withUser(String login) {
      this.login = login;
      return this;
   }

   public String getName() {
      return name;
   }

   public UserData withName (String name) {
      this.name = name;
      return this;
   }

   public String getRole() {
      return role;
   }

   public UserData withRole(String role) {
      this.role = role;
      return this;
   }

   public boolean getActive() {
      return active;
   }

   public UserData withActive(boolean active) {
      this.active = active;
      return this;
   }

   public String getEmail() {
      return email;
   }

   public UserData withEmail(String email) {
      this.email = email;
      return this;
   }

   @Override
   public String toString() {
      return "UserData{" +
             "user='" + login + '\'' +
             ", name='" + name + '\'' +
             ", role='" + role + '\'' +
             ", email='" + email + '\'' +
             ", active=" + active +
             '}';
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      UserData userData = (UserData) o;
      return active == userData.active && Objects.equals(login, userData.login) && Objects.equals(name, userData.name)
             && Objects.equals(role, userData.role) && Objects.equals(email, userData.email);
   }

   @Override
   public int hashCode() {
      return Objects.hash(login, name, role, email, active);
   }
}
