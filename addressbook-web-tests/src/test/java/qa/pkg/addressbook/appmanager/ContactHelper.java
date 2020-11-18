package qa.pkg.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
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

  public void clickEditContactBtn() {
    click(By.xpath("//img[@alt='Edit']"));
  }

  public void clickDeleteBtn() {
    click(By.cssSelector("input[value='Delete']"));

  }

  public void clickUpdateBtn() {
    click(By.cssSelector("input[name='update']"));
  }

  public void selectContact() {
    click(By.name("selected[]"));
  }

  public void createContact(ContactData contactData) {
    fillNewContactForm(contactData, true);
    clickEnterBtn();
    returnToHomePage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public List<ContactData> getContactList() {
    List<WebElement> elements=wd.findElements(By.name("selected[]"));
    List<ContactData> contacts = new ArrayList<ContactData>();
    for(WebElement el:elements){
     String titleAttr=el.getAttribute("title");
      String substr[]=titleAttr.split(" ");
      String firstName=substr[1].substring(1);
      String lastName=substr[2].substring(0,substr[2].length()-1);
      int contactId=Integer.parseInt(el.getAttribute("id"));
      System.out.println(titleAttr+" "+contactId);
      contacts.add(new ContactData(contactId,firstName,lastName,null,null,null,null));
    }
    return contacts;
  }
}

