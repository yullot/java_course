package qa.pkg.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import qa.pkg.addressbook.model.GroupData;
import qa.pkg.addressbook.model.Groups;

import java.util.List;

public class GroupHelper extends HelperBase {

  public GroupHelper(WebDriver wd) {
    super(wd);
  }

  public void fillGroupForm(GroupData groupData) {
    fillInput(By.name("group_name"), groupData.getGroupName());
    fillInput(By.name("group_header"), groupData.getHeader());
    fillInput(By.name("group_footer"), groupData.getFooter());
  }

  public void initGroupCreation() {
    click(By.name("new"));
  }

  public void deleteSelectedGroup() {
    click(By.xpath("(//input[@name='delete'])[1]"));
  }

  public void selectGroup(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  private void selectGroupById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void returnToGroupPage() {
    click(By.linkText("group page"));
  }

  public void clickEditGroupBtn() {
    click(By.name("edit"));
  }

  public void
  clickUpdateBtn() {
    click(By.name("update"));
  }

  public void createGroup(GroupData group) {
    initGroupCreation();
    fillGroupForm(group);
    click(By.name("submit"));
    groupCache = null;
    returnToGroupPage();
  }

  public boolean isThereAGroup() {
    return isElementPresent(By.name("selected[]"));
  }

  /**
   * go to Groups page
   * and if there any existed group=> return group name
   * else return "[none]" value- value from AddNewContactForm
   */
/*  public String getNameGroup() {
    click(By.linkText("groups"));
    if (isThereAGroup()) {
      return wd.findElement(By.xpath("//div[@id='content']/form/span")).getText();
    }
    return "[none]";
  }*/

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public void edit(GroupData group) {
    selectGroupById(group.getId());
    clickEditGroupBtn();
    fillGroupForm(group);
    clickUpdateBtn();
    groupCache = null;
    returnToGroupPage();
  }

  public void delete(GroupData group) {
    selectGroupById(group.getId());
    deleteSelectedGroup();
    groupCache = null;
    returnToGroupPage();
  }

  private Groups groupCache = null;

  public Groups all() {
    if (groupCache != null) {
      return new Groups(groupCache);
    }
    groupCache = new Groups();
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    for (WebElement element : elements) {
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      groupCache.add(new GroupData().withId(id).withGroupName(name));
    }
    return new Groups(groupCache);
  }
}
