package classes;
import java.util.Properties;
import java.util.*;
import java.sql.*;
import com.jcraft.jsch.*;

public class MySQLConnector {
  public Connection con;

  public void connectMysql() {
    try {
      this.con = DriverManager.getConnection(
        "jdbc:mysql://"+ MySQLSetting.hostname, MySQLSetting.username, MySQLSetting.password
      );
      System.out.println("mysql connected !!!");
    } catch (SQLException e) { e.printStackTrace(); }
  }

  public void connectDatabase(String databaseName) {
    try {
      this.con = DriverManager.getConnection(
        "jdbc:mysql://" + MySQLSetting.hostname + "/" + databaseName,
        MySQLSetting.username,
        MySQLSetting.password
      );
      System.out.println("databasse connected !!!");
    } catch (SQLException e) { e.printStackTrace(); }
  }

  public void doSSHForward(String sshHostname, String sshUsername, String sshPassword, int sshPort) {
    JSch jsch         = new JSch();
    Properties config = new Properties();
    config.put("StrictHostKeyChecking", "no");
    try {
      Session session = jsch.getSession(sshUsername, sshHostname, sshPort);
      session.setConfig(config);
      session.setPassword(sshPassword);
      session.connect();
      session.setPortForwardingL(MySQLSetting.port, sshHostname, MySQLSetting.port);
      System.out.println("ssh connected !!!");
    } catch (Exception e) { e.printStackTrace(); }
  }
} 
