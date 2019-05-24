package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;

public class Controller {

    @FXML
    private TextField ipAddress;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private Text ip;

    public void setIpAddress() {
        ip.setText(ipAddress.getText());
    }

    public void connectButton(ActionEvent event) throws IOException {
        System.out.println("Changing to the credentials screen");
        System.out.println(this.ipAddress.getText());
        sceneChange("credentials.fxml", event);
    }

    public void goBackButton(ActionEvent event) throws IOException {
        System.out.println("Changing to the home screen");
        sceneChange("home.fxml", event);
    }

    public void loginButton(ActionEvent event) {
        String username = this.username.getText();
        String password = this.password.getText();
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
    }

    private void sceneChange(String fxmlName, ActionEvent event) throws IOException {
        Parent credentials_screen = FXMLLoader.load(getClass().getResource(fxmlName));
        Scene credentials_screen_scene = new Scene(credentials_screen);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(credentials_screen_scene);
        stage.show();
    }

}
