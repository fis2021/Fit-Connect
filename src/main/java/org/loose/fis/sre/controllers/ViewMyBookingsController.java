package org.loose.fis.sre.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.sre.model.Reservation;
import org.loose.fis.sre.model.User;
import org.loose.fis.sre.services.ReservationService;
import org.loose.fis.sre.services.UserService;

import java.io.IOException;

public class ViewMyBookingsController {
    ObservableList<Reservation> myReservations= FXCollections.observableArrayList(ReservationService.myReservations(User.getCurrentUser()));

    @FXML
    private Text backMessage;

    @FXML
    public ListView<Reservation> rezervarileMele = new ListView<>();

    @FXML
    public void initialize() throws IOException {
        rezervarileMele.setItems(myReservations);
    }

    @FXML
    public void handleBackSportsmanPageAction() throws IOException {
        Stage primary = (Stage) backMessage.getScene().getWindow();
        Parent root = FXMLLoader.load(this.getClass().getClassLoader().getResource("fxml/sportsmanPage.fxml"));
        Scene nextScene = new Scene(root, 800, 600);
        primary.setScene(nextScene);
    }
}
