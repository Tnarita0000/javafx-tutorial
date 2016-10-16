package sample;
import java.util.*;
import java.util.Properties;
import java.sql.*;
import com.jcraft.jsch.*;
import com.mysql.jdbc.JDBC42ResultSet;

public class MySQLSearch {
  static MySQLConnector connector = MySQLManager.connector;

  public static List<String> query(String query, String columnName) {
    List<String> results  = new ArrayList<String>();
    ResultSet queryResult = null;
    try {
      PreparedStatement statement = connector.con.prepareStatement(query);
      queryResult = statement.executeQuery();
      while(queryResult.next()) {
        String result = queryResult.getString(columnName);
        results.add(result);
      }
    } catch (SQLException e) { e.printStackTrace(); }
    return results;
  }
}
