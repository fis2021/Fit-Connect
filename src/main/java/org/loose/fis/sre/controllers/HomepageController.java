package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class HomepageController {

    @FXML
    private Text registrationMessage;
    @FXML
    private Text loginMessage;
    @FXML
    private Button loginButton;
    @FXML
    private Button registrationButton;

    @FXML
    public void handleRegisterAction() throws IOException {
            Stage primary = (Stage) registrationMessage.getScene().getWindow();
            Parent root = FXMLLoader.load(this.getClass().getClassLoader().getResource("fxml/register.fxml"));
            Scene nextScene = new Scene(root, 800, 600);
            primary.setScene(nextScene);
    }

    public void handleLoginAction() throws IOException {
        Stage primary = (Stage) loginMessage.getScene().getWindow();
        Parent root = FXMLLoader.load(this.getClass().getClassLoader().getResource("fxml/login.fxml"));
        Scene nextScene = new Scene(root, 800, 600);
        primary.setScene(nextScene);
    }

}
