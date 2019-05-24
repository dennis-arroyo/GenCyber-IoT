package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;

public class Controller {

    @FXML
    private TextField ipAddress;

    public void connectButton(ActionEvent event) throws IOException {
        System.out.println("Changing to the credentials screen");
        System.out.println(this.ipAddress.getText());
        sceneChange("credentials.fxml", event);
    }

    private void sceneChange(String fxmlName, ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent credentials_screen = loader.load(getClass().getResource(fxmlName).openStream());
        CredentialsController credentialsController = loader.getController();
        RaspberryPiIp raspberryPiIp = new RaspberryPiIp();
        raspberryPiIp.setIp(this.ipAddress.getText());
        credentialsController.initData(raspberryPiIp);
        Scene credentials_screen_scene = new Scene(credentials_screen);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(credentials_screen_scene);
        stage.show();
    }

}
