package sample;
import java.net.URL;
import java.sql.*;
import java.io.IOException;
import java.util.*;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class MySQLDBController implements Initializable{
  @FXML
  ComboBox databaseComboBox;
  @FXML
  ListView tableList;
  @FXML
  TableView columnTable;

  public void selectDatabase(ActionEvent e) {
    String dbName = databaseComboBox.getValue().toString();
    MySQLManager.connector.connectDatabase(dbName);

    /* set table list after cleared tables list in ListView */
    tableList.setItems(FXCollections.observableArrayList());
    List<String> queryResult = MySQLSearch.query("SHOW TABLES", "Tables_in_"+dbName);
    for(String table : queryResult) {
      tableList.getItems().add(table);
    }
  }

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    /* set database list to ListView */
    databaseComboBox.setItems(FXCollections.observableArrayList());
    List<String> databaseList = MySQLSearch.query("SHOW DATABASES", "Database");
    for(String database : databaseList) {
      databaseComboBox.getItems().add(database);
    }

    /* when clicked table name in ListView */
    tableList.setOnMouseClicked((MouseEvent)-> {
      /* set column name */
      String tableName = tableList.getSelectionModel().getSelectedItem().toString();
      List<String> columnList = MySQLSearch.query("SHOW COLUMNS FROM "+tableName, "Field");
      ObservableList<Column> columns = FXCollections.observableArrayList();
      for(String column : columnList) {
        columns.add(new Column(column));
      }
      columnTable.setItems(columns);

      /* set content on any column in TableView */
      ArrayList<ArrayList<String>> recordList = MySQLSearch.query("SELECT * FROM "+tableName);
      for(int rowCount=0; rowCount < recordList.size(); rowCount++) {
        for(int columnIndex=0; columnIndex < recordList.get(rowCount).size(); columnIndex++) {
          System.out.println(recordList.get(rowCount).get(columnIndex));
        }
      }
    });
  }
}
