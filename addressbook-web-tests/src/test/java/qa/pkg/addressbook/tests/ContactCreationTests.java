package qa.pkg.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import qa.pkg.addressbook.model.ContactData;

import java.util.Set;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() {
    String groupName = app.group().getNameGroup();
    app.goTo().homePage();
    Set<ContactData> before = app.contact().all();
    app.goTo().addNewPage();
    ContactData contact = new ContactData().withLastname("Ustinivich").withFirstname("Mealnia").
            withAddress("Moscow, Lenina str 15").withGroup(groupName);
    app.contact().createContact(contact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);
    contact.withContactId(after.stream().mapToInt((c)->c.getContactId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(after, before);
  }
}
