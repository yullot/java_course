package qa.pkg.rest.appmanager;

public class ApplicationManager {
  private RestAssuredHelper restAssuredHelper;
  private RestHelper restHelper;


  public RestAssuredHelper rest() {
    if (restAssuredHelper == null) {
      restAssuredHelper = new RestAssuredHelper(this);
    }
    return restAssuredHelper;
  }

  public RestHelper rest0() {
    if (restHelper == null) {
      restHelper = new RestHelper(this);
    }
    return restHelper;
  }
}
