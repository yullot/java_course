package qa.pkg.mantis.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import qa.pkg.mantis.model.UserData;

import java.util.List;


public class DBHelper {
  private final SessionFactory sessionFactory;
  private final ApplicationManager app;

  public DBHelper(ApplicationManager app) {
    this.app = app;

    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
  }

  public List<UserData> users() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<UserData> result = session.createQuery("from UserData").list();
    session.getTransaction().commit();
    session.close();
    return result;
  }


//  public ContactData contactById(int id) {
//    Session session = sessionFactory.openSession();
//    session.beginTransaction();
//    ContactData result = (ContactData) session
//            .createQuery(String.format("from ContactData where deprecated ='0000-00-00 00:00:00' and id=%s", id))
//            .getSingleResult();
//    session.getTransaction().commit();
//    session.close();
//    return result;
//  }


}
