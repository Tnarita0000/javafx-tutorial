package sample;
import javafx.fxml.FXMLLoader;
import java.net.URL;
import java.io.IOException;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class ViewComponent {
  public static String controller;
  public static Scene scene;

  public ViewComponent(String controller) {
    this.controller = controller;
    loadFXML();
  }

  public void loadFXML() {
    try {
      Parent root  = FXMLLoader.load(getFXMLLocation());
      this.scene = new Scene(root);
    } catch (IOException exception) {
      throw new RuntimeException(exception);
    }
  }

  public URL getFXMLLocation() {
    String parentDir = "../src/view/";
    String fxmlFile  = this.controller + ".fxml";
    return getClass().getResource(parentDir + fxmlFile);
  }

  public Scene getScene() {
    return this.scene;
  }
}
