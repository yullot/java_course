package qa.pkg.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import qa.pkg.addressbook.model.GroupData;
import qa.pkg.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  Logger logger= LoggerFactory.getLogger(GroupCreationTests.class);

  @DataProvider
  public Iterator<Object[]> validGroupsAsXml() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader
            (new File("src/test/resources/group.xml")))) {
      String line = reader.readLine();
      String xml = "";
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(GroupData.class);
      List<GroupData> groups = (List<GroupData>) xstream.fromXML(xml);
      return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validGroupsAsJson() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader
            (new File("src/test/resources/group.json")))) {
      String line = reader.readLine();
      String json = "";
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>() {
      }.getType());
      return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }

  @Test(dataProvider = "validGroupsAsJson")
  public void testGroupCreation(GroupData group) {
    logger.info("Start testGroupCreation");
    app.goTo().groupsPage();
    Groups before = app.group().all();
    //GroupData group = new GroupData().withGroupName("testGroup2").withHeader("header").withFooter("footer");
    app.group().createGroup(group);
    assertThat(app.group().count(), equalTo(before.size() + 1));
    Groups after = app.group().all();
    assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    logger.info("Stop testGroupCreation");
  }

  @Test
  public void testBadGroupCreation() {
    app.goTo().groupsPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().withGroupName("test'").withHeader("header").withFooter("footer");
    app.group().createGroup(group);
    assertThat(app.group().count(), equalTo(before.size()));
    Groups after = app.group().all();
    assertThat(after, equalTo(before));
  }
}
