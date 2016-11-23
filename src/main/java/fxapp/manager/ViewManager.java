package fxapp.manager;
import javafx.fxml.FXMLLoader;
import java.net.URL;
import java.io.IOException;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class ViewManager {
  public static String controller;
  public static Scene scene;

  public ViewManager(String controller) {
    this.controller = controller;
    loadFXML();
  }

  public Scene getScene() {
    return this.scene;
  }

  private void loadFXML() {
    try {
      Parent root  = FXMLLoader.load(getFXMLLocation());
      this.scene = new Scene(root, 850, 650); /* width: 850, height: 650 */
      if (getStylesheetLocation() != null)
        scene.getStylesheets().add(getStylesheetLocation().toExternalForm());
    } catch (IOException exception) {
      throw new RuntimeException(exception);
    }
  }

  private URL getFXMLLocation() {
    String parentDir = "/fxapp/fxml/";
    String fxmlFile  = this.controller.replace("Controller","").toLowerCase() + ".fxml";
    return getClass().getResource(parentDir + fxmlFile);
  }

  private URL getStylesheetLocation() {
    String parentDir = "/fxapp/stylesheet/";
    String cssFile   = this.controller.replace("Controller","").toLowerCase() + ".css";
    return getClass().getResource(parentDir + cssFile);
  }
}
