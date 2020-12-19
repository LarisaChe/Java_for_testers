package ru.stqa.pft.model;

import java.util.Objects;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("geoIP")
public class GeoIP {

   private String country;

   private int state;

   public String getCountry() {
      return country;
   }

    public int getState() {
      return state;
   }

   public void setCountry(String country) {
      this.country = country;
   }

   public void setState(int state) {
      this.state = state;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      GeoIP geoIP = (GeoIP) o;
      return state == geoIP.state && Objects.equals(country, geoIP.country);
   }

   @Override
   public int hashCode() {
      return Objects.hash(country, state);
   }

   @Override
   public String toString() {
      return "GeoIP{" +
             "country='" + country + '\'' +
             ", state=" + state +
             '}';
   }
}
