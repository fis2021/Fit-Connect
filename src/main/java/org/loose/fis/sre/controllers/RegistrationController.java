package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.services.UserService;

import java.io.IOException;

public class RegistrationController {

    @FXML
    private Text registrationMessage;
    @FXML
    private TextField nameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private ChoiceBox role;
    @FXML
    private Text backMessage;

    @FXML
    public void initialize() {
        role.getItems().addAll("Sportiv", "Sala de sport");
    }

    @FXML
    public void handleRegisterAction() {
        try {
            UserService.addUser(nameField.getText(),usernameField.getText(), passwordField.getText(), (String) role.getValue());
            registrationMessage.setText("Contul a fost creat cu succes!");
        } catch (UsernameAlreadyExistsException e) {
            registrationMessage.setText(e.getMessage());
        }
    }

    @FXML
    public void handleBackAction() throws IOException {
        Stage primary = (Stage) backMessage.getScene().getWindow();
        Parent root = FXMLLoader.load(this.getClass().getClassLoader().getResource("fxml/homepage.fxml"));
        Scene nextScene = new Scene(root, 800, 600);
        primary.setScene(nextScene);
    }
}

