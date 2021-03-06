package qa.pkg.mantis.tests;

import org.testng.annotations.Test;
import qa.pkg.mantis.appmanager.HttpSession;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class LoginTests extends TestBase {

  @Test
  public void testLogin() throws IOException {
    HttpSession session = app.newSession();
    assertTrue(session.loginHttpSession("administrator", "root"));
    assertTrue(session.isLoggedInAs("administrator"));
  }
}
