package qa.pkg.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import qa.pkg.addressbook.model.ContactData;
import qa.pkg.addressbook.model.Contacts;
import qa.pkg.addressbook.model.GroupData;
import qa.pkg.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {
  @DataProvider
  public Iterator<Object[]> validContactsAsJson() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader
            (new File("src/test/resources/contacts.json")))) {
      String line = reader.readLine();
      String json = "";
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
      }.getType());
      return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }

  @BeforeMethod
  public void ensurePrecondition() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupsPage();
      app.group().createGroup(new GroupData().withGroupName("testGroup2").withHeader("header").withFooter("footer"));
    }
  }

  @Test//(dataProvider = "validContactsAsJson")
  public void testContactCreation() {
    Groups groups = app.db().groups();
    File photo = new File("src/test/resources/photo.jpg");
    ContactData contact = new ContactData().withLastname("Verkivich").withFirstname("Mealnia").
            withHomePhone("+7952764532").withWorkPhone("+37584930303").
            withAddress("Moscow, Lenina str 15").withEmail("mail1@mail.ru")
            .withEmail2("mail2@mail.ru").withPhoto(photo).withGroup(groups.stream().iterator().next());
    app.goTo().homePage();
    Contacts before = app.db().contacts();
    app.goTo().addNewPage();
    app.contact().createContact(contact);

    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after =app.db().contacts();
    assertThat(after, equalTo(before.withAdded(contact.
            withContactId(after.stream().mapToInt((c) -> c.getContactId()).max().getAsInt()))));
  }

}
