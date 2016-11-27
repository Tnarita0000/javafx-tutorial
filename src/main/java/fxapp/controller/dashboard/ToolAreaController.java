package fxapp.controller.dashboard;
import fxapp.model.Record;
import fxapp.manager.MySQLManager;
import fxapp.manager.MySQLSearch;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import javafx.util.Callback;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.beans.value.ObservableValue;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class ToolAreaController {
  @FXML
  TableView<Record> recordsArea;
  @FXML
  TextArea queryArea;

  public void update(String tableName) {
    recordsArea.getColumns().clear();
    recordsArea.getItems().clear();
    setRecords(tableName);
  }

  private void setRecords(String tableName) {
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
      recordsArea.getColumns().add(tableColumn);
    }

    /* set records */
    ArrayList<ArrayList<String>> recordList = MySQLSearch.query("SELECT * FROM "+tableName);
    for(int rowCount=0; rowCount < recordList.size(); rowCount++) {
      Map<String, String> tableRecord = new HashMap<String, String>();
      for(int index=0; index<columns.size(); index++) {
        tableRecord.put(columns.get(index), recordList.get(rowCount).get(index));
      }
      recordsArea.getItems().add(new Record().setData(tableRecord));
    }
  }
}
