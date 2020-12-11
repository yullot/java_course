package qa.pkg.addressbook.tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.pkg.addressbook.model.GroupData;
import qa.pkg.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTests extends TestBase {
  Logger logger = LoggerFactory.getLogger(GroupDeletionTests.class);

  @BeforeMethod
  public void ensurePrecondition() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupsPage();
      app.group().createGroup(new GroupData().withGroupName("testGroup2").withHeader("header").withFooter("footer"));
    }
  }

  @Test
  public void testGroupDeletion() {
    logger.info("Start testGroupDeletion");
    Groups before = app.db().groups();
    GroupData deletedGroup = before.iterator().next();
    app.goTo().groupsPage();
    app.group().delete(deletedGroup);
    assertThat(app.group().count(), equalTo(before.size() - 1));
    Groups after = app.db().groups();
    assertThat(after, equalTo(before.without(deletedGroup)));
    logger.info("Stop testGroupDeletion");
  }
}
