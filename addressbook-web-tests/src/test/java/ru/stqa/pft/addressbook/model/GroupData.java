package ru.stqa.pft.addressbook.model;

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
}
