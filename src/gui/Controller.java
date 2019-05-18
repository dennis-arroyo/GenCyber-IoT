package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

public class Controller {

    public void connectButton(ActionEvent event) throws IOException {
        System.out.println("Changing to the credentials screen");
        sceneChange("credentials.fxml", event);
    }

    public void goBackButton(ActionEvent event) throws IOException {
        System.out.println("Changing to the home screen");
        sceneChange("home.fxml", event);
    }

    private void sceneChange(String fxmlName, ActionEvent event) throws IOException {
        Parent crendetials_screen = FXMLLoader.load(getClass().getResource(fxmlName));
        Scene credentials_screen_scene = new Scene(crendetials_screen);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(credentials_screen_scene);
        stage.show();
    }

}
