package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SportsmanPageController {
    @FXML
    private Button logOut;
    @FXML
    private Button viewFitnessRoom;


    public void handleLogOutButtonAction() throws Exception{
        Stage stage = (Stage) logOut.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/login.fxml"));
        stage.setScene(new Scene(root, 800,600));
    }


    public void handleViewFitnessRoomAction() throws Exception{
        Stage stage = (Stage) logOut.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/viewFitnessRoomList.fxml"));
        stage.setScene(new Scene(root, 800,600));
    }
}
