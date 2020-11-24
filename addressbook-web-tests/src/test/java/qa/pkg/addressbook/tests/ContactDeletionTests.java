package qa.pkg.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.pkg.addressbook.model.ContactData;
import qa.pkg.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {
  @BeforeMethod
  public void ensurePrecondition() {
    String groupName = app.group().getNameGroup();
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.goTo().addNewPage();
      app.contact().createContact(new ContactData().withLastname("Petrova").withFirstname("Mealnia").
              withAddress("Moscow, Lenina str 15").withGroup(groupName));
    }
  }

  @Test
  public void testContactDeletion() {
    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().waitForHomePage();

    assertThat(app.contact().count(), equalTo(before.size() - 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.without(deletedContact)));
  }
}
