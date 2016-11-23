package fxapp.manager;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;

public class StageManager {
  public static Stage stage;

  public StageManager(Stage primaryStage) {
    this.stage = primaryStage;
    sendScene("HomeController");
  }

  public static void sendScene(String controllerName) {
    if (stage.getScene() == null) {
      ViewManager vmanager = new ViewManager(controllerName);
      stage.setScene(vmanager.getScene());
      stage.show();
    } else {
      ViewManager vmanager = new ViewManager(controllerName);
      stage.setScene(vmanager.getScene());
    }
  }
}
