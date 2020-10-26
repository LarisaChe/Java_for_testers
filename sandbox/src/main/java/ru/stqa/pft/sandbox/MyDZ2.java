package ru.stqa.pft.sandbox;

/**
 * Description.
 *
 * @author Larisa Chernaya
 */
public class MyDZ2 {

   public static void main(String[] args) {
      Point p1 = new Point(1, 2);
      Point p2 = new Point(4, 6);
      System.out.println("Расстояние между точками (" + p1.x + ", " + p1.y + ") и (" + p2.x + ", " + p2.y + ") = " + p1.distance(p2));
   }

}


