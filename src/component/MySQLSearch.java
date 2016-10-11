package classes;
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

}
