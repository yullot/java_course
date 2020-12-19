package qa.pkg.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.MatchResult;

public class ApplicationManager {
  private final Properties properties;
  private WebDriver wd;
  String browser;
  private RegistrationHelper registrationHelper;
  private FtpHelper ftp;
  private MailHelper mail;
  private JamesHelper jamesHelper;
  private ResetPasswordHelper resetPasswordHelper;
  private LoginHelper loginHelper;

  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
  }

  public void stop() {
    if (wd != null) {
      wd.quit();
    }
  }

  public HttpSession newSession() {
    return new HttpSession(this);
  }

  public String getProperty(String key) {
    return properties.getProperty(key);
  }

  public RegistrationHelper signUp() {
    if (registrationHelper == null) {
      registrationHelper = new RegistrationHelper(this);
    }
    return registrationHelper;
  }

  public ResetPasswordHelper resetPassword() {
    if (resetPasswordHelper == null) {
      resetPasswordHelper = new ResetPasswordHelper(this);
    }
    return resetPasswordHelper;
  }

  public LoginHelper loginHelper() {
    if (loginHelper == null) {
      loginHelper = new LoginHelper(this);
    }
    return loginHelper;
  }

  public FtpHelper ftp() {
    if (ftp == null) {
      ftp = new FtpHelper(this);
    }
    return ftp;
  }

  public MailHelper mail() {
    if (mail == null) {
      mail = new MailHelper(this);
    }
    return mail;
  }

  public JamesHelper james() {
    if (jamesHelper == null) {
      jamesHelper = new JamesHelper(this);
    }
    return jamesHelper;
  }

  public WebDriver getDriver() {
    if (wd == null) {
      if (browser.equals(BrowserType.FIREFOX)) {
        wd = new FirefoxDriver();
      } else if (browser.equals(BrowserType.CHROME)) {
        wd = new ChromeDriver();
      }
      //wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
      wd.get(properties.getProperty("web.baseUrl"));
    }
    return wd;
  }
}
