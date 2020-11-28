package ru.stqa.pft.addressbook.tests;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

public class GroupCreationTests extends TestBase {

   @DataProvider
   public Iterator<Object[]> validGroups() {
      List<Object[]> list = new ArrayList<Object[]>();
      list.add(new Object[] {"test 'V1","header V1","footer V1"});
      list.add(new Object[] {"test V2","header V2","footer V2"});
      list.add(new Object[] {"test V3","header V3","footer V3"});
      return list.iterator();
   }

   @Test (dataProvider = "validGroups")
   public void testGroupCreation(String name, String header, String footer) throws Exception {
      app.goTo().groupPage();

      Groups before = app.group().all();
      GroupData group = new GroupData().withName(name).withHeader(header).withFooter(footer);
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
