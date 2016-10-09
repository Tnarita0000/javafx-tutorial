package classes;

import java.util.Properties;
import java.util.*;
import java.sql.*;
import com.jcraft.jsch.*;

public class MysqlConnector {
  String name;
  String hostname;
  String username;
  String password;
  int port;
  String sshUsername;
  String sshHostname;
  String sshPassword;
  int sshPort;
  List<String> tables;

  public MysqlConnector( String name, String hostname,    String username,    String password,
                         int port,    String sshHostname, String sshPassword, String sshUsername,
                         int sshPort) {
    this.name = name; this.hostname = hostname;       this.username = username;       this.password = password;
    this.port = port; this.sshHostname = sshHostname; this.sshPassword = sshPassword; this.sshPort = sshPort;
    this.sshUsername = sshUsername;  this.tables = new ArrayList<String>();

    try {
      final JSch jsch         = new JSch();
      final Properties config = new Properties();
      config.put("StrictHostKeyChecking", "no");
      Session session = jsch.getSession(sshUsername, sshHostname, sshPort);
      session.setConfig(config);
      session.setPassword(sshPassword);
      session.connect();
      session.setPortForwardingL(3036, sshHostname, port);

      Connection con = DriverManager.getConnection("jdbc:mysql://" + hostname, username, password);
      PreparedStatement statement = con.prepareStatement("show databases;");
      ResultSet result = statement.executeQuery();
      while(result.next()) {
        String table = result.getString("Database");
        this.tables.add(table);
      }
    } catch (Exception e) { e.printStackTrace(); } 
  }

  public void getTables() {
    this.tables.forEach(table -> System.out.println(table));
  }
} 
