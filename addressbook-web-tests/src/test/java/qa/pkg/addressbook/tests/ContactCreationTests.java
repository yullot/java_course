package qa.pkg.addressbook.tests;

import org.testng.annotations.Test;
import qa.pkg.addressbook.model.ContactData;
import qa.pkg.addressbook.model.GroupData;

public class ContactCreationTests extends TestBase {


  @Test
  public void testAdressbookEntryCreation() {
    app.getNavigationHelper().goToGroupsPage();
    app.getContactHelper().createContact(new ContactData("Ivan", "Ivanovna", "Mealnia",
            null, null, "Moscow, Lenina str 15"));
    // }
  }

}
