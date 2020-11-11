package qa.pkg.addressbook.tests;

import org.testng.annotations.Test;
import qa.pkg.addressbook.model.ContactData;

public class ContactEditionTests extends TestBase {
  @Test
  public void testContactEdition(){
    String groupName=app.getGroupHelper().getNameGroup();
    app.getNavigationHelper().goToHomePage();
    if (!app.getContactHelper().isThereAContact()){
      app.getNavigationHelper().goToAddNewPage();
      app.getContactHelper().createContact(new ContactData("NEWWW", "Ivanovna", "Mealnia",
              null, null, "Moscow, Lenina str 15",groupName)); }
    app.getContactHelper().clickEditContactBtn();
    app.getContactHelper().fillNewContactForm(new ContactData("IvaEdit", "ValentinivnaEdit", "Thrump", "mail@mail.ru", "+375295464722", "Moscow, Lenina str 15",groupName),false);
    app.getContactHelper().clickUpdateBtn();
    app.getContactHelper().returnToHomePage();
  }
}
