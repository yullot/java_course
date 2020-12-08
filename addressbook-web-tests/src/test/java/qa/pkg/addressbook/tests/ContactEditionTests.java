package qa.pkg.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.pkg.addressbook.model.ContactData;
import qa.pkg.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEditionTests extends TestBase {
  @BeforeMethod
  public void ensurePrecondition() {
    if (app.db().contacts().size() == 0) {
      String groupName = app.group().getNameGroup();
      app.goTo().homePage();
      app.goTo().addNewPage();
      app.contact().createContact(new ContactData().withLastname("Kudrevich").withFirstname("Mealnia").
              withAddress("Moscow, Lenina str 15").withGroup(groupName));
    }
  }

  @Test
  public void testContactEdition() {
    String groupName = app.group().getNameGroup();
    app.goTo().homePage();
    Contacts before = app.db().contacts();
    ContactData editContact = before.iterator().next();
    ContactData contact = new ContactData().withContactId(editContact.getContactId()).withLastname("Maroov")
            .withFirstname("Lost").withEmail("mail@mail.ru").withHomePhone("+375295464722")
            .withAddress("London, Lenina str 15 dsds").withGroup(groupName);

    app.contact().edit(contact);

    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(editContact).withAdded(contact)));
  }


}
