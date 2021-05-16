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
    @FXML
    private Button viewMyReservations;

    public void handleLogOutButtonAction() throws Exception{
        Stage stage = (Stage) logOut.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/homepage.fxml"));
        stage.setScene(new Scene(root, 800,600));
    }

    public void handleViewFitnessRoomAction() throws Exception{
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("fxml/viewFitnessRoomList.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        ViewFitnessRoomController controller = loader.getController();
        controller.set();
        Stage stage = (Stage) (viewFitnessRoom.getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }

    public void handleViewMyBookingsAction () throws Exception{
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("fxml/viewMyBookings.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        ViewMyBookingsController controller = loader.getController();
        controller.initialize();
        Stage stage = (Stage) (viewMyReservations.getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }
}
