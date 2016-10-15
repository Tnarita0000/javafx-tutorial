package sample;
import java.util.*;
import java.util.Properties;
import java.sql.*;
import com.jcraft.jsch.*;
public class MySQLSearch {
  public MySQLConnector connector = MySQLManager.connector;

  //public static JDBC42ResultSet query(String query) {
  //  List<String> result = new ArrayList<String>();
  //  try {
  //    PreparedStatement statement = connector.prepareStatement(query);
  //    ResultSet queryResult       = statement.executeQuery();
  //    while(queryResult.next()) {
  //      String result = queryResult.getString();
  //    }
  //  } catch (SQLException e) { e.printStackTrace(); }
  //}

  //public static List<String> databases() {
  //  List<String> databases = new ArrayList<String>();
  //  while(result.next()) {
  //    String database = result.getString("Database");
  //    databases.add(database);
  //  }
  //  return databases;
  //}

  //public static String getTable(String databaseName) {
  //  List<String> tables = new ArrayList<String>();
  //  while(result.next()) {
  //    String table = result.getString("Tables_in_" + databaseName);
  //    tables.add(table);
  //  }
  //  return tables;
  //}

}
