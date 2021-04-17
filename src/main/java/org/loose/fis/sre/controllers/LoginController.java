package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.sre.exceptions.WrongPasswordException;
import org.loose.fis.sre.exceptions.WrongUsernameException;
import org.loose.fis.sre.services.UserService;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class LoginController {
    @FXML
    private Text loginMessage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private ChoiceBox role;
    @FXML
    public void handleLoginAction() throws IOException,WrongUsernameException,WrongPasswordException{
        if(usernameField.getText()==null||usernameField.getText().isEmpty()){
            loginMessage.setText("Campul username este gol!");
            return;
        }
        if(passwordField.getText()==null||passwordField.getText().isEmpty()){
            loginMessage.setText("Campul password este gol!");
            return;
        }
        else{
            try{
                UserService.checkUser(usernameField.getText(),passwordField.getText());
                Alert alert=new Alert(Alert.AlertType.INFORMATION,"Autentificare efectuata cu succes!");
                alert.showAndWait();

                Parent view= FXMLLoader.load(getClass().getResource("login.fxml"));
                Scene scene=new Scene(view);
                Stage stage = (Stage) loginMessage.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }
            catch(WrongUsernameException | WrongPasswordException e){
                Alert alert=new Alert(Alert.AlertType.WARNING,e.getMessage());
                alert.showAndWait();
            }
        }
    }
    @FXML
    public void goToLogin(ActionEvent event) throws IOException{
        FXMLLoader loader=new FXMLLoader(getClass().getClassLoader().getResource("login.fxml"));
        Parent parent=loader.load();
        Scene scene=new Scene(parent);
        Stage stage=(Stage) loginMessage.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}