package qa.pkg.mantis.tests;

import org.testng.annotations.Test;

public class RegistrationTests extends TestBase {
  @Test
  public void testRegistration() {
    app.signUp().start("user1","user1@localhost.localdomain");
  }
}
