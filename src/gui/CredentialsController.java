package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CredentialsController implements Initializable {
    @FXML
    private TextField username;

    @FXML
    private TextField password;

    private RaspberryPiIp selectedRaspberry;

    private boolean connectionFlag;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void initData(RaspberryPiIp raspberryIp) {
        this.selectedRaspberry = raspberryIp;
    }

    public void setConnectionFlag(boolean connectionFlag) {
        this.connectionFlag = connectionFlag;
    }

    public void goBackButton(ActionEvent event) throws IOException {
        System.out.println("Changing to the home screen");
        sceneChange("home.fxml", event);
    }

    public void loginButton(ActionEvent event) {
        String username = this.username.getText();
        String password = this.password.getText();
        System.out.println("IP: " + selectedRaspberry.getIp());
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
    }

    private void sceneChange(String fxmlName, ActionEvent event) throws IOException {
        Parent home_screen = FXMLLoader.load(getClass().getResource(fxmlName));
        Scene home_screen_scene = new Scene(home_screen);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(home_screen_scene);
        stage.show();
    }
}
