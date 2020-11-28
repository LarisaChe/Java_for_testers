package ru.stqa.pft.addressbook.tests;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

public class GroupCreationTests extends TestBase {

   @DataProvider
   public Iterator<Object[]> validGroups() throws IOException {
      List<Object[]> list = new ArrayList<Object[]>();
      //list.add(new Object[] {new GroupData().withName("test V1").withHeader("header V1").withFooter("footer V1")});
      BufferedReader reader = new BufferedReader(new FileReader(new File("src/resource/groups2.csv")));
      String line = reader.readLine();
      while (line != null) {
         String[] split = line.split(";");
         list.add(new Object[] {new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
         line = reader.readLine();
      }
      return list.iterator();
   }

   @Test (dataProvider = "validGroups")
   public void testGroupCreation(GroupData group) throws Exception {
      app.goTo().groupPage();

      Groups before = app.group().all();
      //GroupData group = new GroupData().withName(name).withHeader(header).withFooter(footer);
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
