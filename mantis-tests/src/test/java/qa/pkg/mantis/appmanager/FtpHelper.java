package qa.pkg.mantis.appmanager;

import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FtpHelper {
  private ApplicationManager app;
  private FTPClient ftp;

  public FtpHelper(ApplicationManager app) {
    this.app = app;
    ftp = new FTPClient();
  }

  public void upload(File file, String targetFileName, String backupFileName) throws IOException {
    ftp.connect(app.getProperty("ftp.host"));
    ftp.login(app.getProperty("ftp.login"),app.getProperty("ftp.password"));
    ftp.deleteFile(backupFileName);
    ftp.rename(targetFileName,backupFileName);
    ftp.enterLocalPassiveMode();
    ftp.storeFile(targetFileName, new FileInputStream(file));
    ftp.disconnect();
  }

  public void restore(String backupFileName, String targetFileName) throws IOException {
    ftp.connect(app.getProperty("ftp.host"));
    ftp.login(app.getProperty("ftp.login"),app.getProperty("ftp.password"));
    ftp.deleteFile(targetFileName);
    ftp.rename(backupFileName,targetFileName);
    ftp.disconnect();
    }

}

