package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Description.
 *
 * @author lchernaya
 */
public class EquationTests {

   @Test
   public void testEquation0 () {
      Equation equation = new Equation(1, 1, 1);
      Assert.assertEquals(equation.rootNumber(), 0);
   }

   @Test
   public void testEquation1 () {
      Equation equation = new Equation(1, 2, 1);
      Assert.assertEquals(equation.rootNumber(), 1);
   }

   @Test
   public void testEquation2 () {
      Equation equation = new Equation(1, 5, 6);
      Assert.assertEquals(equation.rootNumber(), 2);
   }
}
