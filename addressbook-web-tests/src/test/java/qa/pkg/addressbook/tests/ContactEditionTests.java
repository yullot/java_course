package qa.pkg.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.pkg.addressbook.model.ContactData;
import qa.pkg.addressbook.model.Contacts;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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
    Contacts before = app.contact().all();
    ContactData editContact = before.iterator().next();
    ContactData contact = new ContactData().withContactId(editContact.getContactId()).withLastname("Maroov")
            .withFirstname("Lost").withEmail("mail@mail.ru").withHomePhone("+375295464722")
            .withAddress("Moscow, Lenina str 15").withGroup(groupName);

    app.contact().edit(contact);

    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size()));
    assertThat(after, equalTo(before.without(editContact).withAdded(contact)));
  }


}
