package qa.pkg.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import qa.pkg.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactEditionTests extends TestBase {
  @Test
  public void testContactEdition() {
    String groupName = app.getGroupHelper().getNameGroup();
    app.getNavigationHelper().goToHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().goToAddNewPage();
      app.getContactHelper().createContact(new ContactData("NEWWW", "Mealnia",
              null, null, "Moscow, Lenina str 15", groupName));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData(before.get(before.size() - 1).getContactId(), "Maroov", "Lost", "mail@mail.ru", "+375295464722",
            "Moscow, Lenina str 15", groupName);

    app.getContactHelper().clickEditContactBtn(before.size() - 1);
    app.getContactHelper().fillNewContactForm(contact, false);
    app.getContactHelper().clickUpdateBtn();
    app.getContactHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getContactId);
    //before.sort(byId);
    before.remove(before.size() - 1);
    before.add(contact);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(after, before);
  }
}
