package qa.pkg.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.pkg.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().groupsPage();
    System.out.println("Group " + app.group().getNameGroup());
    if (app.group().list().size()==0) {
      app.group().createGroup(new GroupData().withGroupName("testGroup2").withHeader("header").withFooter("footer"));
    }
  }

  @Test
  public void testGroupDeletion()  {
    List<GroupData> before = app.group().list();
    int index=before.size() - 1;
    app.group().delete(index);

    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(index);
    Comparator<? super GroupData> byId= Comparator.comparingInt(GroupData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(after,before);
    }

  }
