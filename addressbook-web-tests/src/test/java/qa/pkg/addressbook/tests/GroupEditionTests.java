package qa.pkg.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.pkg.addressbook.model.GroupData;
import qa.pkg.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupEditionTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupsPage();
      app.group().createGroup(new GroupData().withGroupName("testGroup").withHeader("header").withFooter("footer"));
    }
  }
  @Test
  public void testGroupEdition() {
    Groups before = app.db().groups();
    GroupData editedGroup = before.iterator().next();
    GroupData group = new GroupData().withId(editedGroup.getId())
            .withGroupName("testGroupEdit2").withHeader("headerEdit2").withFooter("footerEdit2");
    app.goTo().groupsPage();
    app.group().edit(group);
    assertThat(app.group().count(), equalTo(before.size()));
    Groups after = app.db().groups();
    assertThat(after, equalTo(before.without(editedGroup).withAdded(group)));
    verifyGroupListInUI();
  }

}

