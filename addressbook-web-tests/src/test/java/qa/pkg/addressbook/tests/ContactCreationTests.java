package qa.pkg.addressbook.tests;

import org.testng.annotations.Test;
import qa.pkg.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() {
    String groupName=app.getGroupHelper().getNameGroup();
    app.getNavigationHelper().goToAddNewPage();
    app.getContactHelper().createContact(new ContactData("NEWWW", "Ivanovna", "Mealnia",
            null, null, "Moscow, Lenina str 15",groupName));
    // }
  }

}
