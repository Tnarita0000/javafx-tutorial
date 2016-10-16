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
  ComboBox databasesComboBox;
  @FXML
  ListView tables;
  @FXML
  TableView columns;

  public void selectDatabase(ActionEvent e) {
    String dbName = databasesComboBox.getValue().toString();
    MySQLManager.connector.connectDatabase(dbName);

    /* set table list after cleared tables list in ListView */
    tables.setItems(FXCollections.observableArrayList());
    List<String> queryResult = MySQLSearch.query("SHOW TABLES", "Tables_in_"+dbName);
    for(String table : queryResult) {
      tables.getItems().add(table);
    }
  }

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    /* set database list to ListView */
    databasesComboBox.setItems(FXCollections.observableArrayList());
    List<String> databaseList = MySQLSearch.query("SHOW DATABASES", "Database");
    for(String database : databaseList) {
      databasesComboBox.getItems().add(database);
    }

    /* set TableView after clicked table name in ListView */
    tables.setOnMouseClicked((MouseEvent)-> {
      String tableName = tables.getSelectionModel().getSelectedItem().toString();
      List<String> columnList = MySQLSearch.query("SHOW COLUMNS FROM "+tableName, "Field");
      List<String> contentList= MySQLSearch.query("SELECT * FROM "+tableName);
      for(String column : columnList) {
        System.out.println(column);
        TableColumn<Void, Void> col = new TableColumn<>(column);
        columns.getColumns().add(col);
      }
    });
  }

}
