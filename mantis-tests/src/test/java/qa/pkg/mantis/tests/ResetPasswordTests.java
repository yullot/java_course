package qa.pkg.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.pkg.mantis.model.MailMessage;
import ru.lanwen.verbalregex.VerbalExpression;

import java.util.List;
import java.util.stream.Collectors;

import static org.testng.Assert.assertTrue;

public class ResetPasswordTests extends TestBase {
  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testResetPassword() {
    long now = System.currentTimeMillis();
    String userName = app.db().users().stream().filter((d) -> d.getId() > 1).collect(Collectors.toList()).iterator().next().getUsername();
    String newPassword = String.format("new%s", now);
    String email = app.db().users().stream().filter((d) -> d.getId() > 1).collect(Collectors.toList()).iterator().next().getEmail();
    logger.info("**** User " + userName + " and email " + email);
    app.loginHelper().login("administrator", "root");
    app.resetPassword().openUserDetailsForm(userName);
    app.resetPassword().clickResetBtn();
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 1000);
    //app.james().doesUserExist(userName);
    //app.james().drainEmail(userName, password);
    // List<MailMessage> mailMessages = app.james().waitForMail(userName, password, 60000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    logger.info("**** Link " + confirmationLink);
    app.resetPassword().changePassword(confirmationLink, newPassword);
    assertTrue(app.loginHelper().isLoggedInAs(userName));
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.toWhom.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }
}
