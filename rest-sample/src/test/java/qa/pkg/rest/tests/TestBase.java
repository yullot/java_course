package qa.pkg.rest.tests;

import com.jayway.restassured.RestAssured;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import qa.pkg.rest.appmanager.ApplicationManager;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class TestBase {
  protected static final ApplicationManager app = new ApplicationManager();

  public void skipIfNotFixed(int issueId) throws RemoteException, MalformedURLException {
    if (isIssueOpen(issueId)) {
      //logger.info("*** Ignored because of issue " + issueId);
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

  private boolean isIssueOpen(int issueId) throws RemoteException,  MalformedURLException {
    String issueStatus = app.rest().getIssueState(issueId);
    if (issueStatus.equals("Resolved") || issueStatus.equals("Closed")) {
     return false;
      //return true;
    }
    return true;
    //return false;
  }

  @BeforeClass
  public void init()  {
    RestAssured.authentication = RestAssured.basic("288f44776e7bec4bf44fdfeb1e646490", "");
  }
}
