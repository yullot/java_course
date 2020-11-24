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
    app.goTo().groupsPage();
    System.out.println("Group " + app.group().getNameGroup());
    if (app.group().all().size() == 0) {
      app.group().createGroup(new GroupData().withGroupName("testGroup2").withHeader("header").withFooter("footer"));
    }
  }

  @Test
  public void testGroupEdition() {
   Groups before = app.group().all();
    GroupData editedGroup = before.iterator().next();
    GroupData group = new GroupData().withId(editedGroup.getId())
            .withGroupName("testGroupEdit").withHeader("headerEdit").withFooter("footerEdit");
    app.group().edit(group);

    Groups after = app.group().all();
    assertThat(after.size(), equalTo(before.size()));
    assertThat(after,equalTo(before.without(editedGroup).withAdded(group)));
   
  }
}

