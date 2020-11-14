package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class GroupData {

   private final String name;
   private final String header;
   private final String footer;

   public GroupData(String name, String header, String footer) {
      this.name = name;
      this.header = header;
      this.footer = footer;
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

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      GroupData groupData = (GroupData) o;
      return Objects.equals(name, groupData.name);
   }

   @Override
   public int hashCode() {
      return Objects.hash(name);
   }

   @Override
   public String toString() {
      return "GroupData{" +
             "name='" + name + '\'' +
             //", header='" + header + '\'' +
             //", footer='" + footer + '\'' +
             '}';
   }
}
