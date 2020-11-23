package qa.pkg.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.pkg.addressbook.model.GroupData;

import java.util.Set;

public class GroupEditionTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().groupsPage();
    System.out.println("Group " + app.group().getNameGroup());
    if (app.group().all().size() == 0) {
      app.group().createGroup(new GroupData().withGroupName("testGroup2").withHeader("header").withFooter("footer"));
    }
  }

  @Test
  public void testGroupEdition() {
    Set<GroupData> before = app.group().all();
    GroupData editedGroup = before.iterator().next();
    GroupData group = new GroupData().withId(editedGroup.getId())
            .withGroupName("testGroupEdit").withHeader("headerEdit").withFooter("footerEdit");
    app.group().edit(group);

    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(), before.size());
    before.remove(editedGroup);
    before.add(group);
    Assert.assertEquals(after, before);
  }
}

