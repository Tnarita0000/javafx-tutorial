package classes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
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
import javafx.scene.Parent;
import javafx.stage.Stage;

public class App extends Application {
  private static App instance;
  private Stage stage;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    instance = this;
    stage = primaryStage;
    stage.setMaximized(true);
    stage.setTitle("Mysql Client");
    sendTopController();
    stage.show();
  }

  public void sendTopController() {
    TopController controller = new TopController();
    this.switchScene(controller);
  }

  public void sendMysqlViewController() {
    MysqlViewController controller = new MysqlViewController();
    this.switchScene(controller);
  }

  private void switchScene(Parent controller) {
    Scene scene = stage.getScene();
    if (scene == null) {
      scene = new Scene(controller);
      stage.setScene(scene);
    } else {
      stage.getScene().setRoot(controller);
    }
  }

  public static App getInstance() {
    return instance;
  }
}
