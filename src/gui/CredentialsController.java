package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import logic.MySQLAccess;


public class CredentialsController implements Initializable {
    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private TextField languageColumn;

    @FXML
    private TextField cultureColumn;

    @FXML
    private TextField touristAttractionColumn;

    @FXML
    private TextField continentColumn;

    private RaspberryPiIp selectedRaspberry;

    private String[] contryInfo = new String[4];

    public void setCountryInfo(String language, String culture, String touristAttraction, String continent) {
        this.contryInfo[0] = language;
        this.contryInfo[1] = culture;
        this.contryInfo[2] = touristAttraction;
        this.contryInfo[3] = continent;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    public void initData(RaspberryPiIp raspberryIp) {
        this.selectedRaspberry = raspberryIp;
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
        initiateConnection(selectedRaspberry.getIp(), username, password, this.contryInfo);
    }

    private void sceneChange(String fxmlName, ActionEvent event) throws IOException {
        Parent home_screen = FXMLLoader.load(getClass().getResource(fxmlName));
        Scene home_screen_scene = new Scene(home_screen);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(home_screen_scene);
        stage.show();
    }

    private void initiateConnection(String ip, String username, String password, String[] countryInfo) {
        MySQLAccess dao = new MySQLAccess();
        this.contryInfo = dao.connect(ip, username, password, countryInfo);
        this.languageColumn.setText(countryInfo[0]);
        this.cultureColumn.setText(countryInfo[1]);
        this.touristAttractionColumn.setText(countryInfo[2]);
        this.continentColumn.setText(countryInfo[3]);
    }
}
