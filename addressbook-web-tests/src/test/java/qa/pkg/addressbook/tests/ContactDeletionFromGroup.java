package qa.pkg.addressbook.tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.pkg.addressbook.model.ContactData;
import qa.pkg.addressbook.model.Contacts;
import qa.pkg.addressbook.model.GroupData;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionFromGroup extends TestBase {

  @BeforeMethod
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
     */
    if (app.db().groups().size() == 0) {
      app.goTo().groupsPage();
      app.group().createGroup(new GroupData().withGroupName("testGroupNew2").withHeader("header2").withFooter("footer2"));
    }
    /**
     * Get list of Contacts for selectedGroup
     * if no contact-> add contact to group
     */

    GroupData selectedGroup = app.db().groups().iterator().next();
    logger.info("***Selected group in precondition " + selectedGroup);
    Contacts contactsOfGroup = app.db().groupById(selectedGroup.getId()).getContacts();
    logger.info("***Contacts oF group " + contactsOfGroup);

    if (contactsOfGroup.size() == 0) {
      app.goTo().homePage();
      ContactData selectedContact = app.db().contacts().iterator().next();
      app.contact().addGroupTo(selectedContact, selectedGroup);
      logger.info("***Contacts of group after adding " + app.db().groupById(selectedGroup.getId()).getContacts());
    }
  }

  @Test
  public void testContactDeletionFromGroup() {
    app.goTo().homePage();
    GroupData selectedGroup = app.db().groups().iterator().next();
    Contacts beforeContactsListOfGroup = app.db().groupById(selectedGroup.getId()).getContacts();
    logger.info("!!!Contacts List Of Group " + selectedGroup + " is " + beforeContactsListOfGroup);
    ContactData selectedContact = beforeContactsListOfGroup.iterator().next();

    app.contact().deleteContactFromGroup(selectedContact,selectedGroup);

    Contacts afterContactsListOfGroup = app.db().groupById(selectedGroup.getId()).getContacts();
    logger.info("!!!Contacts List Of Group " + selectedGroup + " is " + afterContactsListOfGroup);
    assertThat(afterContactsListOfGroup, equalTo(beforeContactsListOfGroup.without(selectedContact)));
  }
}

