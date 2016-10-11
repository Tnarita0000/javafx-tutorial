package classes;
import java.util.Properties;
import java.util.*;
import java.sql.*;
import com.jcraft.jsch.*;

public class MySQLManager {
  private static Connection   connection;
  private static List<String> databases;
  private static List<String> tables;

  public void setConnection(Connection con) {
    connection = con;
  }

  public void setDatabases(List<String> databases) {
    databases = new ArrayList<String>();
    databases = databases;

  }

  public void setTables(List<String> tables) {
    tables = new ArrayList<String>();
    tables = tables;
  }

}
