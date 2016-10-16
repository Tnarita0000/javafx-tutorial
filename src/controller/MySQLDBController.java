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
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class MySQLDBController implements Initializable{
  @FXML
  ComboBox databases;
  @FXML
  ListView tables;

  public void selectDatabase(ActionEvent e) {
    String dbName = databases.getValue().toString();
    MySQLManager.connector.connectDatabase(dbName);

    /* clear ListView data */
    tables.setItems(FXCollections.observableArrayList());
    List<String> queryResult = MySQLSearch.getTables(dbName);
    for(String table : queryResult) {
      tables.getItems().add(table);
    }

  }

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    databases.setItems(FXCollections.observableArrayList());
    List<String> queryResult = MySQLSearch.getDatabases();
    for(String database : queryResult) {
      databases.getItems().add(database);
    }
  }


}
