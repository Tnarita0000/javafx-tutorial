package fxapp.controller.dashboard;
import fxapp.controller.DashboardController;
import fxapp.controller.dashboard.RecordsTableController;
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
  public RecordsTableController recordsTableController;

  public void setTables(String databaseName) {
    tableList.getItems().clear();
    List<String> queryResult = MySQLSearch.query("SHOW TABLES", "Tables_in_"+databaseName);
    for(String table : queryResult) {
      tableList.getItems().add(table);
    }
  }

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    tableList.setOnMouseClicked((MouseEvent)-> {
      String tableName = tableList.getSelectionModel().getSelectedItem().toString();
      recordsTableController.update(tableName);
    });
  }
}
