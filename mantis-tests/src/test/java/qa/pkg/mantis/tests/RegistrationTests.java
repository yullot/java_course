package qa.pkg.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.pkg.mantis.model.MailMessage;
import ru.lanwen.verbalregex.VerbalExpression;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTests extends TestBase {
//  @BeforeMethod
//  public void startMailServer() {
//    app.mail().start();
//  }

  @Test
  public void testRegistration() throws IOException, MessagingException, InterruptedException {
    long now = System.currentTimeMillis();
    String email = String.format("user%s@localhost", now);
    String user = String.format("user%s", now);

    String password = "password";
    app.james().createUser(user, password);
    app.signUp().start(user, email);
    //List<MailMessage> mailMessages=app.mail().waitForMail(2, 1000);
    List<MailMessage> mailMessages = app.james().waitForMail(user, password, 60000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    app.signUp().finish(confirmationLink, password);
    assertTrue(app.newSession().loginHttpSession(user, password));
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.toWhom.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

//  @AfterMethod(alwaysRun = true)
//  public void stopMailServer() {
//    app.mail().stop();
//  }
}
