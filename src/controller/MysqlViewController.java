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

public class MysqlViewController {
  public static BorderPane pane;
  private Stage stage;

  public MysqlViewController(Stage stage) {
    this.stage = stage;
    render();
  }

  public static void render() {
    Rectangle2D windowSize = Screen.getPrimary().getVisualBounds();
    pane = new BorderPane();
    List<String> tableList = MysqlConnector.tables;

    /* header */
    GridPane gridHeader = new GridPane();
    gridHeader.setPadding(new Insets(15, 25, 15, 25));
    gridHeader.setStyle("-fx-background-color: black");
    Button tableButton = new Button("Tables");
    tableButton.setStyle("-fx-font: 20px Serif");
    gridHeader.getChildren().add(tableButton);


    /* table list */
    GridPane gridTableList = new GridPane();
    gridTableList.setAlignment(Pos.CENTER);
    gridTableList.setHgap(30);
    for(int i=0; i<tableList.size(); i++) {
      VBox vb = new VBox();
      vb.getStyleClass().add("table-list");
      vb.setPadding(new Insets(10, windowSize.getWidth()/4, 10, windowSize.getWidth()/4));
      Label label = new Label(tableList.get(i));
      label.setStyle("-fx-font: 18px Serif");
      vb.getChildren().add(label);
      gridTableList.add(vb, 0, i);

      label.setOnMouseClicked(e -> {
        System.out.println("hoge");
      });
    }

    pane.setTop(gridHeader);
    pane.setCenter(gridTableList);
  }
}
