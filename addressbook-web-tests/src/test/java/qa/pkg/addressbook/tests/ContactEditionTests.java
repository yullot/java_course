package qa.pkg.addressbook.tests;

import org.testng.annotations.Test;
import qa.pkg.addressbook.model.ContactData;

public class ContactEditionTests extends TestBase {
  @Test
  public void testContactEdition(){
    app.getNavigationHelper().goToHomePage();
    app.getContactHelper().clickEditContactBtn();
    app.getContactHelper().fillNewContactForm(new ContactData("IvaEdit", "ValentinivnaEdit", "Thrump", "mail@mail.ru", "+375295464722", "Moscow, Lenina str 15"));
    app.getContactHelper().clickUpdateBtn();
    app.getContactHelper().returnToHomePage();
  }
}
