package ru.md.ars.model;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.google.gson.annotations.Expose;

public class EntityData {
   @Expose
   private String key;
   @Expose
   private String nameRu;
   @Expose
   private String nameEn;
   @Expose
   private String commentRu;
   @Expose
   private String commentEn;
   @Expose
   private boolean hi;
   @Expose
   private Set<AttributeData> attributes = new HashSet<AttributeData>();
   //private List<AttributeData> attributes = new List<AttributeData>();

   public Attributes getAttributes() {
      return new Attributes(attributes);
   }

   public EntityData withAttributes(Set<AttributeData> attributes) {
      this.attributes = attributes;
      return this;
   }

   public String getKey() {
      return key;
   }

   public EntityData withKey(String key) {
      this.key = key;
      return this;
   }

   public String getNameRu() {
      return nameRu;
   }

   public EntityData withNameRu(String nameRu) {
      this.nameRu = nameRu;
      return this;
   }

   public String getNameEn() {
      return nameEn;
   }

   public EntityData withNameEn(String nameEn) {
      this.nameEn = nameEn;
      return this;
   }

   public String getCommentRu() {
      return commentRu;
   }

   public EntityData withCommentRu(String commentRu) {
      this.commentRu = commentRu;
      return this;
   }

   public String getCommentEn() {
      return commentEn;
   }

   public EntityData withCommentEn(String commentEn) {
      this.commentEn = commentEn;
      return this;
   }

   public boolean isHi() {
      return hi;
   }

   public EntityData withHi(boolean hi) {
      this.hi = hi;
      return this;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      EntityData that = (EntityData) o;
      return hi == that.hi && Objects.equals(key, that.key) && Objects.equals(nameRu, that.nameRu) && Objects
           .equals(nameEn, that.nameEn) && Objects.equals(commentRu, that.commentRu) && Objects.equals(commentEn, that.commentEn)
             && Objects.equals(attributes, that.attributes);
   }

   @Override
   public int hashCode() {
      return Objects.hash(key, nameRu, nameEn, commentRu, commentEn, hi, attributes);
   }

   @Override
   public String toString() {
      return "EntityData{" +
             "key='" + key + '\'' +
             ", nameRu='" + nameRu + '\'' +
             ", nameEn='" + nameEn + '\'' +
             ", commentRu='" + commentRu + '\'' +
             ", commentEn='" + commentEn + '\'' +
             ", hi=" + hi +
             ", attributes=" + attributes +
             '}';
   }
}
