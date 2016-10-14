package sample;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.Parent;
import javafx.scene.Group;
import javafx.stage.Stage;
import java.net.URL;
import java.io.IOException;
import javafx.scene.layout.ColumnConstraints;

public class FirstController {
  /* Pane */
  public GridPane gridForm;
  public GridPane gridSshForm;
  public GridPane gridSslForm;
  /* RadioButton */
  public RadioButton sshTrue;
  public RadioButton sslTrue;
  /* TextField */
  public TextField hostnameInput;     public TextField usernameInput;
  public TextField passwordInput;     public TextField portInput;
  public TextField sshHostnameInput;  public TextField sshPortInput;
  public TextField sshUsernameInput;  public TextField sshPasswordInput;

  public void connectMySQL(ActionEvent e) throws Exception {
    int port    = portInput.getText().isEmpty()    ? 0 : Integer.parseInt(portInput.getText());
    int sshPort = sshPortInput.getText().isEmpty() ? 0 : Integer.parseInt(sshPortInput.getText());
    MySQLManager manager = new MySQLManager();
    manager.setMySQLInfo(hostnameInput.getText(), usernameInput.getText(), passwordInput.getText(), port);
    manager.setSSHInfo(sshHostnameInput.getText(), sshUsernameInput.getText(), sshPasswordInput.getText(), sshPort);
    manager.setConnection();

    if (manager.hasConnection()) {
      StageComponent.sendScene("SecondController");
    }
  }

  public void pushMe(ActionEvent e) throws Exception {
    StageComponent.sendScene("SecondController");
  }

  public void selectSshOption(ActionEvent e) throws Exception {
    if (sshTrue.isSelected()) {
      gridSshForm.setVisible(true);
      gridSshForm.setManaged(true);
    } else {
      gridSshForm.setVisible(false);
      gridSshForm.setManaged(false);
    }
  }

  public void selectSslOption(ActionEvent e) throws Exception {
    if (sslTrue.isSelected()) {
      gridSslForm.setVisible(true);
      gridSslForm.setManaged(true);
    } else {
      gridSslForm.setVisible(false);
      gridSslForm.setManaged(false);
    }
  }
}
