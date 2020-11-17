package qa.pkg.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import qa.pkg.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
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
    GroupData group=new GroupData(before.get(before.size()-1).getId(),"testGroupEdit", "headerEdit", "footerEdit");
    app.getGroupHelper().fillGroupForm(group);
    app.getGroupHelper().clickUpdateBtn();
    app.getGroupHelper().returnToGroupPage();
    List <GroupData> after=app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(),before.size());
    before.remove(before.size()-1);
    before.add(group);
    Comparator<? super GroupData> byId= Comparator.comparingInt(GroupData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(after,before);
  }
}

