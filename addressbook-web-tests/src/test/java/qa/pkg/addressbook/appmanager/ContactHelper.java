package qa.pkg.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import qa.pkg.addressbook.model.ContactData;
import qa.pkg.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

  private void clickEditContactBtnById(int contactId) {
    wd.findElement(By.cssSelector("a[href='edit.php?id=" + contactId + "']")).click();

  }

  public void clickDeleteBtn() {
    click(By.cssSelector("input[value='Delete']"));
  }

  public void confirmAlert() {
    wd.switchTo().alert().accept();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getContactId());
    clickDeleteBtn();
    contactCache = null;
    confirmAlert();
  }

  public void clickUpdateBtn() {
    click(By.cssSelector("input[name='update']"));
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  private void selectContactById(int contactId) {
    wd.findElement(By.id(String.valueOf(contactId))).click();
  }

  public void createContact(ContactData contactData) {
    fillNewContactForm(contactData, true);
    clickEnterBtn();
    contactCache = null;
    returnToHomePage();
  }

  public void edit(ContactData contact) {
    clickEditContactBtnById(contact.getContactId());
    fillNewContactForm(contact, false);
    clickUpdateBtn();
    contactCache = null;
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
      //System.out.println("name:" + firstName + " " + lastName + " " + contactId);
      contacts.add(new ContactData().withContactId(contactId).withFirstname(firstName).withLastname(lastName));
    }
    System.out.println();
    return contacts;
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    List<WebElement> elements = wd.findElements(By.name("entry"));
    contactCache = new Contacts();
    for (WebElement el : elements) {
      String firstName = el.findElement(By.xpath("td[3]")).getText();
      String lastName = el.findElement(By.xpath("td[2]")).getText();
      int contactId = Integer.parseInt(el.findElement(By.xpath("td[1]/input")).getAttribute("id"));
      System.out.println("name:" + firstName + " " + lastName + " " + contactId);
      contactCache.add(new ContactData().withContactId(contactId).withFirstname(firstName).withLastname(lastName));
    }
    return contactCache;
  }
}

