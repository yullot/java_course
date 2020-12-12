package qa.pkg.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import qa.pkg.addressbook.model.ContactData;
import qa.pkg.addressbook.model.Contacts;
import qa.pkg.addressbook.model.GroupData;

import java.io.File;
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
    fillInput(By.name("mobile"), contactData.getMobilePhone());
    fillInput(By.name("work"), contactData.getWorkPhone());
    fillInput(By.name("email"), contactData.getEmail());
    fillInput(By.name("email2"), contactData.getEmail2());
    selectByVisibleText(By.name("bday"), "18");
    selectByVisibleText(By.name("bmonth"), "December");
    fillInput(By.name("byear"), "1980");
    fillInput(By.name("address2"), contactData.getAddress());
    fillInput(By.name("notes"), "Notes");
    /*if (creation) {
      selectByVisibleText(By.name("new_group"), contactData.getGroups().stream().iterator().next().getGroupName());
    } else Assert.assertFalse(isElementPresent(By.name("new_group")));
*/
    if (creation) {
      attach(By.name("photo"), contactData.getPhoto());
    }
  }

  protected void attach(By locator, File photo) {
    if (photo != null) {
      wd.findElement(locator).sendKeys(photo.getAbsolutePath());
    }
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

  public void returnToHomePageWithSelectedGroup(GroupData group) {
    click(By.linkText("group page \""+group.getGroupName()+"\""));
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

  public void addGroupTo(ContactData contact, GroupData group) {
    selectContactById(contact.getContactId());
    selectGroup(group);
    clickAddTo();
    returnToHomePageWithSelectedGroup(group);
  }

  private void selectGroup(GroupData group) {
   // new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(group.getGroupName());
    wd.findElement(By.xpath("(//option[@value='"+group.getId()+"'])[2]")).click();
  }

  private void clickAddTo() {
    click(By.cssSelector("input[name='add']"));
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

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  /*public List<ContactData> list() {
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
  }*/

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
      String allPhones = el.findElement(By.xpath("td[6]")).getText();
      String allEmails = el.findElement(By.xpath("td[5]")).getText();
      String address = el.findElement(By.xpath("td[4]")).getText();
      contactCache.add(new ContactData().withContactId(contactId).withFirstname(firstName).withLastname(lastName).
              withAllPhones(allPhones).withAllEmails(allEmails).withAddress(address));

      System.out.println("name:" + firstName + " " + lastName + " " + contactId + " " + allPhones);
    }
    return contactCache;
  }

  public ContactData infoFromEditForm(ContactData contact) {
    clickEditContactBtnById(contact.getContactId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String homePhone = wd.findElement(By.name("home")).getAttribute("value");
    String mobilePhone = wd.findElement(By.name("mobile")).getAttribute("value");
    String workPhone = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getText();
    wd.navigate().back();
    return new ContactData().withContactId(contact.getContactId()).withFirstname(firstname)
            .withLastname(lastname).withHomePhone(homePhone).withMobilePhone(mobilePhone)
            .withWorkPhone(workPhone).withEmail(email).withEmail2(email2)
            .withEmail3(email3).withAddress(address);
  }


}

