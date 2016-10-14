package sample;
import java.net.Socket;
import java.util.Properties;
import java.util.*;
import java.sql.*;
import com.jcraft.jsch.*;
import org.apache.commons.lang3.StringUtils;

public class MySQLConnector {
  private static boolean sshFlag = false;
  public Connection con;
  public String tunnelPort;

  public void connectMysql() {
    String targetHost = ""; 
    String targetPort = "";
    if(sshFlag) {
      targetPort = this.tunnelPort;
      targetHost = "localhost";
    } else {
      targetPort = String.valueOf(MySQLSetting.port);
      targetHost = MySQLSetting.hostname;
    }
    try {
      this.con = DriverManager.getConnection(
        "jdbc:mysql://"+targetHost+":"+targetPort+"/", MySQLSetting.username, MySQLSetting.password
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
    sshFlag           = true;
    this.tunnelPort   = unUsedPort();
    JSch jsch         = new JSch();
    Properties config = new Properties();
    config.put("StrictHostKeyChecking", "no");
    try {
      Session session = jsch.getSession(sshUsername, sshHostname, sshPort);
      session.setConfig(config);
      session.setPassword(sshPassword);
      session.connect();
      session.setPortForwardingL(Integer.parseInt(this.tunnelPort), sshHostname, MySQLSetting.port);
      System.out.println("ssh connected !!!");
    } catch (Exception e) { e.printStackTrace(); }
  }

  private String unUsedPort() {
    Socket sock       = null;
    String unUsedPort = null; 
    for (int port=1024; port<1124; port++) {
      try{
        sock = new Socket("localhost", port);
        sock.close();
      } catch( Exception e ) {
        unUsedPort = String.valueOf(port);
        break;
      }
    }
    return unUsedPort;
  }
} 
