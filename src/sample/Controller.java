package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

public class Controller {

    public void connectButton(ActionEvent event) throws IOException {
        System.out.println("Changing to the crendetials screen");
        Parent crendetials_screen = FXMLLoader.load(getClass().getResource("crendetials.fxml"));
        Scene credentials_screen_scene = new Scene(crendetials_screen);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(credentials_screen_scene);
        stage.show();
    }

}
