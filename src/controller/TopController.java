package classes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.*;
import java.io.IOException;

public class TopController extends BorderPane {
  public TextField hostname;
  public TextField username;
  public PasswordField password;
  public TextField port;
  public TextField sshHostname;
  public TextField sshUsername;
  public PasswordField sshPassword;
  public TextField sshPort;

  public TopController() {
    loadFXML();
  }

  public void connectMySQL(ActionEvent e) throws Exception{
    MysqlConnector connector = new MysqlConnector(
        hostname.getText(),    username.getText(),
        password.getText(),    Integer.parseInt(port.getText()),
        sshHostname.getText(), sshUsername.getText(),
        sshPassword.getText(), Integer.parseInt(sshPort.getText())
    );
    App.getInstance().sendMysqlViewController();
  }

  private void loadFXML() {
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("../src/view/Top.fxml"));
    fxmlLoader.setRoot(this);
    fxmlLoader.setController(this);
    try {
      fxmlLoader.load();
    } catch (IOException exceptioin) {
      throw new RuntimeException(exceptioin);
    }
  }
}
