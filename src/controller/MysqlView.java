package classes;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import java.util.*;

public class MysqlView {
  public static void render(List<String> tables) {
    tables.forEach(table -> System.out.println(table));
    GridPane gridTableList = new GridPane();
    gridTableList.setAlignment(Pos.CENTER);
    gridTableList.setPadding(new Insets(10, 10, 10, 10));
    gridTableList.setVgap(12);
    gridTableList.setHgap(10);
  }
}
