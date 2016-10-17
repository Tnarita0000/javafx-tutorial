package sample;
import java.util.*;
import java.util.Properties;
import java.sql.*;
import com.jcraft.jsch.*;
import com.mysql.jdbc.JDBC42ResultSet;

public class MySQLSearch {

  public static List<String> query(String query, String columnName) {
    List<String> results       = new ArrayList<String>();
    ResultSet queryResult      = null;
    try {
      PreparedStatement statement = MySQLManager.connector.con.prepareStatement(query);
      queryResult                 = statement.executeQuery();
      while(queryResult.next()) {
        results.add(queryResult.getString(columnName));
      }
    } catch (SQLException e) { e.printStackTrace(); }
    return results;
  }

  public static ArrayList<ArrayList<String>> query(String query) {
    ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();
    ResultSet queryResult                = null;
    ResultSetMetaData metaData           = null;
    try {
      PreparedStatement statement = MySQLManager.connector.con.prepareStatement(query);
      queryResult                 = statement.executeQuery();
      metaData                    = statement.getMetaData();
      int columnCount             = metaData.getColumnCount();

      while(queryResult.next()) {
        ArrayList<String> record = new ArrayList<String>();
        for(int i=1; i <= columnCount; i++) {
          record.add(queryResult.getString(metaData.getColumnName(i)));
        }
        results.add(record);
      }
    } catch (SQLException e) { e.printStackTrace(); }
    return results;
  }
}
