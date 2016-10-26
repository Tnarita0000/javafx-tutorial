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

public class QueryConsoleController implements Initializable {
  @FXML
  ImageView queryConsoleImage;
  @FXML
  ImageView rowImage;


  @Override
  public void initialize(URL url, ResourceBundle rb) {
    setIcons();
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
