package qa.pkg.mantis.appmanager;

import org.openqa.selenium.By;

public class ResetPasswordHelper extends HelperBase {

  public ResetPasswordHelper(ApplicationManager app) {
    super(app);
  }

  public void openUserDetailsForm(String userName) {
    click(By.xpath("//div[@id='sidebar']/ul/li[6]/a/span"));
    click(By.xpath("//a[contains(@href, '/mantisbt-2.24.2/manage_user_page.php')]"));
    click(By.linkText(userName));
  }

  public void clickResetBtn() {
    click(By.cssSelector("span > input.btn.btn-primary.btn-white.btn-round"));
  }

  public void changePassword(String confirmationLink, String password) {
    wd.get(confirmationLink);
    fillInput(By.name("password"), password);
    fillInput(By.name("password_confirm"), password);
    wd.findElement(By.tagName("button")).click();
  }

}
