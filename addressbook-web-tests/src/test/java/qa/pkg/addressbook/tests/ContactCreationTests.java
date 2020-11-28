package qa.pkg.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import qa.pkg.addressbook.model.ContactData;
import qa.pkg.addressbook.model.Contacts;
import qa.pkg.addressbook.model.GroupData;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactCreationTests extends TestBase {


  @Test (dataProvider = "validContacts")
  public void testContactCreation() {
    String groupName = app.group().getNameGroup();
    app.goTo().homePage();
    Contacts before = app.contact().all();
    app.goTo().addNewPage();
    File photo =new File("src/test/resources/photo.jpg");
    ContactData contact = new ContactData().withLastname("Verkivich").withFirstname("Mealnia").
            withHomePhone("+7952764532").withWorkPhone("+37584930303").
            withAddress("Moscow, Lenina str 15").withGroup(groupName).withEmail("mail1@mail.ru")
            .withEmail2("mail2@mail.ru").withPhoto(photo);
    app.contact().createContact(contact);

    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withAdded(contact.
            withContactId(after.stream().mapToInt((c) -> c.getContactId()).max().getAsInt()))));
  }

}
