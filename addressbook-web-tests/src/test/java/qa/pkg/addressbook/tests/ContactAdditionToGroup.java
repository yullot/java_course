package qa.pkg.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.pkg.addressbook.model.ContactData;
import qa.pkg.addressbook.model.GroupData;
import qa.pkg.addressbook.model.Groups;

import java.io.File;

public class ContactAdditionToGroup extends TestBase{
  @BeforeMethod
  public void ensurePrecondition() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupsPage();
      app.group().createGroup(new GroupData().withGroupName("testGroupNew").withHeader("header").withFooter("footer"));
    }
    Groups groups = app.db().groups();
    File photo = new File("src/test/resources/photo.jpg");
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.goTo().addNewPage();
      app.contact().createContact(new ContactData().withLastname("Kudrevich").withFirstname("Martha").
              withAddress("Moscow, Lenina str 15").withGroup(groups.stream().iterator().next()).withPhoto(photo));
    }
  }
  @Test
  public void testContactAddittionToGroup(){

  }
}
