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

  public static List<String> databases() {
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

  //public static String getTable(String databaseName) {
  //  List<String> tables = new ArrayList<String>();
  //  while(result.next()) {
  //    String table = result.getString("Tables_in_" + databaseName);
  //    tables.add(table);
  //  }
  //  return tables;
  //}

}
