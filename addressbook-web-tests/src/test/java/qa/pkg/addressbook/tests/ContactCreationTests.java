package qa.pkg.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;
import qa.pkg.addressbook.model.ContactData;
import qa.pkg.addressbook.model.Contacts;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() {
    String groupName = app.group().getNameGroup();
    app.goTo().homePage();
    Contacts before = app.contact().all();
    app.goTo().addNewPage();
    ContactData contact = new ContactData().withLastname("Ustinivich").withFirstname("Mealnia").
            withHomePhone("+7952764532").withMobilePhone("+37593994505").withWorkPhone("+37584930303").
            withAddress("Moscow, Lenina str 15").withGroup(groupName).withEmail("mail1@mail.ru").withEmail2("mail2@mail.ru");;
    app.contact().createContact(contact);

    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withAdded(contact.
            withContactId(after.stream().mapToInt((c) -> c.getContactId()).max().getAsInt()))));
  }
}
