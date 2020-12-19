package qa.pkg.mantis.appmanager;

import org.openqa.selenium.By;

public class LoginHelper extends HelperBase {
  public LoginHelper(ApplicationManager app) {
    super(app);
  }

  public void login(String userName, String password) {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    fillInput(By.name("username"), userName);
    click(By.cssSelector("input[type='submit']"));
    fillInput(By.name("password"), password);
    click(By.cssSelector("input[type='submit']"));
  }


  public boolean isLoggedInAs(String username){
    return isElementPresent(By.linkText(username));
  }
}
