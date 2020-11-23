package qa.pkg.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.pkg.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactEditionTests extends TestBase {
  @BeforeMethod
  public void ensurePrecondition() {
    String groupName = app.group().getNameGroup();
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.goTo().addNewPage();
      app.contact().createContact(new ContactData().withLastname("Kudrevich").withFirstname("Mealnia").
              withAddress("Moscow, Lenina str 15").withGroup(groupName));
    }
  }

  @Test
  public void testContactEdition() {
    String groupName = app.group().getNameGroup();
    app.goTo().homePage();
    Set<ContactData> before = app.contact().all();
    ContactData editContact=before.iterator().next();
    ContactData contact = new ContactData().withContactId(editContact.getContactId()).withLastname("Maroov")
            .withFirstname("Lost").withEmail("mail@mail.ru").withHomePhone("+375295464722")
            .withAddress("Moscow, Lenina str 15").withGroup(groupName);

    app.contact().edit(contact);

    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(editContact);
    before.add(contact);

    Assert.assertEquals(after, before);
  }


}
