package qa.pkg.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.pkg.addressbook.model.ContactData;

import java.util.Set;

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
    Set<ContactData> before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().waitForHomePage();

    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(deletedContact);
    Assert.assertEquals(after, before);
  }
}
