package qa.pkg.addressbook.tests;

import org.testng.annotations.Test;
import qa.pkg.addressbook.model.ContactData;
import qa.pkg.addressbook.model.Contacts;

public class ContactPhoneTests extends TestBase{
  @Test
  public void testContactPhones(){
    app.goTo().homePage();
    ContactData contact= app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm=app.contact().infoFromEditForm(contact);
  }
}
