package classes;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;

public class SceneComponent {

  public static void sendScene(Stage stage, Parent pane) {
    Scene scene = stage.getScene();
    if (scene == null) {
      scene = new Scene(pane);
      scene.getStylesheets().addAll("./src/stylesheets/application.css");
      stage.setScene(scene);
    } else {
      stage.getScene().setRoot(pane);
    }
  }

}
