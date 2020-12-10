package qa.pkg.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import qa.pkg.addressbook.appmanager.ApplicationManager;
import qa.pkg.addressbook.model.GroupData;
import qa.pkg.addressbook.model.Groups;

import java.io.IOException;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.equalTo;

public class TestBase {

  protected static final ApplicationManager app =
          new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

  @BeforeSuite
  public void setUp() throws IOException {
    app.init();
  }

  public void verifyGroupListInUI() {
    /**
     * parsing as booleean System property
     */
    if (Boolean.getBoolean("verifyUI")) {
      Groups dbGroups = app.db().groups();
      Groups uiGroups = app.group().all();
      assertThat(uiGroups, equalTo(dbGroups.stream()
              .map((g) -> (new GroupData().withId(g.getId()).withGroupName(g.getGroupName())))
              .collect(Collectors.toSet())));
      System.out.println(" verifyGroupListInUI()=TRUE");
    }
  }

  @AfterSuite
  public void tearDown() {
    app.stop();
  }

}
