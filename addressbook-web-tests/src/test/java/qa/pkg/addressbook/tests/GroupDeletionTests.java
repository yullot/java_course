package qa.pkg.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import qa.pkg.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion() throws Exception {
    app.getNavigationHelper().goToGroupsPage();
    int before=app.getGroupHelper().getGroupCount();
    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("testGroup2", "header", "footer"));
    }
    app.getGroupHelper().selectGroup(before-1);
    app.getGroupHelper().deleteSelectedGroup();
    app.getGroupHelper().returnToGroupPage();
    int after=app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after,before-1);
  }
}
