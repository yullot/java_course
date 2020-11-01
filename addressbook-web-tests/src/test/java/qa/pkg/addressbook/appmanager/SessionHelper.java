package qa.pkg.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase {
  public SessionHelper(WebDriver wd) {
    super(wd);
  }

  public void login(String username, String password) {
    fillInput(By.name("user"), username);
    fillInput(By.name("pass"), password);
    click(By.xpath("//input[@value='Login']"));
  }

  public void logout() {
    click(By.linkText("Logout"));
  }
}
