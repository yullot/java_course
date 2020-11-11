package qa.pkg.addressbook.tests;

import org.testng.annotations.Test;
import qa.pkg.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {
  @Test
  public void testContactDeletion() {
    String groupName=app.getGroupHelper().getNameGroup();
    app.getNavigationHelper().goToHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().goToAddNewPage();
      app.getContactHelper().createContact(new ContactData("NEWWW", "Ivanovna", "Mealnia",
              null, null, "Moscow, Lenina str 15",groupName));
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().clickDeleteBtn();
    app.getNavigationHelper().confirmAlert();
  }
}
