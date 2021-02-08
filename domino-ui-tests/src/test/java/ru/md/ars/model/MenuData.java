package ru.md.ars.model;

public class MenuData {
   private int id;
   private String nameRU;
   private String nameEN;
   private String url;
   private int level;
   private String inMenuGroup;
   private int orderInMenuGroup;
   private boolean testable;

   public boolean isTestable() {
      return testable;
   }

   public MenuData withTestable(boolean testable) {
      this.testable = testable;
      return this;
   }

   public int getLevel() {
      return level;
   }

   public MenuData withLevel(int level) {
      this.level = level;
      return this;
   }

   public String getInMenuGroup() {
      return inMenuGroup;
   }

    public MenuData withInMenuGroup(String inMenuGroup) {
      this.inMenuGroup = inMenuGroup;
       return this;
   }

   public int getOrderInMenuGroup() {
      return orderInMenuGroup;
   }

   public MenuData withOrderInMenuGroup(int orderInMenuGroup) {
      this.orderInMenuGroup = orderInMenuGroup;
      return this;
   }

   public int getId() {
      return id;
   }

    public MenuData withId(int id) {
      this.id = id;
       return this;
   }

   public String getUrl() {
      return url;
   }

   public MenuData withUrl(String url) {
      this.url = url;
      return this;
   }

    public String getNameRU() {
      return nameRU;
   }

    public MenuData withNameRU(String nameRU) {
      this.nameRU = nameRU;
       return this;
   }

    public String getNameEN() {
      return nameEN;
    }

    public MenuData withNameEN(String nameEN) {
      this.nameEN = nameEN;
      return this;
   }

}

