package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.sre.exceptions.AccountCrdentialsException;

import java.io.IOException;

import static org.loose.fis.sre.services.UserService.getUserRole;

public class LoginController {
    @FXML
    private Text loginMessage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;

    public void handleLoginAction() {
        if(usernameField.getText()==null||usernameField.getText().isEmpty()){
            loginMessage.setText("Campul username este gol!");
            return;
        }
        if(passwordField.getText()==null||passwordField.getText().isEmpty()){
            loginMessage.setText("Campul password este gol!");
            return;
        }
        else{
            Stage primary = (Stage) loginMessage.getScene().getWindow();
            try{
                String role=getUserRole(usernameField.getText(),passwordField.getText());
                if(role.equals("Sportiv")) {
                    Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/sportsmanPage.fxml"));
                    Scene nextScene = new Scene(root, 800, 600);
                    primary.setScene(nextScene);
                }
                else{
                    Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/fitnessRoomPage.fxml"));
                    Scene nextScene = new Scene(root, 800, 600);
                    primary.setScene(nextScene);
                }
            }
            catch(AccountCrdentialsException | IOException e){
              loginMessage.setText(e.getMessage());
            }
        }
    }

}