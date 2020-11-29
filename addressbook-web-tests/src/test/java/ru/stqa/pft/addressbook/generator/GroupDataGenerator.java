package ru.stqa.pft.addressbook.generator;

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
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupDataGenerator {
// строка из конфигурации
// -f src/resource/group.xml -d xml -c 4
//  D:\Users\lchernaya\Documents\GitHub\Java_for_testers\addressbook-web-tests

   @Parameter(names = "-c",  description = "Group count")
   public int count;

   @Parameter (names = "-f", description = "Target file")
   public String file;

   @Parameter (names = "-d", description = "Data format xml or csv or json")
   public String format;

   private void run () throws IOException {
      List<GroupData> groups = generateGroups(count);
      if (format.equals("csv")) {
         saveAsCSV(groups, new File(file));
      } else if (format.equals("xml")) {
         saveAsXML(groups, new File(file));
      } else if (format.equals("json")) {
         saveAsJSON(groups, new File(file));
      } else {
         System.out.println("Unrecognized format: "+format);
      }
   }

   private void saveAsJSON(List<GroupData> groups, File file) throws IOException {
     // Gson gson = new Gson();
      Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
      String json = gson.toJson(groups);
      try (Writer writer = new FileWriter(file)) {
         writer.write(json);
      }
   }

   private void saveAsXML(List<GroupData> groups, File file) throws IOException {
      XStream xstream = new XStream();
      //xstream.alias("group", GroupData.class);
      xstream.processAnnotations(GroupData.class);
      String xml = xstream.toXML(groups);
      try (Writer writer = new FileWriter(file)) {
         writer.write(xml);
      }
   }

   public static void main(String[] args) throws IOException {
      GroupDataGenerator generator = new GroupDataGenerator();
      JCommander jCommander = new JCommander(generator);
      try {
         jCommander.parse(args);
      }
      catch (ParameterException ex) {
         jCommander.usage();
         return;
      }
      generator.run();

      /*int count = Integer.parseInt(args[0]);
      File file = new File(args[1]);
      List<GroupData> groups = generateGroups(count);
      save(groups, file); */
   }

   private void saveAsCSV(List<GroupData> groups, File file) throws IOException {
      //System.out.println(new File(".").getAbsolutePath());
      try (Writer writer = new FileWriter(file)) {
         for (GroupData group : groups) {
            writer.write(String.format("%s;%s;%s\n", group.getName(), group.getHeader(), group.getFooter()));
         }
      }
   }

   private  List<GroupData> generateGroups(int count) {
      List<GroupData> groups = new ArrayList<GroupData>();
      for (int i=0; i<count; i++) {
         groups.add(new GroupData().withName(String.format("Test %s G%s", format, i))
                                   .withHeader(String.format("Header %s G%s", format, i))
                                   .withFooter(String.format("Footer %s G%s", format, i)));
      }
      return groups;
   }

}
