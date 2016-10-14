package sample;
import java.util.*;
import java.util.Properties;
import java.sql.*;
import com.jcraft.jsch.*;
public class MySQLSearch {

  public static List<String> getDatabases(Connection con) {
    List<String> databases;
    databases = new ArrayList<String>();
    try {
      PreparedStatement statement = con.prepareStatement("show databases;");
      ResultSet result = statement.executeQuery();
      while(result.next()) {
        String database = result.getString("Database");
        databases.add(database);
      }
    } catch (SQLException e) { e.printStackTrace(); }
    return databases;
  }

  public static List<String> getTables(Connection con, String databaseName) {
    List<String> tables;
    tables = new ArrayList<String>();
    try{
      PreparedStatement statement = con.prepareStatement("show tables");
      ResultSet result = statement.executeQuery();
      while(result.next()) {
        String table = result.getString("Tables_in_" + databaseName);
        tables.add(table);
      }
    } catch (SQLException e) { e.printStackTrace(); }
    return tables;
  }

}
