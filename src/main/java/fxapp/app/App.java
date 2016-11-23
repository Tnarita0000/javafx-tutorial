package fxapp.app;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import fxapp.manager.StageManager;

public class App extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    new StageManager(primaryStage);
  }
}
