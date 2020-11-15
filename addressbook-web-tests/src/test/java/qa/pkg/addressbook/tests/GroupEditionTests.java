package qa.pkg.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import qa.pkg.addressbook.model.GroupData;

public class GroupEditionTests extends TestBase {
  @Test
  public void testGroupEdition() {
    app.getNavigationHelper().goToGroupsPage();
    System.out.println("Group "+app.getGroupHelper().getNameGroup());
    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("testGroup2", "header", "footer"));
    }
    int before=app.getGroupHelper().getGroupCount();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().clickEditGroupBtn();
    app.getGroupHelper().fillGroupForm(new GroupData("testGroupEdit", "headerEdit", "footerEdit"));
    app.getGroupHelper().clickUpdateBtn();
    app.getGroupHelper().returnToGroupPage();
    int after=app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after,before);
  }
}

