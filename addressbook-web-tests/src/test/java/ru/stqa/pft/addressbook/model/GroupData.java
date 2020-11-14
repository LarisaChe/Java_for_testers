package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class GroupData {
   private int id;
   private final String name;
   private final String header;
   private final String footer;

   public GroupData(int id, String name, String header, String footer) {
      this.id = id;
      this.name = name;
      this.header = header;
      this.footer = footer;
   }
   public GroupData( String name, String header, String footer) {
      this.id = 0;
      this.name = name;
      this.header = header;
      this.footer = footer;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      GroupData groupData = (GroupData) o;
      return Objects.equals(id, groupData.id) &&
             Objects.equals(name, groupData.name);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, name);
   }

   @Override
   public String toString() {
      return "GroupData{" +
             "id='" + id + '\'' +
             ", name='" + name + '\'' +
             '}';
   }

   public int getId() {
      return id;
   }

   /**
    * Sets .
    *
    * @param id
    */
   public void setId(int id) {
      this.id = id;
   }

   /**
    * Returns .
    *
    * @return
    */
   public String getName() {
      return name;
   }

   /**
    * Returns .
    *
    * @return
    */
   public String getHeader() {
      return header;
   }

   /**
    * Returns .
    *
    * @return
    */
   public String getFooter() {
      return footer;
   }

}
