package classes;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
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
    layout.setVgap(10);
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
    TextField portInput = new TextField();
    Button connectBtn = new Button("Connect");

    connectBtn.setOnAction(e -> {
      System.out.println(nameInput.getText());
      System.out.println(hostnameInput.getText());
    });

    layout.add(nameLabel, 0, 0);
    layout.add(nameInput, 1, 0);
    layout.add(hostnameLabel, 0, 1);
    layout.add(hostnameInput, 1, 1);
    layout.add(usernameLabel, 0, 2);
    layout.add(usernameInput, 1, 2);
    layout.add(passwordLabel, 0, 3);
    layout.add(passwordInput, 1, 3);
    layout.add(portLabel, 0, 4);
    layout.add(portInput, 1, 4);
    layout.add(connectBtn, 1, 5);

    Scene scene = new Scene(layout, 800, 600);
    scene.getStylesheets().add("sources/stylesheets/application.css");
    window.setScene(scene);
    window.show();
  }
}
