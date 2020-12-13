package qa.pkg.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import qa.pkg.addressbook.model.ContactData;
import qa.pkg.addressbook.model.Contacts;
import qa.pkg.addressbook.model.GroupData;
import qa.pkg.addressbook.model.Groups;

import java.util.List;

public class DBHelper {
  private final SessionFactory sessionFactory;

  public DBHelper() {
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
  }

  public Groups groups() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<GroupData> result = session.createQuery("from GroupData").list();
    session.getTransaction().commit();
    session.close();
    return new Groups(result);
  }


  public Contacts contacts() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<ContactData> result = session.createQuery("from ContactData where deprecated < '1970-01-01 01:00:00'").list();
    session.getTransaction().commit();
    session.close();
    return new Contacts(result);
  }

  public Session getSession() {
    return sessionFactory.openSession();
  }

 /* public Contacts contacts(Integer max) {
    List<ContactData> result = new ArrayList<>();
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    if (max == null) {
      return contacts();
    } else {
      result = session.createQuery("from ContactData where deprecated ='0000-00-00 00:00:00'").setMaxResults(max).list();
    }
    session.getTransaction().commit();
    session.close();
    return new Contacts(result);
  }*/

  public ContactData contactById(int id) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    ContactData result = (ContactData) session
            .createQuery(String.format("from ContactData where deprecated ='0000-00-00' and id=%s", id))
            .getSingleResult();
    session.getTransaction().commit();
    session.close();
    return result;
  }

  public GroupData groupById(int id) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    GroupData result = (GroupData) session.createQuery(String.format("from GroupData where id=%s", id)).getSingleResult();
    session.getTransaction().commit();
    session.close();
    return result;
  }


}
