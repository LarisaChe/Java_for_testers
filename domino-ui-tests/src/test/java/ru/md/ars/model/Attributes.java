package ru.md.ars.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.ForwardingSet;

public class Attributes extends ForwardingSet<AttributeData> {

   private Set<AttributeData> delegate;

   public Attributes(Attributes attributes) {
      this.delegate = new HashSet<AttributeData>(attributes.delegate);
   }

   public Attributes() {
      this.delegate = new HashSet<AttributeData>();
   }

   public Attributes(Collection<AttributeData> attributes) {
      this.delegate = new HashSet<AttributeData>(attributes);
   }

   @Override
   protected Set<AttributeData> delegate() {
      return delegate;
   }

   public Attributes withAdded(AttributeData attribute) {
      Attributes attributes = new Attributes(this);
      attributes.add(attribute);
      return attributes;
   }

}
