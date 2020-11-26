package qa.pkg.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.pkg.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneEmailAddressTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    String groupName = app.group().getNameGroup();
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.goTo().addNewPage();
      app.contact().createContact(new ContactData().withLastname("Petrova").withFirstname("Mealnia").
              withAddress("Barnaul, Lenina str 15").withGroup(groupName));
    }
  }

  @Test
  public void testContactDataFromEditAndPreview() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getAllPhones(), equalTo(mergePhone(contactInfoFromEditForm)));
    assertThat(contact.getAllEmails(), equalTo(mergeEmail(contactInfoFromEditForm)));
    assertThat(contact.getAddress().replaceAll("\\s", ""),
            equalTo(contactInfoFromEditForm.getAddress().replaceAll("\\s", "")));
  }

  public String mergePhone(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
            .stream().filter((s) -> !s.equals(""))
            .map(ContactPhoneEmailAddressTests::cleaned).collect(Collectors.joining("\n"));
  }

  public String mergeEmail(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s) -> !s.equals(""))
            .map(ContactPhoneEmailAddressTests::cleaned).collect(Collectors.joining("\n"));
  }

  public static String cleaned(String str) {
    return str.replaceAll("\\s", "").replaceAll("[-()]", "");
  }
}
