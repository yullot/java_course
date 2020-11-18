package qa.pkg.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import qa.pkg.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ContactDeletionTests extends TestBase {
  @Test
  public void testContactDeletion() throws InterruptedException {
    String groupName=app.getGroupHelper().getNameGroup();
    app.getNavigationHelper().goToHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().goToAddNewPage();
      app.getContactHelper().createContact(new ContactData("NEWWW",  "Mealnia",
              null, null, "Moscow, Lenina str 15",groupName));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact();
    app.getContactHelper().clickDeleteBtn();
    app.getNavigationHelper().confirmAlert();
    app.getNavigationHelper().waitForHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(),before.size()-1);
    before.remove(before.size()-1);
    Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getContactId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(after, before);
  }
}
