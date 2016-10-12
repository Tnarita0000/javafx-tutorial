package classes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.net.URL;
import java.util.*;
import java.io.IOException;

public class TopController extends BorderPane {
  /* MySQL information */
  public TextField hostname;     public TextField username;
  public TextField password;     public TextField port;

  /* SSH information */
  public Label sshHostnameLabel; public TextField sshHostnameInput;
  public Label sshPortLabel;     public TextField sshPortInput;
  public Label sshUsernameLabel; public TextField sshUsernameInput;
  public Label sshPasswordLabel; public TextField sshPasswordInput;

  /* Form information */
  public RadioButton sshTrue;
  public GridPane gridForm;
  public Button connectButton;

  public TopController() {
    loadFXML();
  }

  public void connectMySQL(ActionEvent e) throws Exception{
    MysqlConnector connector = new MysqlConnector(
        hostname.getText(),    username.getText(),
        password.getText(),    Integer.parseInt(port.getText()),
        sshHostnameInput.getText(), sshUsernameInput.getText(),
        sshPasswordInput.getText(), Integer.parseInt(sshPortInput.getText())
    );
    App.getInstance().sendMysqlViewController();
  }

  private void loadFXML() {
    URL location = App.class.getResource("../src/view/Top.fxml");
    FXMLLoader fxmlLoader = new FXMLLoader(location);
    fxmlLoader.setRoot(this);
    fxmlLoader.setController(this);
    try {
      fxmlLoader.load();
    } catch (IOException exceptioin) {
      throw new RuntimeException(exceptioin);
    }
  }

  public void selectSshOption(ActionEvent e) {
    int gridRowCount = gridForm.getChildren().size();
    int i = 0;
    if (sshTrue.isSelected()) {
      sshHostnameLabel.setManaged(true);
      sshHostnameInput.setManaged(true);
      sshPortLabel.setManaged(true);
      sshPortInput.setManaged(true);
      sshUsernameLabel.setManaged(true);
      sshUsernameInput.setManaged(true);
      sshPasswordLabel.setManaged(true);
      sshPasswordInput.setManaged(true);

      gridForm.getChildren().remove(connectButton);
      gridForm.add(connectButton, 1, getGridRowSize() + 1);
    } else {
      loadFXML();
    }
  }

  public int getGridRowSize() {
    return gridForm.getChildren().size()/2;
  }
}
