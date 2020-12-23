package qa.pkg.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import qa.pkg.mantis.appmanager.ApplicationManager;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class TestBase {
  Logger logger = LoggerFactory.getLogger(TestBase.class);

  protected static final ApplicationManager app =
          new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

  public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
    if (isIssueOpen(issueId)) {
      logger.info("*** Ignored because of issue " + issueId);
      throw new SkipException("Ignored because of issue " + issueId);
          }
  }

  private boolean isIssueOpen(int issueId) throws RemoteException, ServiceException, MalformedURLException {
    String issueStatus = app.soap().getIssueStatus(issueId);
    if (issueStatus.equals("resolved") || issueStatus.equals("closed")) {
      return false;
    }
    return true;
  }



  @BeforeSuite
  public void setUp() throws IOException, ServiceException {
    app.init();
    app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
    skipIfNotFixed(2); //1-new, 2-closed
  }

  @AfterSuite
  public void tearDown() throws IOException {
    app.ftp().restore("config_inc.php.bak", "config_inc.php");
    app.stop();
  }

}
