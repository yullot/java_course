package qa.pkg.addressbook.tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.pkg.addressbook.model.ContactData;
import qa.pkg.addressbook.model.GroupData;
import qa.pkg.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAdditionToGroup extends TestBase {

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
     * else comparing groupList of the selectedContact with general groupList
     * if they are equal create a new group
     */
    if (app.db().groups().size() == 0) {
      app.goTo().groupsPage();
      app.group().createGroup(new GroupData().withGroupName("testGroupNew").withHeader("header").withFooter("footer"));
    } else {
      ContactData selectedContact = app.db().contacts().iterator().next();
      logger.info("***Selected contact in precondition " + selectedContact);
      Groups groupsOfContact = app.db().contactById(selectedContact.getContactId()).getGroups();
      logger.info("***Groups of contact " + groupsOfContact);
      Groups groups = app.db().groups();
      if (groups.equals(groupsOfContact)) {
        logger.info("***Oops lists are equal");
        app.goTo().groupsPage();
        app.group().createGroup(new GroupData().withGroupName("testGroupAdditional").withHeader("header").withFooter("footer"));
      }
    }
  }

  @Test
  public void testContactAddittionToGroup() {
    Groups groups = app.db().groups();
    app.goTo().homePage();
    ContactData selectedContact = app.db().contacts().iterator().next();
    logger.info("!!!Selected contact " + selectedContact);
    Groups beforeGroupListOfContact = app.db().contactById(selectedContact.getContactId()).getGroups();
    GroupData addedGroup = new GroupData();
    if (beforeGroupListOfContact.size() != 0) {
      addedGroup = beforeGroupListOfContact.iterator().next();
    }
    for (GroupData group : groups) {
      logger.info("!!!Group " + group + " vs AddedGroup " + addedGroup);
      if (!group.equals(addedGroup)) {
        addedGroup = app.db().groupById(group.getId());
        logger.info("!!!return " + addedGroup);
        break;
      }
    }
    app.contact().addGroupTo(selectedContact, addedGroup);
    Groups afterGroupListOfContact = app.db().contactById(selectedContact.getContactId()).getGroups();
    assertThat(afterGroupListOfContact, equalTo(beforeGroupListOfContact.withAdded(addedGroup)));
  }
}
