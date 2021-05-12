package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.sre.exceptions.AntrenamentAlreadyExistsException;
import org.loose.fis.sre.exceptions.EmptyTextfieldsException;
import org.loose.fis.sre.exceptions.NameAndAntrenorExistsException;
import org.loose.fis.sre.services.AntrenamentService;

import java.io.IOException;

public class DeleteAntrenamentController {
    @FXML
    private TextField fitnessRoomNameField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField antrenorField;
    @FXML
    private TextField timeField;
    @FXML
    private TextField priceField;
    @FXML
    private Text deleteMessage;
    @FXML
    private Text backMessage;

    @FXML
    private void handleDeleteButton() throws EmptyTextfieldsException, AntrenamentAlreadyExistsException {
        try {
            AntrenamentService.deleteAntrenament(fitnessRoomNameField.getText(),nameField.getText(),antrenorField.getText(),timeField.getText(),Integer.parseInt(priceField.getText()));
            deleteMessage.setText("Clasa de antrenament stearsa cu succes!");
        } catch (EmptyTextfieldsException e){
            deleteMessage.setText(e.getMessage);
        } //catch (NameAndAntrenorExistsException e){
        //editMessage.setText(e.getMessage);
        // }
    }

    @FXML
    public void handleBackFitnessRoomPageAction() throws IOException {
        Stage primary = (Stage) backMessage.getScene().getWindow();
        Parent root = FXMLLoader.load(this.getClass().getClassLoader().getResource("fxml/fitnessRoomPage.fxml"));
        Scene nextScene = new Scene(root, 800, 600);
        primary.setScene(nextScene);
    }
}
