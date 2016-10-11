package classes;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.util.*;
import java.io.IOException;

public class MySQLViewController {
  public static BorderPane pane;
  private Stage stage;

  public MySQLViewController(Stage stage) {
    this.stage = stage;
    render();
  }

  public static void render() {
    Rectangle2D windowSize = Screen.getPrimary().getVisualBounds();
    pane = new BorderPane();
    List<String> tableList = MySQLSearch.getDatabases(MySQLConnector.con);

    /* header */
    GridPane gridHeader = new GridPane();
    gridHeader.setPadding(new Insets(15, 25, 15, 25));
    gridHeader.setStyle("-fx-background-color: black");
    Button tableButton = new Button("Tables");
    tableButton.setStyle("-fx-font: 20px Serif");
    gridHeader.getChildren().add(tableButton);


    /* database list */
    GridPane gridTableList = new GridPane();
    gridTableList.setAlignment(Pos.CENTER);
    gridTableList.setHgap(30);
    for(int i=0; i<tableList.size(); i++) {
      VBox databaseVBox = new VBox();
      databaseVBox.getStyleClass().add("table-list");
      databaseVBox.setPadding(new Insets(10, windowSize.getWidth()/4, 10, windowSize.getWidth()/4));
      Label label = new Label(tableList.get(i));
      label.setStyle("-fx-font: 18px Serif");
      databaseVBox.getChildren().add(label);
      gridTableList.add(databaseVBox, 0, i);

      databaseVBox.setOnMouseClicked(e -> {
        MySQLConnector.instance.connectDatabase(label.getText());
        List<String> tables = MySQLSearch.getTables(MySQLConnector.con, label.getText());
        tables.forEach( table -> System.out.println(table));
      });
    }

    pane.setTop(gridHeader);
    pane.setCenter(gridTableList);
  }
}
