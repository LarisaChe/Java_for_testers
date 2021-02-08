package ru.md.ars.model;

import java.util.Objects;

import com.google.gson.annotations.Expose;

public class AttributeData {
   @Expose
   private String key;
   @Expose
   private String nameRu;
   @Expose
   private String nameEn;
   @Expose
   private String type;
   @Expose
   private boolean filter;
   @Expose
   private String ref;
   @Expose
   private boolean multilang;
   @Expose
   private boolean nullable;

   @Override
   public String toString() {
      return "AttributeData{" +
             "key='" + key + '\'' +
             ", nameRu='" + nameRu + '\'' +
             ", nameEn='" + nameEn + '\'' +
             ", type='" + type + '\'' +
             ", filter=" + filter +
             ", ref='" + ref + '\'' +
             ", multilang=" + multilang +
             ", nullable=" + nullable +
             '}';
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      AttributeData that = (AttributeData) o;
      return filter == that.filter && multilang == that.multilang && nullable == that.nullable && Objects.equals(key, that.key)
             && Objects.equals(nameRu, that.nameRu) && Objects.equals(nameEn, that.nameEn) && Objects
                  .equals(type, that.type) && Objects.equals(ref, that.ref);
   }

   @Override
   public int hashCode() {
      return Objects.hash(key, nameRu, nameEn, type, filter, ref, multilang, nullable);
   }

   public boolean isNullable() {
      return nullable;
   }

   public AttributeData withNullable(boolean nullable) {
      this.nullable = nullable;
      return this;
   }

   public boolean isMultilang() {
      return multilang;
   }

   public AttributeData withMultilang(boolean multilang) {
      this.multilang = multilang;
      return this;
   }

   public String getKey() {
      return key;
   }

   public AttributeData withKey(String key) {
      this.key = key;
      return this;
   }

   public String getNameRu() {
      return nameRu;
   }

   public AttributeData withNameRu(String nameRu) {
      this.nameRu = nameRu;
      return this;
   }

   public String getNameEn() {
      return nameEn;
   }

   public AttributeData withNameEn(String nameEn) {
      this.nameEn = nameEn;
      return this;
   }

   public boolean isFilter() {
      return filter;
   }

   public AttributeData withFilter(boolean filter) {
      this.filter = filter;
      return this;
   }

   public String getType() {
      return type;
   }

   public AttributeData withType(String type) {
      this.type = type;
      return this;
   }
   public String getRef() {
      return ref;
   }

   public AttributeData withRef(String ref) {
      this.ref = ref;
      return this;
   }

}
