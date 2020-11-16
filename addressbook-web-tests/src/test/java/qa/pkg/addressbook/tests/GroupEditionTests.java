package qa.pkg.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import qa.pkg.addressbook.model.GroupData;

import java.util.List;

public class GroupEditionTests extends TestBase {
  @Test
  public void testGroupEdition() {
    app.getNavigationHelper().goToGroupsPage();
    System.out.println("Group "+app.getGroupHelper().getNameGroup());
    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("testGroup2", "header", "footer"));
    }
    List<GroupData> before=app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size()-1);
    app.getGroupHelper().clickEditGroupBtn();
    app.getGroupHelper().fillGroupForm(new GroupData("testGroupEdit", "headerEdit", "footerEdit"));
    app.getGroupHelper().clickUpdateBtn();
    app.getGroupHelper().returnToGroupPage();
    List <GroupData> after=app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(),before.size());
  }
}

