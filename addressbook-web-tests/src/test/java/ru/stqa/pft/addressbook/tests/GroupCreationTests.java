package ru.stqa.pft.addressbook.tests;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

public class GroupCreationTests extends TestBase {

   @DataProvider
   public Iterator<Object[]> validGroups() throws IOException {
      if (app.formatDataForGroup().equals("xml"))   return validGroupsFromXML();
       else if (app.formatDataForGroup().equals("json"))   return validGroupsFromJSON();
       else if (app.formatDataForGroup().equals("csv"))   return validGroupsFromCSV();
       else System.out.println("Неправильно указан формат данных для групп");
       return null;
   }

   @DataProvider
   public Iterator<Object[]> validGroupsFromCSV() throws IOException {
      List<Object[]> list = new ArrayList<Object[]>();
     try(BufferedReader reader = new BufferedReader(new FileReader(new File(app.fileDataForGroup())))) { // "src/test/resources/groups2.csv"
        String line = reader.readLine();
        while (line != null) {
           String[] split = line.split(";");
           list.add(new Object[] {new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
           line = reader.readLine();
        }
        return list.iterator();
     }
   }

   @DataProvider
   public Iterator<Object[]> validGroupsFromJSON() throws IOException {
      try (BufferedReader reader = new BufferedReader(new FileReader(new File(app.fileDataForGroup())))) { //"src/test/resources/groups.json"
         String json = "";
         String line = reader.readLine();
         while (line != null) {
             json += line;
            line = reader.readLine();
         }
         Gson gson = new Gson();
         List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>() {}.getType()); // это сложный способ только для списков объектов
         return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
      }
   }

   @DataProvider
   public Iterator<Object[]> validGroupsFromXML() throws IOException {
     try (BufferedReader reader = new BufferedReader(new FileReader(new File(app.fileDataForGroup())))) { //"src/test/resources/groups.xml"
        String xml = "";
        String line = reader.readLine();
        while (line != null) {
           xml += line;
           line = reader.readLine();
        }
        XStream xStream = new XStream();
        xStream.processAnnotations(GroupData.class);
        List<GroupData> groups = (List<GroupData>) xStream.fromXML(xml);
        return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
     }
   }

   @Test (dataProvider = "validGroups") // (dataProvider = "validGroupsFromXML")
   public void testGroupCreation(GroupData group) throws Exception {
      app.goTo().groupPage();
      Groups before = app.group().all();
      app.group().create(group);
      assertThat(app.group().count(), equalTo(before.size() + 1));
      Groups after = app.group().all();
      assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
      //app.logout();
   }

   @Test
   public void testBadGroupCreation() throws Exception {
      app.goTo().groupPage();
      Groups before = app.group().all();
      GroupData group = new GroupData().withName("Te'st").withHeader("Test header Cr1").withFooter("Test footer Cr1");
      app.group().create(group);
      assertThat(app.group().count(), equalTo(before.size()));
      Groups after = app.group().all();
      assertThat(after, equalTo(before));
   }
}
