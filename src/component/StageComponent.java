package sample;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;

public class StageComponent {
  public static Stage stage;

  StageComponent(Stage primaryStage) {
    this.stage = primaryStage;
    this.stage.setMaximized(true);
    sendScene("FirstController");
  }

  public static void sendScene(String controllerName) {
    if (stage.getScene() == null) {
      ViewComponent vmanager = new ViewComponent(controllerName);
      stage.setScene(vmanager.getScene());
      stage.show();
    } else {
      ViewComponent vmanager = new ViewComponent(controllerName);
      stage.setScene(vmanager.getScene());
    }
  }
}
