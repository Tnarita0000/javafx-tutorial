package classes;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import java.util.*;

public class Top {
  public static Scene render() {
    /* setting GridPane */
    GridPane gridForm = new GridPane();
    gridForm.setAlignment(Pos.CENTER);
    gridForm.setPadding(new Insets(10, 10, 10, 10));
    gridForm.setVgap(12);
    gridForm.setHgap(10);

    GridPane gridList= new GridPane();
    gridList.setAlignment(Pos.CENTER);
    gridList.setPadding(new Insets(10, 10, 10, 10));
    gridList.setVgap(12);
    gridList.setHgap(10);

    GridPane gridHeader= new GridPane();
    gridHeader.setPadding(new Insets(2, 2, 2, 2));
    gridHeader.setVgap(2);
    gridHeader.setHgap(2);

    /* setting BorderPane */
    BorderPane borderPane = new BorderPane();

    /* setting Scene */
    Scene scene = new Scene(borderPane, 800, 650);
    scene.getStylesheets().add("src/stylesheets/application.css");

    /* parts in gridForm */
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

    /* parts in gridList */
    Label nameLabel1 = new Label("Sample1");
    Label nameLabel2 = new Label("Sample2");
    Label nameLabel3 = new Label("Sample3");

    /* parts in gridHeader */
    Label headerLabel1 = new Label("File");
    Label headerLabel2 = new Label("Edit");
    headerLabel1.setStyle("-fx-border-color: transparent transparent #888 transparent");
    headerLabel2.setStyle("-fx-border-color: transparent transparent #888 transparent");

    connectBtn.setOnAction(e -> {
      System.out.println(nameInput.getText());
      System.out.println(hostnameInput.getText());
      MysqlConnector mysqlConnector = new MysqlConnector(nameInput.getText(),     hostnameInput.getText(),
                         usernameInput.getText(), passwordInput.getText(),
                         Integer.parseInt(portInput.getText()),
                         sshHostnameInput.getText(), sshPasswordInput.getText(),
                         sshUsernameInput.getText(), Integer.parseInt(sshPortInput.getText())
                        );
      MysqlView.render(mysqlConnector.tables);
    });

    gridForm.add(nameLabel,        0, 0);
    gridForm.add(nameInput,        1, 0);
    gridForm.add(hostnameLabel,    0, 1);
    gridForm.add(hostnameInput,    1, 1);
    gridForm.add(usernameLabel,    0, 2);
    gridForm.add(usernameInput,    1, 2);
    gridForm.add(passwordLabel,    0, 3);
    gridForm.add(passwordInput,    1, 3);
    gridForm.add(portLabel,        0, 4);
    gridForm.add(portInput,        1, 4);
    gridForm.add(sshHostnameLabel, 0, 5);
    gridForm.add(sshHostnameInput, 1, 5);
    gridForm.add(sshPasswordLabel, 0, 6);
    gridForm.add(sshPasswordInput, 1, 6);
    gridForm.add(sshUsernameLabel, 0, 7);
    gridForm.add(sshUsernameInput, 1, 7);
    gridForm.add(sshPortLabel,     0, 8);
    gridForm.add(sshPortInput,     1, 8);
    gridForm.add(connectBtn,       1, 9);

    gridList.add(nameLabel1, 0, 1);
    gridList.add(nameLabel2, 0, 2);
    gridList.add(nameLabel3, 0, 3);

    gridHeader.add(headerLabel1, 0, 0);
    gridHeader.add(headerLabel2, 1, 0);

    /* setting Box */
    VBox listVBox = new VBox();
    listVBox.setAlignment(Pos.CENTER);
    listVBox.setStyle("-fx-background-color: white");
    listVBox.getChildren().add(gridList);
    borderPane.setLeft(listVBox);

    gridForm.setStyle("-fx-font-size: 15px;");
    HBox formHBox = new HBox();
    formHBox.setAlignment(Pos.CENTER);
    formHBox.getChildren().add(gridForm);
    borderPane.setCenter(formHBox);

    HBox headerBox = new HBox();
    headerBox.setSpacing(10.0);
    headerBox.getChildren().addAll(headerLabel1, headerLabel2);
    headerBox.setStyle("-fx-background-color: #ccc; -fx-border-color: transparent transparent #999 transparent;");
    headerBox.setMargin(headerLabel1, new Insets(2, 2, 2, 2));
    headerBox.setMargin(headerLabel2, new Insets(2, 2, 2, 2));
    headerBox.getChildren().add(gridHeader);
    borderPane.setTop(headerBox);

    return scene;
  }
}
