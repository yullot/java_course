package qa.pkg.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import qa.pkg.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation()  {
    app.getNavigationHelper().goToGroupsPage();
    app.getGroupHelper().initGroupCreation();
    app.getGroupHelper().fillGroupForm(new GroupData("testGroup2", "header", "footer"));
    app.getGroupHelper().click(By.name("submit"));
    app.getGroupHelper().returnToGroupPage();
  }


}
