package qa.pkg.addressbook;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class AdressbookEntryCreationTest {
  private WebDriver wd;


  @BeforeMethod
  public void setUp() throws Exception {
    wd = new FirefoxDriver();
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    login("admin", "secret");
  }

  @Test
  public void testAdressbookEntryCreation() throws Exception {
    gotoPage("add new");
    fillNewContactForm(new ContactData("Ivan", "Ivanovna", "Mealnia", "mail@mail.ru", "7956782934", "Moscow, Lenina str 15"));
    clickButtonByXpath("(//input[@name='submit'])[2]");
    gotoPage("home page");
  }

  private void logout() {
    wd.findElement(By.linkText("Logout")).click();
  }

  private void clickButtonByXpath(String xpath) {
    wd.findElement(By.xpath(xpath)).click();
  }

  private void fillNewContactForm(ContactData contactData) {
    wd.findElement(By.name("firstname")).click();
    wd.findElement(By.name("firstname")).clear();
    wd.findElement(By.name("firstname")).sendKeys(contactData.getFirstname());
    wd.findElement(By.name("middlename")).clear();
    wd.findElement(By.name("middlename")).sendKeys(contactData.getMiddlename());
    wd.findElement(By.name("lastname")).clear();
    wd.findElement(By.name("lastname")).sendKeys(contactData.getLastname());
    wd.findElement(By.name("nickname")).clear();
    wd.findElement(By.name("nickname")).sendKeys("Marfa");
    wd.findElement(By.name("company")).clear();
    wd.findElement(By.name("company")).sendKeys("Yandex");
    wd.findElement(By.name("address")).clear();
    wd.findElement(By.name("address")).sendKeys(contactData.getAddress());
    wd.findElement(By.name("home")).clear();
    wd.findElement(By.name("home")).sendKeys(contactData.getHomePhone());
    wd.findElement(By.name("email")).clear();
    wd.findElement(By.name("email")).sendKeys(contactData.getEmail());
    wd.findElement(By.name("bday")).click();
    new Select(wd.findElement(By.name("bday"))).selectByVisibleText("18");
    wd.findElement(By.xpath("//option[@value='18']")).click();
    wd.findElement(By.name("bmonth")).click();
    new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText("December");
    wd.findElement(By.xpath("//option[@value='December']")).click();
    wd.findElement(By.name("theform")).click();
    wd.findElement(By.name("byear")).click();
    wd.findElement(By.name("byear")).clear();
    wd.findElement(By.name("byear")).sendKeys("1980");
    wd.findElement(By.name("address2")).clear();
    wd.findElement(By.name("address2")).sendKeys("Minsk, Lenina 124/12");
    wd.findElement(By.name("phone2")).clear();
    wd.findElement(By.name("phone2")).sendKeys("12");
    wd.findElement(By.name("notes")).click();
    wd.findElement(By.name("notes")).clear();
    wd.findElement(By.name("notes")).sendKeys("Notes");
  }

  private void gotoPage(String linkName) {
    wd.findElement(By.linkText(linkName)).click();
  }

  private void login(String username, String password) {
    wd.get("http://localhost/addressbook/");
    wd.findElement(By.name("user")).clear();
    wd.findElement(By.name("user")).sendKeys(username);
    wd.findElement(By.name("pass")).click();
    wd.findElement(By.name("pass")).clear();
    wd.findElement(By.name("pass")).sendKeys(password);
    wd.findElement(By.xpath("//input[@value='Login']")).click();
  }

  @AfterMethod
  public void tearDown() throws Exception {
    logout();
    wd.quit();
  }

  private boolean isElementPresent(By by) {
    try {
      wd.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }


}
