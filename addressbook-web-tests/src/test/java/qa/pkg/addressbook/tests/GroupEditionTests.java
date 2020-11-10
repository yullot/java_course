package qa.pkg.addressbook.tests;

import org.testng.annotations.Test;
import qa.pkg.addressbook.model.GroupData;

public class GroupEditionTests extends TestBase {
  @Test
  public void testGroupEdition() {
    app.getNavigationHelper().goToGroupsPage();
    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("testGroup2", "header", "footer"));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().clickEditGroupBtn();
    app.getGroupHelper().fillGroupForm(new GroupData("testGroupEdit", "headerEdit", "footerEdit"));
    app.getGroupHelper().clickUpdateBtn();
    app.getGroupHelper().returnToGroupPage();
  }
}

