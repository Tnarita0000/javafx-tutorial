package sample;
import java.net.URL;
import java.sql.*;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.*;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class MySQLDBController implements Initializable{
  @FXML
  ComboBox databases;
  @FXML
  ListView tables;
  @FXML
  TableView columns;

  public void selectDatabase(ActionEvent e) {
    String dbName = databases.getValue().toString();
    MySQLManager.connector.connectDatabase(dbName);

    /* set table list after cleared tables list in ListView */
    tables.setItems(FXCollections.observableArrayList());
    List<String> queryResult = MySQLSearch.getTables(dbName);
    for(String table : queryResult) {
      tables.getItems().add(table);
    }
  }


  @Override
  public void initialize(URL url, ResourceBundle rb) {
    /* set databases list to ListView */
    databases.setItems(FXCollections.observableArrayList());
    List<String> queryResult = MySQLSearch.getDatabases();
    for(String database : queryResult) {
      databases.getItems().add(database);
    }

    /* after clicked table in ListView */
    tables.setOnMouseClicked((MouseEvent)-> {
      String tableName = tables.getSelectionModel().getSelectedItem().toString();
      ResultSet columnsName    = MySQLSearch.query("SHOW COLUMNS FROM "+tableName);
      ResultSet columnsContens = MySQLSearch.query("SELECT * FROM     "+tableName);

      while(columnsName.next()) {
        String columnName = columnsName.getString("Field");
        System.out.println(columnsName);
      }
      //columns.getColumns.add
    });
  }

}
