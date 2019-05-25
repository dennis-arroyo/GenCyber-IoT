package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Controller {

    @FXML
    private TextField ipAddress;

    @FXML
    private Label errorLabel;

    private ArrayList<String> array = new ArrayList<String>(Arrays.asList("192.168.0.104", "192.168.0.105", "192.168.0.106", "192.168.0.107", "192.168.0.108", "192.168.0.109", "192.168.0.110", "192.168.0.111",
            "192.168.0.112",
            "192.168.0.113", "192.168.0.114", "192.168.0.115", "192.168.0.116", "192.168.0.117", "192.168.0.118", "192.168.0.119", "192.168.0.120", "192.168.0.121", "192.168.0.122",
            "192.168.0.123", "192.168.0.124","192.168.0.125","192.168.0.126","192.168.0.127","198.168.0.128","192.168.0.129","192.168.0.130"));

    public void connectButton(ActionEvent event) throws IOException {
        System.out.println("Changing to the credentials screen");
        System.out.println(this.ipAddress.getText());
        boolean validIp = validateIpAddressInput(this.ipAddress.getText());
        if (validIp)
            sceneChange("credentials.fxml", event);
        else
            this.errorLabel.setText("Error connecting to: " + this.ipAddress.getText() + "\n" +
                    "Make sure you typed the correct IP address");
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

    private boolean validateIpAddressInput(String ip) {
        return array.contains(ip);
    }

}
