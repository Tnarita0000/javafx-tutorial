package classes;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {
  Stage window;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    window = primaryStage;
    window.setTitle("Mysql Client");

    GridPane layout = new GridPane();
    layout.setAlignment(Pos.CENTER);
    layout.setPadding(new Insets(10, 10, 10, 10));
    layout.setVgap(12);
    layout.setHgap(10);

    Label nameLabel = new Label("Name:");
    TextField nameInput = new TextField();
    Label hostnameLabel = new Label("Host Name:");
    TextField hostnameInput = new TextField();
    Label usernameLabel = new Label("User Name:");
    TextField usernameInput = new TextField();
    Label passwordLabel = new Label("Password:");
    TextField passwordInput = new TextField();
    Label portLabel = new Label("Port no.:");
    TextField portInput = new TextField("3306");
    Label sshHostnameLabel = new Label("SSH Host Name:");
    TextField sshHostnameInput = new TextField();
    Label sshPasswordLabel = new Label("SSH Password:");
    TextField sshPasswordInput = new TextField();
    Label sshUsernameLabel = new Label("SSH User Name:");
    TextField sshUsernameInput = new TextField();
    Label sshPortLabel = new Label("SSH Port:");
    TextField sshPortInput = new TextField();

    Button connectBtn = new Button("Connect");

    HBox hbButtons = new HBox();
    hbButtons.setSpacing(10.0);
    Button btnSubmit = new Button("Submit");
    Button btnClear = new Button("Clear");
    Button btnExit = new Button("Exit");

    connectBtn.setOnAction(e -> {
      System.out.println(nameInput.getText());
      System.out.println(hostnameInput.getText());
      new MysqlConnector(nameInput.getText(),     hostnameInput.getText(),
                         usernameInput.getText(), passwordInput.getText(),
                         Integer.parseInt(portInput.getText()),
                         sshHostnameInput.getText(), sshPasswordInput.getText(),
                         sshUsernameInput.getText(), Integer.parseInt(sshPortInput.getText())
                        );
    });

    layout.add(nameLabel,        0, 0);
    layout.add(nameInput,        1, 0);

    layout.add(hostnameLabel,    0, 1);
    layout.add(hostnameInput,    1, 1);

    layout.add(usernameLabel,    0, 2);
    layout.add(usernameInput,    1, 2);

    layout.add(passwordLabel,    0, 3);
    layout.add(passwordInput,    1, 3);

    layout.add(portLabel,        0, 4);
    layout.add(portInput,        1, 4);

    layout.add(sshHostnameLabel, 0, 5);
    layout.add(sshHostnameInput, 1, 5);

    layout.add(sshPasswordLabel, 0, 6);
    layout.add(sshPasswordInput, 1, 6);

    layout.add(sshUsernameLabel, 0, 7);
    layout.add(sshUsernameInput, 1, 7);

    layout.add(sshPortLabel,     0, 8);
    layout.add(sshPortInput,     1, 8);

    layout.add(connectBtn,       1, 9);
    hbButtons.getChildren().addAll(btnSubmit, btnClear, btnExit);
    layout.add(hbButtons, 0, 10, 2, 1);

    Scene scene = new Scene(layout, 800, 600);
    scene.getStylesheets().add("src/stylesheets/application.css");
    window.setScene(scene);
    window.show();
  }
}
