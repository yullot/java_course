package qa.pkg.rest.appmanager;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import qa.pkg.rest.model.Issue;

import java.util.ArrayList;
import java.util.Set;

public class RestAssuredHelper {
  private final ApplicationManager app;

  public RestAssuredHelper(ApplicationManager app) {
    this.app = app;
  }

  public Set<Issue> getIssues() {
    String json = RestAssured.get("https://bugify.stqa.ru/api/issues.json").asString();
    JsonElement parsed = JsonParser.parseString(json); //new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
    }.getType());
  }

  public int createIssue(Issue newIssue) {
    String json = RestAssured.given()
            .parameter("subject", newIssue.getSubject())
            .parameter("description", newIssue.getDescription())
            .post("https://bugify.stqa.ru/api/issues.json").asString();
    JsonElement parsedJson = new JsonParser().parse(json);
    return parsedJson.getAsJsonObject().get("issue_id").getAsInt();
  }

  public String getIssueState(int issueId) {
    String json = RestAssured.get("https://bugify.stqa.ru/api/issues/" + issueId + ".json").asString();
    JsonArray issues = JsonParser.parseString(json).getAsJsonObject().getAsJsonArray("issues");
    String stateName = issues.get(0).getAsJsonObject().get("state_name").getAsString();
    return stateName;
  }

  public void updateIssuesState(int issueId) {
    String json = RestAssured.given()
            .parameter("method", "update")
            .parameter("status[state]", "2")
            .parameter("status[assignee]", "")
            .parameter("status[percentage]", "0")
            .parameter("status[comment]", "")
            .post("https://bugify.stqa.ru/api/issues/" + issueId + ".json").asString();
  }

  public ArrayList<String> getIssueIdArray() {
    String json = RestAssured.get("https://bugify.stqa.ru/api/issues.json").asString();
    JsonArray issues = JsonParser.parseString(json).getAsJsonObject().getAsJsonArray("issues");

    ArrayList<String> idArray = new ArrayList<String>();

    for (int i = 0; i < issues.size(); i++) {
      idArray.add(issues.get(i).getAsJsonObject().get("id").getAsString());
      System.out.println("!!! " + i + " -" + idArray.get(i));
    }
    return idArray;
  }
}
