package qa.pkg.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import qa.pkg.addressbook.model.GroupData;

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

  public void selectGroup() {
    click(By.name("selected[]"));
  }

  public void returnToGroupPage() {
    click(By.linkText("group page"));
  }

  public void clickEditGroupBtn() {
    click(By.name("edit"));
  }

  public void clickUpdateBtn() {
    click(By.name("update"));
  }

  public void createGroup(GroupData group) {
    initGroupCreation();
    fillGroupForm(group);
    click(By.name("submit"));
    returnToGroupPage();
  }

  public boolean isThereAGroup() {
    return isElementPresent(By.name("selected[]"));
  }
}
