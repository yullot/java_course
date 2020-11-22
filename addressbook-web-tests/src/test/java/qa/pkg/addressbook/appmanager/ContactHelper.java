package qa.pkg.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import qa.pkg.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {
  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void fillNewContactForm(ContactData contactData, boolean creation) {
    fillInput(By.name("firstname"), contactData.getFirstname());
    fillInput(By.name("lastname"), contactData.getLastname());
    fillInput(By.name("nickname"), "Marfa");
    fillInput(By.name("company"), "Yandex");
    fillInput(By.name("address"), contactData.getAddress());
    fillInput(By.name("home"), contactData.getHomePhone());
    fillInput(By.name("email"), contactData.getEmail());
    selectByVisibleText(By.name("bday"), "18");
    selectByVisibleText(By.name("bmonth"), "December");
    fillInput(By.name("byear"), "1980");
    fillInput(By.name("address2"), "Minsk, Lenina 124/12");
    fillInput(By.name("phone2"), "12");
    fillInput(By.name("notes"), "Notes");
    if (creation) {
      selectByVisibleText(By.name("new_group"), contactData.getGroup());
    } else Assert.assertFalse(isElementPresent(By.name("new_group")));
  }

  private void selectByVisibleText(By locator, String value) {
    new Select(wd.findElement(locator)).selectByVisibleText(value);
  }

  public void clickEnterBtn() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public void clickEditContactBtn(int index) {
    wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
  }

  public void clickDeleteBtn() {
    click(By.cssSelector("input[value='Delete']"));
  }
  public void confirmAlert() {
    wd.switchTo().alert().accept();
  }
  public void delete(int index) {
    selectContact(index);
    clickDeleteBtn();
    confirmAlert();
  }
  public void clickUpdateBtn() {
    click(By.cssSelector("input[name='update']"));
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void createContact(ContactData contactData) {
    fillNewContactForm(contactData, true);
    clickEnterBtn();
    returnToHomePage();
  }

  public void edit(int index, ContactData contact) {
    clickEditContactBtn(index);
    fillNewContactForm(contact, false);
    clickUpdateBtn();
    returnToHomePage();
  }
  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public List<ContactData> list() {
    List<WebElement> elements = wd.findElements(By.name("entry"));
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (WebElement el : elements) {
      String firstName = el.findElement(By.xpath("td[3]")).getText();
      String lastName = el.findElement(By.xpath("td[2]")).getText();
      int contactId = Integer.parseInt(el.findElement(By.xpath("td[1]/input")).getAttribute("id"));
      System.out.println("name:" + firstName + " " + lastName + " " + contactId);
      contacts.add(new ContactData(contactId, firstName, lastName, null, null, null, null));
    }
    System.out.println();
    return contacts;
  }
}

