package ru.stqa.pft.mantis.appmanager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;

public class FtpHelper {
   private  final ApplicationManager app;
   private FTPClient ftp;

   public FtpHelper(ApplicationManager app) {
      this.app = app;
      ftp = new FTPClient();
   }

   public void upload(File file, String target, String backup) throws IOException {
      ftp.connect(app.getProperty("ftp.host"));
      ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password"));
      /* из скайковского чата, инфа от Alexei :
      относится к MacOS!!
надо просто добавить права доступа
chmod -R 755 <путь к папке с приложением>

(можно и через Finder поменять, если в терминале неудобно)
      а ещё лучше -- предоставьте права доступа
chmod a+w /Applications/XAMPP/xamppfiles/htdocs/mantisbt-2.24.2/config/
и ещё раз запустите инсталлятор, он сам создаст файл config_inc.php (сейчас он у вас не может этого сделать, потому что прав не хватает)
      */
      ftp.deleteFile(backup);
      ftp.rename(target, backup);
      ftp.enterLocalPassiveMode();
      ftp.storeFile(target, new FileInputStream(file));
      // ftpClient.sendSiteCommand("chmod 755 config_inc.php");
      ftp.disconnect();
   }

   public void restore(String backup, String target) throws IOException {
      ftp.connect(app.getProperty("ftp.host"));
      ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password"));
      ftp.deleteFile(target);
      ftp.rename(backup, target);
      ftp.disconnect();
   }
}
