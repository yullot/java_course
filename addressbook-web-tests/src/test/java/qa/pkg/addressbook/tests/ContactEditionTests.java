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
      app.contact().createContact(new ContactData("NEWWW", "Mealnia",
              null, null, "Moscow, Lenina str 15", groupName));
    }
  }
  @Test
  public void testContactEdition() {
    String groupName = app.group().getNameGroup();
    app.goTo().homePage();
    List<ContactData> before = app.contact().list();
    int index=before.size() - 1;
    ContactData contact = new ContactData(before.get(before.size() - 1).getContactId(), "Maroov", "Lost", "mail@mail.ru", "+375295464722",
            "Moscow, Lenina str 15", groupName);

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
