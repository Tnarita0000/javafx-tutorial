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
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class MySQLDBController implements Initializable{
  @FXML
  ComboBox databases;

  public void selectDatabase(ActionEvent e) {
    System.out.println(databases.getValue());
  }

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    databases.setItems(FXCollections.observableArrayList());
    List<String> queryResult = MySQLSearch.databases();
    for(String database : queryResult) {
      databases.getItems().add(database);
    }
  }


}
