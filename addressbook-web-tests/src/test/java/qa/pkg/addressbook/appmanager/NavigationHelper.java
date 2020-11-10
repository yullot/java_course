package qa.pkg.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.sql.SQLOutput;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void goToGroupsPage() {
    if (isElementPresent(By.cssSelector("div#content>h1"))
            && wd.findElement(By.xpath("//div[@id='content']/h1")).getText().equals("Groups")
            && isElementPresent(By.name("new"))) {
      return;
    }
    click(By.linkText("groups"));
  }

  public void goToAddNewPage() {
    if (isElementPresent(By.cssSelector("div#content>h1")) &&
            wd.findElement(By.cssSelector("div#content>h1")).getText().equals("Edit / add address book entry")
            && isElementPresent(By.name("submit"))) {
      return;
    }
    click(By.linkText("add new"));
  }

  public void goToHomePage() {
    if (isElementPresent(By.id("maintable"))) {
      return;
    }
    click(By.linkText("home"));
  }

  public void confirmAlert() {
    wd.switchTo().alert().accept();
  }
}
