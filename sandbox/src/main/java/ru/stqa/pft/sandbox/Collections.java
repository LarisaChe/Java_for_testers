package ru.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Description.
 *
 * @author lchernaya
 */
public class Collections {
   public static void main (String[] args) {
      /*String[] langs = new String[4];
      langs[0] = "Java";
      langs[1] = "C#";
      langs[2] = "Python";
      langs[3] = "PHP"; */
      String[] langs ={"Java", "C#", "Python", "PHP"}; // размер массива задается в момент объявления и потом не меняется

      /*for (int i= 0; i<langs.length; i++) {
         System.out.println("Я хочу выучить "+ langs[i]);
      } */
      for (String l : langs) {
         System.out.println("Я хочу выучить "+ l);
      }

      List<String> languages = new ArrayList<String>();  // размер списка меняется динамически
      languages.add("Java");
      languages.add("C#");
      languages.add("Python");
      languages.add("PHP");
      for (String l : languages) {
         System.out.println("Я хочу выучить язык "+ l);
      }

      List<String> lingvas = Arrays.asList("Java", "C#", "Python", "PHP");
      for (String l : lingvas) {
         System.out.println("Хочу выучить: "+ l);
      }
     // для списка цикл фор отличается от цикла для массива
      for (int i=0; i< lingvas.size(); i++) {
         System.out.println("Выучу - "+ lingvas.get(i));
      }

      // необязательно указывать в списке тип
      List lingvas2 = Arrays.asList("Java", "C#", "Python", "PHP");
      for (Object  l : lingvas2) {
         System.out.println("Study  "+ l);
      }
      //Заключение: Массивы используют очень редко, почти не используются,
      // исключение это массив параметров метода main
      // в основном используются списки
   }
}
