package qa.pkg.addressbook.tests;

import org.testng.annotations.Test;
import qa.pkg.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {


  @Test
  public void testAdressbookEntryCreation() {
    app.getNavigationHelper().goToAddNewPage();
    app.getContactHelper().fillNewContactForm(new ContactData("Ivan", "Ivanovna", "Mealnia", null, null, "Moscow, Lenina str 15"));
    app.getContactHelper().clickEnterBtn();
    app.getContactHelper().returnToHomePage();
  }

}
