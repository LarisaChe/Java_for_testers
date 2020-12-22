package ru.stqa.pft.addressbook2.appmanager;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import ru.stqa.pft.addressbook2.model.ContactData;
import ru.stqa.pft.addressbook2.model.Contacts;
import ru.stqa.pft.addressbook2.model.GroupData;
import ru.stqa.pft.addressbook2.model.Groups;

/**
 * Description.
 *
 * @author lchernaya
 */
public class DbHelper {

   private SessionFactory sessionFactory;

   public DbHelper() {
      final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
           .configure() // configures settings from hibernate.cfg.xml
           .build();
         sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
   }

   public Groups groups () {
      Session session = sessionFactory.openSession();
      session.beginTransaction();
      List<GroupData> result = session.createQuery("from GroupData" ).list();
      session.getTransaction().commit();
      session.close();
      //System.out.println("groups (result): ");
      //System.out.println(result);
      return new Groups(result);
   }

   public Contacts contacts() {
      Session session = sessionFactory.openSession();
      session.beginTransaction();
      List<ContactData> result = session.createQuery("from ContactData where deprecated='0000-00-00'" ).list();
      session.getTransaction().commit();
      session.close();
      //System.out.println("contacts (result): ");
      //System.out.println(result);
      return new Contacts(result);
   }

   public ContactData contact(int id) {
      Session session = sessionFactory.openSession();
      session.beginTransaction();
      List<ContactData> result = session.createQuery(String.format("from ContactData where deprecated='0000-00-00' and id=%s", id) ).list();
      session.getTransaction().commit();
      session.close();
      //System.out.println("contacts (result): ");
      //System.out.println(result);
      return new Contacts(result).iterator().next();
   }

   public GroupData group (int id) {
      Session session = sessionFactory.openSession();
      session.beginTransaction();
      List<GroupData> result = session.createQuery(String.format("from GroupData where group_id=%s", id) ).list();
      session.getTransaction().commit();
      session.close();
      //System.out.println("groups (result): ");
      //System.out.println(result);
      return new Groups(result).iterator().next();
   }
}
