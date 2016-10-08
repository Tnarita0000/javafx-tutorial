package classes;
import java.util.Properties;
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

  public MysqlConnector( String name, String hostname,    String username,    String password,
                  int port,    String sshHostname, String sshPassword, String sshUsername,
                  int sshPort
                )
  {
    this.name = name; this.hostname = hostname;       this.username = username;       this.password = password;
    this.port = port; this.sshHostname = sshHostname; this.sshPassword = sshPassword; this.sshPort = sshPort;
    this.sshUsername = sshUsername;

    try {
      final JSch jsch         = new JSch();
      final Properties config = new Properties();
      config.put("StrictHostKeyChecking", "no");
      Session session = jsch.getSession(sshUsername, sshHostname, sshPort);
      session.setConfig(config);
      session.setPassword(sshPassword);
      session.connect();
      session.setPortForwardingL(3036, sshHostname, port);
      System.out.println("succesfull");
    } catch (Exception e){
      e.printStackTrace();
    } 
  }
} 
