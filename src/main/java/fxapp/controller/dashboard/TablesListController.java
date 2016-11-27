package fxapp.controller.dashboard;
import fxapp.controller.DashboardController;
import fxapp.controller.dashboard.ToolAreaController;
import fxapp.manager.MySQLSearch;
import javafx.scene.control.ListView;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TablesListController implements Initializable {
  @FXML
  public ListView<String> tableList;
  @FXML
  public ToolAreaController toolAreaController;

  public void setTables(String databaseName) {
    tableList.getItems().clear();
    List<String> queryResult = MySQLSearch.query("SHOW TABLES", "Tables_in_"+databaseName);
    for(String table : queryResult) {
      tableList.getItems().add(table);
    }
  }

  public void set() {
    toolAreaController.queryArea.setVisible(true);
    toolAreaController.queryArea.setManaged(true);
  }

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    tableList.setOnMouseClicked((MouseEvent)-> {
      String tableName = tableList.getSelectionModel().getSelectedItem().toString();
      toolAreaController.update(tableName);
    });
  }
}
