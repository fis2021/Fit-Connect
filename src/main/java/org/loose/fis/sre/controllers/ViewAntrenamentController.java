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
import org.loose.fis.sre.model.Antrenament;
import org.loose.fis.sre.model.User;
import org.loose.fis.sre.services.AntrenamentService;

public class ViewAntrenamentController {
    @FXML
    private ListView<Antrenament> list=new ListView<>();
    @FXML
    private Button back;


    private User user=new User();
    public void init(String sel) throws Exception{
        user.setSelectedFitnessRoom(sel);
        ObservableList<Antrenament> antre= FXCollections.observableArrayList(AntrenamentService.antrenaments(user.getSelectedFitnessRoom()));
        list.getItems().addAll(antre);
    }

    public void handleBackButton() throws Exception{
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("fxml/viewFitnessRoomList.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        ViewFitnessRoomController control = loader.getController();
        control.set();
        Stage stage = (Stage) (back.getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }

    public void handleReservationButton(){

    }
}
