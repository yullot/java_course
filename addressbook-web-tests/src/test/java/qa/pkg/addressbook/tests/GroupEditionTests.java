package qa.pkg.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.pkg.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupEditionTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().groupsPage();
    System.out.println("Group " + app.group().getNameGroup());
    if (app.group().list().size()==0) {
      app.group().createGroup(new GroupData("testGroup2", "header", "footer"));
    }
  }

  @Test
  public void testGroupEdition() {
    List<GroupData> before = app.group().list();
    int index = before.size() - 1;
    GroupData group = new GroupData(before.get(before.size() - 1).getId(), "testGroupEdit", "headerEdit", "footerEdit");

    app.group().edit(index, group);

    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size(), before.size());
    before.remove(before.size() - 1);
    before.add(group);
    Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(after, before);
  }


}

