package qa.pkg.mantis.appmanager;

import org.openqa.selenium.By;

public class RegistrationHelper extends HelperBase {

  public RegistrationHelper(ApplicationManager app) {
    super(app);
  }

  public void start(String username, String email) {
    wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
    fillInput(By.name("username"), username);
    fillInput(By.name("email"), email);
    click(By.cssSelector("input[type='submit']"));
  }

  public void finish(String confirmationLink, String password) {
    wd.get(confirmationLink);
    fillInput(By.name("password"),password);
    fillInput(By.name("password_confirm"),password);
    click(By.cssSelector("button[type='submit']"));
  }
}
