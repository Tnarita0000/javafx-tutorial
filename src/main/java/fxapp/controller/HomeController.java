package fxapp.controller;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.InputEvent;
import javafx.stage.Stage;
import fxapp.manager.*;
public class HomeController {
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
      StageManager.sendScene("DashboardController");
    }
  }

  public void pressEnter(InputEvent e) {
    if(e instanceof KeyEvent) {
      KeyEvent kEvent = (KeyEvent) e;
      if(kEvent.getCode() == KeyCode.ENTER) {
        System.out.println("enter pressed");
      }
    }
  }

  public void pushMe(ActionEvent e) throws Exception {
    StageManager.sendScene("DashboardController");
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
