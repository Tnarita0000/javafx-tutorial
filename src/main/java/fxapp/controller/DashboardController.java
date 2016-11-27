package fxapp.controller;
import javafx.scene.Parent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import fxapp.controller.dashboard.*;
import javafx.scene.control.ListView;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ResourceBundle;
import javafx.scene.control.ComboBox;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.List;
import fxapp.manager.*;
import fxapp.controller.dashboard.TablesListController;

public class DashboardController implements Initializable {
  @FXML
  ComboBox<String> databaseComboBox;
  @FXML
  ImageView queryConsoleImage;
  @FXML
  ImageView rowImage;
  @FXML
  TablesListController tablesListController;

  public void selectDatabase(ActionEvent e) {
    String databaseName = databaseComboBox.getValue().toString();
    MySQLManager.connector.connectDatabase(databaseName);
    tablesListController.setTables(databaseName);
  }

  public void selectConsole(ActionEvent e) {
    tablesListController.set();
  }

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    setDatabases();
    setIcons();
  }

  private void setDatabases() {
    List<String> databaseList = MySQLSearch.query("SHOW DATABASES", "Database");
    for(String database : databaseList) {
      databaseComboBox.getItems().add(database);
    }
  }

  private void setIcons() {
    String imagePath = "/fxapp/images/";

    URL consoleIconLocation = getClass().getResource(imagePath + "query_console_icon.png");
    Image queryIcon = new Image(consoleIconLocation.toString(), true);
    queryConsoleImage.setImage(queryIcon);

    URL rowIconLocation = getClass().getResource(imagePath + "rows_icon.png");
    Image rowIcon = new Image(rowIconLocation.toString(), true);
    rowImage.setImage(rowIcon);
  }

}
