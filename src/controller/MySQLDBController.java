package sample;
import java.net.URL;
import java.io.IOException;
import java.util.ResourceBundle;
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

  public void pushMe(ActionEvent e) {
    System.out.println("Pushed Me");
  }

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    System.out.println(databases);
    ObservableList<String> options = FXCollections.observableArrayList(
      "Select Database",
      "Option 1",
      "Option 2",
      "Option 3"
    );
    databases.setItems(options);
  }


}
