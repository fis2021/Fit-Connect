package org.loose.fis.sre.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.loose.fis.sre.services.UserService;


public class ViewFitnessRoomController {
    @FXML
    private ListView<String> list = new ListView <String> ();
    @FXML
    private Button select;
    @FXML
    private Button back;

    ObservableList<String> fitnessRooms = FXCollections.observableArrayList(UserService.users("Sala de sport"));
    public void set(){
        list.setItems(fitnessRooms);
    }

    public void handleSelect() throws Exception{
        String selected=list.getSelectionModel().getSelectedItem();
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(this.getClass().getClassLoader().getResource("fxml/viewAntrenaments.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        ViewAntrenamentController control = loader.getController();
        control.init(selected);
        Stage stage = (Stage) (select.getScene().getWindow());
        stage.setScene(scene);
        stage.show();

    }
    public void handleBackButton() throws Exception{
        Stage stage = (Stage) back.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/sportsmanPage.fxml"));
        stage.setScene(new Scene(root, 800,600));
    }
}