package classes;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
  private Stage stage;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    stage = primaryStage;
    stage.setMaximized(true);
    stage.setTitle("Mysql Client");
    sendTopController();
    stage.show();
  }

  public void sendTopController() {
    TopController controller = new TopController(stage);
    SceneComponent.switchScene(stage, controller);
  }
}
