package org.loose.fis.sre.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.loose.fis.sre.services.UserService;


public class ViewFitnessRoomController {
    @FXML
    private ListView<String> list = new ListView <String> ();

    ObservableList<String> fitnessRooms = FXCollections.observableArrayList(UserService.users("Sala de sport"));
    public void set(){
        list.setItems(fitnessRooms);

    }
}