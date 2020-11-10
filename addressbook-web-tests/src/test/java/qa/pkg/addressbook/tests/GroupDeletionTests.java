package qa.pkg.addressbook.tests;

import org.testng.annotations.Test;
import qa.pkg.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion() throws Exception {
    app.getNavigationHelper().goToGroupsPage();
    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("testGroup2", "header", "footer"));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectedGroup();
    app.getGroupHelper().returnToGroupPage();
  }
}
