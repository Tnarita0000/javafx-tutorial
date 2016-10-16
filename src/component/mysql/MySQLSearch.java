package sample;
import java.util.*;
import java.util.Properties;
import java.sql.*;
import com.jcraft.jsch.*;
import com.mysql.jdbc.JDBC42ResultSet;

public class MySQLSearch {
  static MySQLConnector connector = MySQLManager.connector;

  public static ResultSet query(String query) {
    ResultSet queryResult = null;
    try {
      PreparedStatement statement = connector.con.prepareStatement(query);
      queryResult = statement.executeQuery();
    } catch (SQLException e) { e.printStackTrace(); }
    return queryResult;
  }

  public static List<String> getDatabases() {
    List<String> databases = new ArrayList<String>();
    try {
      PreparedStatement statement = connector.con.prepareStatement("SHOW DATABASES");
      ResultSet queryResult = statement.executeQuery();
      while(queryResult.next()) {
        String database = queryResult.getString("Database");
        databases.add(database);
      }
    } catch (SQLException e) { e.printStackTrace(); }
    return databases;
  }

  public static List<String> getTables(String databaseName) {
    List<String> tables = new ArrayList<String>();
    try {
      PreparedStatement statement = connector.con.prepareStatement("SHOW TABLES");
      ResultSet queryResult = statement.executeQuery();
      while(queryResult.next()) {
        String table = queryResult.getString("Tables_in_" + databaseName);
        tables.add(table);
      }
    } catch (SQLException e) { e.printStackTrace(); }
    return tables;
  }
}
