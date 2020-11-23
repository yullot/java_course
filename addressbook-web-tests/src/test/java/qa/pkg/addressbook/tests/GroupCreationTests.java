package qa.pkg.addressbook.tests;

import org.testng.annotations.Test;
import qa.pkg.addressbook.model.GroupData;
import qa.pkg.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.goTo().groupsPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().withGroupName("testGroup2").withHeader("header").withFooter("footer");
    app.group().createGroup(group);

    Groups after = app.group().all();
    assertThat(after.size(), equalTo(before.size()+1));
    //group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}
