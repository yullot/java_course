package qa.pkg.addressbook;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() throws Exception {
    gotoPage("groups");
    initGroupCreation();
    fillGroupForm(new GroupData("testGroup2", "header", "footer"));
    submitFormByName("submit");
    gotoPage("group page");
    }


}
