package classes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
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
import java.util.*;
import java.io.IOException;

public class TopController extends BorderPane {
  public TextField hostname;
  public TextField username;
  public PasswordField password;
  public TextField port;
  public TextField sshHostnameInput;
  public TextField sshUsernameInput;
  public PasswordField sshPasswordInput;
  public TextField sshPortInput;
  public RadioButton sshTrue;
  public RadioButton sshFalse;
  public GridPane gridForm;

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
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("../src/view/Top.fxml"));
    fxmlLoader.setRoot(this);
    fxmlLoader.setController(this);
    try {
      fxmlLoader.load();
    } catch (IOException exceptioin) {
      throw new RuntimeException(exceptioin);
    }
  }

  public void selectSshOption(ActionEvent e) throws Exception {
    if (sshTrue.isSelected()) {
      int gridRowCount = gridForm.getChildren().size();
      int i = 0;
      LinkedHashMap<Label, TextField> map = new LinkedHashMap<Label, TextField>();
      //Label sshHostnameLabel = new Label("SSH Host Name:");
      //Label sshPortLabel     = new Label("SSH Port:");
      //Label sshUsernameLabel = new Label("SSH User Name:");
      //Label sshPasswordLabel = new Label("SSH Password:");
      //TextField sshHostnameInput = new TextField();
      //TextField sshPortinput     = new TextField("22");
      //TextField sshUsernameInput = new TextField();
      //TextField sshPasswordInput = new PasswordField();
      map.put(new Label("SSH Host Name:"), new TextField());
      map.put(new Label("SSH Port:"),      new TextField("22"));
      map.put(new Label("SSH User Name:"), new TextField());
      map.put(new Label("SSH Password:"),  new PasswordField());
      for (Label key : map.keySet())  {
        gridForm.add(key, 0, gridRowCount + i);
        gridForm.add(map.get(key), 1, gridRowCount + i);
        i++;
      }
    } else {
      System.out.println("no");
    }
  }
}
