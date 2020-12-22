package ru.stqa.pft.addressbook2.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.stqa.pft.addressbook2.model.ContactData;

public class ContactDataGenerator {
// параметры из конфигурации
// -f src/resource/group.xml -d xml -c 4
// D:\Users\lchernaya\Documents\GitHub\Java_for_testers\addressbook-web-tests
   @Parameter(names = "-c",  description = "Contact count")
   public int count;

   @Parameter (names = "-f", description = "Target file")
   public String file;

   @Parameter (names = "-d", description = "Data format xml or csv or json")
   public String format;

   public static void main(String[] args) throws IOException {
      ContactDataGenerator generator = new ContactDataGenerator();
      JCommander jCommander = new JCommander(generator);
      try {
         jCommander.parse(args);
      }
      catch (ParameterException ex) {
         jCommander.usage();
         return;
      }
      generator.run();
   }

   private void run () throws IOException {
      List<ContactData> contacts = generateContacts(count);
      /* if (format.equals("csv")) {
         saveAsCSV(groups, new File(file));
      } else if (format.equals("xml")) {
         saveAsXML(groups, new File(file));
      } else */if (format.equals("json")) {
         saveAsJSON(contacts, new File(file));
      } else {
         System.out.println("Unrecognized format: " + format);
      }
   }

   private void saveAsJSON(List<ContactData> contacts , File file) throws IOException {
      //System.out.println(new File(".").getAbsolutePath());
      Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
      String json = gson.toJson(contacts);
      try (Writer writer = new FileWriter(file)) {
         writer.write(json);
      }
      //writer.close();
   }

   private  List<ContactData> generateContacts(int count) {
      //System.out.println("count: "+count);
      List<ContactData> contacts = new ArrayList<ContactData>();
      for (int i=0; i<count; i++) {
         contacts.add(new ContactData().withFirstname(String.format("Firstname test %s G%s", format, i))
                                   .withLastname(String.format("Lastname test %s G%s", format, i))
                                   .withMiddlename(String.format("Middlename test %s G%s", format, i))
                                   .withNickname(String.format("Nickname test %s G%s", format, i))
                                   .withAddress(String.format("Address test %s G%s", format, i))
                                   .withEmail(String.format("test_%s_%s@email.ru", format, i))
                                   .withMobilePhone(String.format("+7(900)500505%s", i))
                                   );
      }
      return contacts;
   }

}
