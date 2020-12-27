package qa.pkg.rest.tests;

import org.testng.annotations.Test;
import qa.pkg.rest.model.Issue;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestTest extends TestBase {

  @Test
  public void testCreateIssue() throws IOException {
    Set<Issue> oldIssues = app.rest0().getIssues();
    Issue newIssue = new Issue().withSubject("HHH subject").withDescription("HHH description");
    int issueId = app.rest0().createIssue(newIssue);
    Set<Issue> newIssues = app.rest0().getIssues();
    oldIssues.add(newIssue.withId(issueId));
    assertEquals(newIssues, oldIssues);
  }


}
