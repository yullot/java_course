package qa.pkg.addressbook;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {


  @Test
  public void testGroupDeletion() throws Exception {
    gotoPage("groups");
    selectGroup();
    deleteSelectedGroup();
    gotoPage("group page");
  }


}
