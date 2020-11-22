package qa.pkg.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.pkg.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactEditionTests extends TestBase {
  @BeforeMethod
  public void ensurePrecondition(){
    String groupName = app.group().getNameGroup();
    app.goTo().homePage();
    if (!app.contact().isThereAContact()) {
      app.goTo().addNewPage();
      app.contact().createContact(new ContactData().withLastname("Kudrevich").withFirstname("Mealnia").
              withAddress("Moscow, Lenina str 15").withGroup(groupName));
    }
  }
  @Test
  public void testContactEdition() {
    String groupName = app.group().getNameGroup();
    app.goTo().homePage();
    List<ContactData> before = app.contact().list();
    int index=before.size() - 1;
    ContactData contact = new ContactData().withContactId(before.get(before.size() - 1).getContactId()).withLastname("Maroov")
            .withFirstname("Lost").withEmail("mail@mail.ru").withHomePhone("+375295464722")
            .withAddress("Moscow, Lenina str 15").withGroup(groupName);

    app.contact().edit(index, contact);

    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());

    Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getContactId);
    before.remove(index);
    before.add(contact);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(after, before);
  }


}
