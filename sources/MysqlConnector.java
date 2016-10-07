//package classes;

import java.sql.*;
import com.jcraft.jsch.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class MysqlConnector {
  private static void doSshTunnel( 
    String strSshUser,
    String strSshPassword,
    String strSshHost,
    int nSshPort,
    String strRemoteHost,
    int nLocalPort, int nRemotePort
  ) throws JSchException {

    final JSch jsch = new JSch();
    Session session = jsch.getSession( strSshUser, strSshHost, 443 );
    session.setPassword( strSshPassword );

    final Properties config = new Properties();
    config.put( "StrictHostKeyChecking", "no" );
    session.setConfig( config );

    session.connect();
    session.setPortForwardingL(nLocalPort, strRemoteHost, nRemotePort);
    System.out.println("connected");
  }

  public static void main(String[] args)
  {
    try
    {
      String strSshUser = "naritatakuya";                  // SSH loging username
      String strSshPassword = "6541takuya";                   // SSH login password
      String strSshHost = "219.94.251.254";          // hostname or ip or SSH server
      int nSshPort = 443;                                    // remote SSH host port number
      String strRemoteHost = "219.94.251.254";  // hostname or ip of your database server
      int nLocalPort = 3036;                                // local port number use to bind SSH tunnel
      int nRemotePort = 3306;                               // remote port number of your database 
      String strDbUser = "techuser";                    // database loging username
      String strDbPassword = "h0use51551";                    // database login password

      MysqlConnector.doSshTunnel(strSshUser, strSshPassword, strSshHost, nSshPort, strRemoteHost, nLocalPort, nRemotePort);

      Connection con = DriverManager.getConnection("jdbc:mysql://219.94.251.254/jeek_development", strDbUser, strDbPassword);
      String sql = "SELECT * FROM users";
      PreparedStatement statement = con.prepareStatement(sql);
      ResultSet result = statement.executeQuery();
      if (result.next()) {
        System.out.println("ID:" + result.getString(1));
      }

      System.out.println(result);
      System.out.println(con);
      con.close();
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }
    finally
    {
      System.exit(0);
    }
  }
}
