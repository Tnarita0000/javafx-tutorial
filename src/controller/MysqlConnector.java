package classes;

import java.util.Properties;
import java.util.*;
import java.sql.*;
import com.jcraft.jsch.*;

public class MysqlConnector {
  String hostname;
  String username;
  String password;
  int port;
  String sshUsername;
  String sshHostname;
  String sshPassword;
  int sshPort;
  static List<String> tables;
  static Connection con;

  public MysqlConnector( String hostname,    String username,    String password, int port,
      String sshHostname, String sshUsername, String sshPassword, int sshPort) {
    this.hostname = hostname;
    this.username = username;
    this.password = password;
    this.port = port;
    this.sshHostname = sshHostname;
    this.sshPassword = sshPassword;
    this.sshPort = sshPort;
    this.sshUsername = sshUsername;
    this.tables = new ArrayList<String>();
    try {
      doSshForward();
      connectMysql();
      getDatabases();
    } catch (Exception e) { e.printStackTrace(); }
  }

  public void getDatabases() {
    try {
      PreparedStatement statement = this.con.prepareStatement("show databases;");
      ResultSet result = statement.executeQuery();
      while(result.next()) {
        String table = result.getString("Database");
        this.tables.add(table);
      }
    } catch (SQLException e) { e.printStackTrace(); }
  }

  private void connectMysql() {
    try {
      this.con = DriverManager.getConnection(
          "jdbc:mysql://127.0.0.1:3306/", this.username, this.password
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

  public void getTables() {
    this.tables.forEach(table -> System.out.println(table));
  }
} 
