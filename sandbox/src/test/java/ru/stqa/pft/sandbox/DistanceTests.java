package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

/**
 * Description.
 *
 * @author Larisa Chernaya
 */

public class DistanceTests {

   @Test
   public void testDistance1() {
      Point p1 = new Point(1, 2);
      Point p2 = new Point(4, 6);
      double len = p1.distance(p2);
      System.out.println(len);
      Assert.assertEquals(len, 5.0);
   }

   @Test
   public void testDistance2() {
      Point p1 = new Point(0, 0);
      Point p2 = new Point(0, 0);
      Assert.assertEquals(p1.distance(p2), 0);
   }

   @Test
   public void testDistance3() {
      Point p1 = new Point(-1, 2);
      Point p2 = new Point(2, -2);
      Assert.assertEquals(p1.distance(p2), 5.0);
   }
}
