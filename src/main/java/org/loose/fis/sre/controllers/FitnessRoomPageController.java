package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class FitnessRoomPageController {
    @FXML
    private Button addAntrenament;

    @FXML
    private void handleAddAntrenamentAction() throws Exception{
        Stage stage = (Stage) addAntrenament.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/addAntrenament.fxml"));
        stage.setScene(new Scene(root, 800,600));
    }
}
