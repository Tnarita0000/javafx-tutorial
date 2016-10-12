package classes;
import java.util.Properties;
import java.util.*;
import java.sql.*;
import com.jcraft.jsch.*;

/** --- example ---------------------------------------------------------------
 *  MySQLManager manager = new MySQLManager();
 *  manager.setMySQLInfo(str host, str usename, str pass, int port);
 *  manager.setSSHInfo(str host, str username, str pass, int port);
 *  manager.setConnection();
 *  ---------------------------------------------------------------------------
**/
public class MySQLManager {
  static  MySQLConnector connector;
  private List<String> databases;
  private List<String> tables;

  /* Constructor */
  public MySQLManager() {
    connector = new MySQLConnector();
  }

  public void setConnection() {
    if (isSetedSSHInfo()) {
      connector.doSSHForward(MySQLSetting.sshHostname, MySQLSetting.sshUsername, MySQLSetting.sshPassword, MySQLSetting.sshPort);
    }
    if (isSetedMySQLInfo()) {
      connector.connectMysql();
      connector.connectDatabase("jeek_development");
    }
  }

  public static Connection getConnection() {
    return connector.con;
  }

  public void setSSHInfo(String sshHostname, String sshUsername, String sshPassword, int sshPort) {
    MySQLSetting.sshHostname = sshHostname;
    MySQLSetting.sshUsername = sshUsername;
    MySQLSetting.sshPassword = sshPassword;
    MySQLSetting.sshPort     = sshPort;
  }

  public void setMySQLInfo(String hostname, String username, String password, int port) {
    MySQLSetting.hostname = hostname;
    MySQLSetting.username = username;
    MySQLSetting.password = password;
    MySQLSetting.port     = port;
  }

  private boolean isSetedSSHInfo() {
    return !(MySQLSetting.sshUsername == null || MySQLSetting.sshHostname == null || MySQLSetting.sshPassword == null || MySQLSetting.sshPort == 0);
  }

  private boolean isSetedMySQLInfo() {
    return !(MySQLSetting.username == null || MySQLSetting.hostname == null || MySQLSetting.password == null || MySQLSetting.port == 0);
  }

  private void setDatabases(List<String> databases) {
    this.databases = new ArrayList<String>();
    this.databases = databases;
  }

  private void setTables(List<String> tables) {
    this.tables = new ArrayList<String>();
    this.tables = tables;
  }
}
