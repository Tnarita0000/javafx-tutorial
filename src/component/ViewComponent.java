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
      this.scene = new Scene(root, 850, 750); /* width: 850, height: 650 */
      if (getStylesheetLocation() != null)
        scene.getStylesheets().add(getStylesheetLocation().toExternalForm());
    } catch (IOException exception) {
      throw new RuntimeException(exception);
    }
  }

  public URL getFXMLLocation() {
    String parentDir = "../src/view/";
    String fxmlFile  = this.controller + ".fxml";
    return getClass().getResource(parentDir + fxmlFile);
  }

  public URL getStylesheetLocation() {
    String parentDir = "../src/stylesheets/";
    String cssFile   = this.controller + ".css";
    return getClass().getResource(parentDir + cssFile);
  }

  public Scene getScene() {
    return this.scene;
  }
}
