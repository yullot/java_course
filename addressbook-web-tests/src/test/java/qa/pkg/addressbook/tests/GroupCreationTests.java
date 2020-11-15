package qa.pkg.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import qa.pkg.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().goToGroupsPage();
    int before=app.getGroupHelper().getGroupCount();
    app.getGroupHelper().createGroup(new GroupData("testGroup2", "header", "footer"));
    int after=app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after,before+1);
  }


}
