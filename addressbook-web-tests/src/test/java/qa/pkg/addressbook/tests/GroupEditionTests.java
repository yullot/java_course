package qa.pkg.addressbook.tests;

import org.testng.annotations.Test;
import qa.pkg.addressbook.model.GroupData;

public class GroupEditionTests extends TestBase {
  @Test
  public void testGroupEdition() {
    app.getNavigationHelper().goToGroupsPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().clickEditGroupBtn();
    app.getGroupHelper().fillGroupForm(new GroupData("testGroupEdit", "headerEdit", "footerEdit"));
    app.getGroupHelper().clickUpdateBtn();
    app.getGroupHelper().returnToGroupPage();
  }
}

