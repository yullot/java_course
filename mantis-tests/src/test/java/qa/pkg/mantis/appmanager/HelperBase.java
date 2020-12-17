package qa.pkg.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class HelperBase {
  protected ApplicationManager app;
  protected WebDriver wd;

  public HelperBase(ApplicationManager app) {
    this.app=app;
    this.wd = app.getDriver();

  }

  protected void click(By locator) {
   wd.findElement(locator).click();
  }

  protected void fillInput(By locator, String value) {
    if (value != null) {
      String existedText = wd.findElement(locator).getAttribute("value");
      if (!value.equals(existedText)) {
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(value);
      }
    }
  }

  public boolean isElementPresent(By by) {
    try {
      wd.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  public boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }
}
