package qa.pkg.rest.tests;

import org.testng.annotations.Test;
import qa.pkg.rest.model.Issue;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestAssuredTest extends TestBase {


  @Test
  public void testCreateIssue() throws MalformedURLException, RemoteException {
    skipIfNotFixed(530);
    Set<Issue> oldIssues = app.rest().getIssues();
    Issue newIssue = new Issue().withSubject("Rest Assured subject").withDescription("Rest Assured description");
    int issueId = app.rest().createIssue(newIssue);
    Set<Issue> newIssues = app.rest().getIssues();
    oldIssues.add(newIssue.withId(issueId));
    assertEquals(newIssues, oldIssues);
  }
}
