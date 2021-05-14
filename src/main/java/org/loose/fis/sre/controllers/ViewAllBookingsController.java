package org.loose.fis.sre.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.model.Reservation;
import org.loose.fis.sre.model.User;
import org.loose.fis.sre.services.ReservationService;
import javafx.scene.control.ListView;
import java.io.IOException;

public class ViewAllBookingsController {
    ObservableList<Reservation> reservations= FXCollections.observableArrayList(ReservationService.reservations(User.getCurrentUser()));

    @FXML
    private Text backMessage;

    @FXML
    public ListView<Reservation> rezervari = new ListView<>();

    @FXML
    public void initialize() throws IOException {
        rezervari.setItems(reservations);
    }


    @FXML
    public void handleBackFitnessRoomPageAction() throws IOException {
        Stage primary = (Stage) backMessage.getScene().getWindow();
        Parent root = FXMLLoader.load(this.getClass().getClassLoader().getResource("fxml/fitnessRoomPage.fxml"));
        Scene nextScene = new Scene(root, 800, 600);
        primary.setScene(nextScene);
    }
}
