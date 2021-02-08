package ru.md.ars.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.md.ars.model.AttributeData;
import ru.md.ars.model.EntityData;

public class EntityDataGenerator {
// строка из конфигурации
// -f src/resource/group.xml -d xml -c 4
//  D:\Users\lchernaya\Documents\GitHub\Java_for_testers\addressbook-web-tests

   //@Parameter(names = "-c",  description = "Group count")
   public int count = 2;

   //@Parameter (names = "-f", description = "Target file")
   public String file = "entities.json";

   //@Parameter (names = "-d", description = "Data format xml or csv or json")
   public String format = "json";

   private void run () throws IOException {
      List<EntityData> entities = generateEntities(count);
      if (format.equals("csv")) {
         saveAsCSV(entities, new File(file));
      } else if (format.equals("xml")) {
         saveAsXML(entities, new File(file));
      } else if (format.equals("json")) {
         saveAsJSON(entities, new File(file));
      } else {
         System.out.println("Unrecognized format: "+format);
      }
   }

   private void saveAsJSON(List<EntityData> entities, File file) throws IOException {
     // Gson gson = new Gson();
      Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
      String json = gson.toJson(entities);
      try (Writer writer = new FileWriter(file)) {
         writer.write(json);
      }
   }

   private void saveAsXML(List<EntityData> entities, File file) throws IOException {
      XStream xstream = new XStream();
      //xstream.alias("group", GroupData.class);
      xstream.processAnnotations(EntityData.class);
      String xml = xstream.toXML(entities);
      try (Writer writer = new FileWriter(file)) {
         writer.write(xml);
      }
   }

   public static void main(String[] args) throws IOException {
      EntityDataGenerator generator = new EntityDataGenerator();
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

   private void saveAsCSV(List<EntityData> entities, File file) throws IOException {
      //System.out.println(new File(".").getAbsolutePath());
      try (Writer writer = new FileWriter(file)) {
         for (EntityData entity : entities) {
            writer.write(String.format("%s;%s;%s;%s\n", entity.getKey(), entity.getNameRu(), entity.getNameEn(), entity.isHi()));
         }
      }
   }

   private  List<EntityData> generateEntities(int count) {
      //List<AttributeData> attributes = generateAttributes(count);
      Set<AttributeData> attributes = generateAttributes(count+1); //new HashSet<AttributeData>();
      List<EntityData> entities = new ArrayList<EntityData>();
      for (int i=0; i<count; i++) {
         entities.add(new EntityData().withKey(String.format("Dict%sG%s", format, i))
                                    .withNameRu(String.format("NameRu %s G %s", format, i))
                                    .withNameEn(String.format("NameEn %s G %s", format, i))
                                    .withHi(true)
                                    .withAttributes(attributes)
         );
      }
      System.out.println(" --------- entities ------------");
      System.out.println(entities);
      return entities;
   }

   //private  List<AttributeData> generateAttributes(int count) {
   private Set<AttributeData> generateAttributes(int count) {
      //List<AttributeData> attributes = new ArrayList<AttributeData>();
      Set<AttributeData> attributes = new HashSet<AttributeData>();
      for (int i=0; i<count; i++) {
         attributes.add(new AttributeData().withKey(String.format("str%s",i))
                                      .withNameRu(String.format("strRu%s",i))
                                      .withNameEn(String.format("strEN%s",i))
                                      .withType("string")
                                      .withFilter(true));
      }
      System.out.println("attributes.size(): "+attributes.size());
      System.out.println(attributes);
      return attributes;
   }
}
