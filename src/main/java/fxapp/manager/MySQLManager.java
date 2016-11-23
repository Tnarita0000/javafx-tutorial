package fxapp.manager;
import java.util.Properties;
import java.util.*;
import java.sql.*;
import com.jcraft.jsch.*;
import org.apache.commons.lang3.StringUtils;

/** --- example ---------------------------------------------------------------
 *  MySQLManager manager = new MySQLManager();
 *  manager.setMySQLInfo(str host, str usename, str pass, int port);
 *  manager.setSSHInfo(str host, str username, str pass, int port);
 *  manager.setConnection();
 *  ---------------------------------------------------------------------------
**/
public class MySQLManager {
  public static  MySQLConnector connector;
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
    return (
      StringUtils.isNotBlank(MySQLSetting.sshUsername) &&
      StringUtils.isNotBlank(MySQLSetting.sshHostname) &&
      StringUtils.isNotBlank(MySQLSetting.sshPassword) &&
      MySQLSetting.sshPort != 0
    );
  }

  private boolean isSetedMySQLInfo() {
    return (
      StringUtils.isNotBlank(MySQLSetting.username) &&
      StringUtils.isNotBlank(MySQLSetting.hostname) &&
      StringUtils.isNotBlank(MySQLSetting.password) &&
      MySQLSetting.port != 0
    );
  }

  public boolean hasConnection() {
    return (connector.con != null);
  }
}
