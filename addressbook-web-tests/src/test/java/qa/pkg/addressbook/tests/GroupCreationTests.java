package qa.pkg.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import qa.pkg.addressbook.model.GroupData;

import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().goToGroupsPage();
    List<GroupData> before=app.getGroupHelper().getGroupList();
    app.getGroupHelper().createGroup(new GroupData("testGroup2", "header", "footer"));
    List <GroupData> after=app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(),before.size()+1);
  }


}
