package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.loose.fis.sre.exceptions.AntrenamentAlreadyExistsException;
import org.loose.fis.sre.services.AntrenamentService;

public class AddAntrenamentController {
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
    private Text addMessage;

    @FXML
    private void handleAddButton() throws AntrenamentAlreadyExistsException{
        try{
            AntrenamentService.addAntrenament(fitnessRoomNameField.getText(),nameField.getText(),antrenorField.getText(),timeField.getText(),Integer.parseInt(priceField.getText()));
            addMessage.setText("Clasa de antrenament adaugata cu succes!");
        }
        catch (AntrenamentAlreadyExistsException e){
            addMessage.setText(e.getMessage());
        }
    }

}
