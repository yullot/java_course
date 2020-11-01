package qa.pkg.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {
  @Test
  public void testContactDeletion() {
    app.getNavigationHelper().goToHomePage();
    app.getContactHelper().clickEditContactBtn();
    app.getContactHelper().clickDeleteBtn();
  }
}
