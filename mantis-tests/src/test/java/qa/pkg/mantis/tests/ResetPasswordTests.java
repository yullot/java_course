package qa.pkg.mantis.tests;

import org.testng.annotations.Test;
import qa.pkg.mantis.appmanager.HttpSession;
import qa.pkg.mantis.model.MailMessage;
import ru.lanwen.verbalregex.VerbalExpression;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ResetPasswordTests extends TestBase {
  @Test
  public void testResetPassword() throws  MessagingException {
    String userName="user1608240786624";
    String password="password";
    String email;

    app.loginHelper().login("administrator","root");
    app.resetPassword().openUserDetailsForm(userName);
    email=app.resetPassword().getEmail();
    app.james().doesUserExist(userName);
    System.out.println("!!!!Email "+email);
    app.resetPassword().clickResetBtn();
     List<MailMessage> mailMessages = app.james().waitForMail(userName, password, 60000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    System.out.println("Link "+confirmationLink);
// app.resetPassword().changePassword(confirmationLink, password);
//    assertTrue(session.login(userName, password));
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage=mailMessages.stream().filter((m)->m.toWhom.equals(email)).findFirst().get();
    VerbalExpression regex= VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }
}
