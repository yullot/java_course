package qa.pkg.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.pkg.addressbook.model.ContactData;
import qa.pkg.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneEmailAddressTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    Groups groups=app.db().groups();
    if (app.db().contacts().size() == 0) {
      //String groupName = app.group().getNameGroup();
      app.goTo().homePage();
      app.goTo().addNewPage();
      app.contact().createContact(new ContactData().withLastname("Petrova").withFirstname("Mealnia").
              withAddress("Moscow, Lenina str 15").withGroup(groups.stream().iterator().next()));
    }
  }

  @Test
  public void testContactDataFromEditAndPreview() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    String[] arrayEmails = {contactInfoFromEditForm.getEmail(), contactInfoFromEditForm.getEmail2(),
            contactInfoFromEditForm.getEmail3()};
    String[] arrayPhones = {contactInfoFromEditForm.getHomePhone(), contactInfoFromEditForm.getMobilePhone(),
            contactInfoFromEditForm.getWorkPhone()};
    assertThat(contact.getAllPhones(), equalTo(merge(arrayPhones)));
    assertThat(contact.getAllEmails(), equalTo(merge(arrayEmails)));
    assertThat(contact.getAddress().replaceAll("\\s", ""),
            equalTo(contactInfoFromEditForm.getAddress().replaceAll("\\s", "")));
  }

        public String merge(String[] array) {
    return Arrays.asList(array)
            .stream().filter((s) -> !s.equals(""))
            .map(ContactPhoneEmailAddressTests::cleaned).collect(Collectors.joining("\n"));
  }

  public static String cleaned(String str) {
    return str.replaceAll("\\s", "").replaceAll("[-()]", "");
  }
}
