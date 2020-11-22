package qa.pkg.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void groupsPage() {
    if (isElementPresent(By.cssSelector("div#content>h1"))
            && wd.findElement(By.xpath("//div[@id='content']/h1")).getText().equals("Groups")
            && isElementPresent(By.name("new"))) {
      return;
    }
    click(By.linkText("groups"));
  }

  public void addNewPage() {
    if (isElementPresent(By.cssSelector("div#content>h1")) &&
            wd.findElement(By.cssSelector("div#content>h1")).getText().equals("Edit / add address book entry")
            && isElementPresent(By.name("submit"))) {
      return;
    }
    click(By.linkText("add new"));
  }

  public void homePage() {
    if (isElementPresent(By.id("maintable"))) {
      return;
    }
    click(By.linkText("home"));
  }

  public void waitForHomePage() {
    WebDriverWait wait = new WebDriverWait(wd, 5);
    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("maintable")));
  }
}
