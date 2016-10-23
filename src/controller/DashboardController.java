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
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.util.Callback;
import javafx.beans.value.ObservableValue;
import javafx.beans.property.SimpleStringProperty;

public class DashboardController implements Initializable{
  @FXML
  ComboBox<String> databaseComboBox;
  @FXML
  ListView<String> tableList;
  @FXML
  TableView<Record> columnTable;
  @FXML
  ImageView queryConsoleImage;
  @FXML
  ImageView rowImage;

  public void selectDatabase(ActionEvent e) {
    String dbName = databaseComboBox.getValue().toString();
    MySQLManager.connector.connectDatabase(dbName);

    tableList.getItems().clear();
    columnTable.getColumns().clear();
    columnTable.getItems().clear();
    /* set table list after cleared tables list in ListView */
    List<String> queryResult = MySQLSearch.query("SHOW TABLES", "Tables_in_"+dbName);
    for(String table : queryResult) {
      tableList.getItems().add(table);
    }
  }

  public void selectConsole(ActionEvent e) {
    StageComponent.sendScene("QueryConsoleController");
  }

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    /* set database list to ListView */
    List<String> databaseList = MySQLSearch.query("SHOW DATABASES", "Database");
    for(String database : databaseList) {
      databaseComboBox.getItems().add(database);
    }

    /* when clicked table name in ListView */
    tableList.setOnMouseClicked((MouseEvent)-> {
      columnTable.getColumns().clear();
      columnTable.getItems().clear();
      String tableName = tableList.getSelectionModel().getSelectedItem().toString();
      setContents(tableName);
    });

    setIcons();
  }

  public void setContents(String tableName) {
    /* set columns */
    List<String> columns = MySQLSearch.query("SHOW COLUMNS FROM "+tableName, "Field");
    for(String column : columns) {
      TableColumn<Record, String> tableColumn = new TableColumn<Record, String>(column);
      tableColumn.setPrefWidth(100);
      tableColumn.setCellValueFactory(new Callback<CellDataFeatures<Record, String>, ObservableValue<String>>() {
        @Override
        public ObservableValue<String> call(CellDataFeatures<Record, String> p) {
          return new SimpleStringProperty(p.getValue().getData(column));
        }
      });
      columnTable.getColumns().add(tableColumn);
    }

    /* set records */
    ArrayList<ArrayList<String>> recordList = MySQLSearch.query("SELECT * FROM "+tableName);
    for(int rowCount=0; rowCount < recordList.size(); rowCount++) {
      Map<String, String> tableRecord = new HashMap<String, String>();
      for(int index=0; index<columns.size(); index++) {
        tableRecord.put(columns.get(index), recordList.get(rowCount).get(index));
      }
      columnTable.getItems().add(new Record().setData(tableRecord));
    }
  }

  public void setIcons() {
    URL consoleIconLocation = getClass().getResource("../public/images/query_console_icon.png");
    Image queryIcon = new Image(consoleIconLocation.toString(), true);
    queryConsoleImage.setImage(queryIcon);

    URL rowIconLocation = getClass().getResource("../public/images/rows_icon.png");
    Image rowIcon = new Image(rowIconLocation.toString(), true);
    rowImage.setImage(rowIcon);
  }
}
