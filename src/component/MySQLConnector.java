package classes;

import java.util.Properties;
import java.util.*;
import java.sql.*;
import com.jcraft.jsch.*;

public class MySQLConnector {
  private String hostname;
  private String username;
  private String password;
  private int port;
  private String sshUsername;
  private String sshHostname;
  private String sshPassword;
  private int sshPort;

  public static Connection con;
  public static MySQLConnector instance;

  public MySQLConnector( String hostname,    String username,    String password, int port,
      String sshHostname, String sshUsername, String sshPassword, int sshPort) {
    this.hostname = hostname;
    this.username = username;
    this.password = password;
    this.port = port;
    this.sshHostname = sshHostname;
    this.sshPassword = sshPassword;
    this.sshPort = sshPort;
    this.sshUsername = sshUsername;
    this.instance = this;
    try {
      doSshForward();
      connectMysql();
    } catch (Exception e) { e.printStackTrace(); }
  }

  private void connectMysql() {
    try {
      this.con = DriverManager.getConnection(
          "jdbc:mysql://"+this.hostname,  this.username, this.password
          );
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void connectDatabase(String databaseName) {
    try {
      this.con = DriverManager.getConnection(
          "jdbc:mysql://" + this.hostname + "/" + databaseName,
          this.username,
          this.password
          );
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private void doSshForward() {
    try {
      final JSch jsch         = new JSch();
      final Properties config = new Properties();
      config.put("StrictHostKeyChecking", "no");
      Session session = jsch.getSession(sshUsername, sshHostname, sshPort);
      session.setConfig(config);
      session.setPassword(sshPassword);
      session.connect();
      session.setPortForwardingL(port, sshHostname, port);
      System.out.println("connected !!!");
    } catch (Exception e) { e.printStackTrace(); }
  }
} 
