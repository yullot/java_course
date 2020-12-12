package qa.pkg.addressbook.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.pkg.addressbook.model.ContactData;
import qa.pkg.addressbook.model.Contacts;
import qa.pkg.addressbook.model.GroupData;

import java.io.File;
import java.util.Collections;
import java.util.HashSet;

public class ContactDeletionFromGroup extends TestBase {
  Logger logger = LoggerFactory.getLogger(ContactDeletionFromGroup.class);

  @Test
  public void ensurePrecondition() {
    /**
     * Check whether any contact exists
     */
    File photo = new File("src/test/resources/photo.jpg");
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.goTo().addNewPage();
      app.contact().createContact(new ContactData().withLastname("Kudrevich").withFirstname("Martha").
              withAddress("Moscow, Lenina str 15").withPhoto(photo));
    }

    /**
     * If no groups -> create a new one
     * Check whether group has assigned
     */
    if (app.db().groups().size() == 0) {
      app.goTo().groupsPage();
      app.group().createGroup(new GroupData().withGroupName("testGroupNew").withHeader("header").withFooter("footer"));
    }
    GroupData selectedGroup = app.db().groups().iterator().next();
    logger.info("***Selected group in precondition " + selectedGroup);
    Contacts contactsOfGroup = app.db().groupSingle(selectedGroup.getId()).getContacts();
    logger.info("***Contacts og group " + contactsOfGroup);

    if (contactsOfGroup.size() == 0) {

      /*Session session = app.db().getSession();
      Transaction tr = session.beginTransaction();
      selectedGroup.setContacts(app.db().contacts(2));
      selectedGroup.setDeprecated("0000-00-00 00:00:00");
      session.save(selectedGroup);
      tr.commit();
      session.close();*/
    }
  }
}

