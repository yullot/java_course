package qa.pkg.addressbook.tests;

import org.testng.annotations.Test;
import qa.pkg.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {
  @Test
  public void testContactDeletion() {
    app.getNavigationHelper().goToHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(
              new ContactData("Ivan", "Ivanovna", "Mealnia", null, null, "Moscow, Lenina str 15", "testGroupEdit"));
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().clickDeleteBtn();
    app.getNavigationHelper().confirmAlert();
  }
}
