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
      ;
      list.add(new Object[] {new GroupData().withName("test V1").withHeader("header V1").withFooter("footer V1")});
      list.add(new Object[] {new GroupData().withName("test V2").withHeader("header V2").withFooter("footer V2")});
      list.add(new Object[] {new GroupData().withName("test V3").withHeader("header V3").withFooter("footer V3")});
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
