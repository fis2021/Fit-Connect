package org.loose.fis.sre.controllers;

//import com.sun.javafx.tk.quantum.PaintRenderJob;
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
    private Button editAntrenament;
    @FXML
    private Button deleteAntrenament;
    @FXML
    private Button logout;
    @FXML
    private Button viewReservations;

    @FXML
    private void handleAddAntrenamentAction() throws Exception{
        Stage stage = (Stage) addAntrenament.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/addAntrenament.fxml"));
        stage.setScene(new Scene(root, 800,600));
    }

    @FXML
    private void handleEditAntrenamentAction() throws Exception{
        Stage stage = (Stage) editAntrenament.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/editAntrenament.fxml"));
        stage.setScene(new Scene(root, 800,600));
    }

    @FXML
    private void handleDeleteAntrenamentAction() throws Exception{
        Stage stage = (Stage) deleteAntrenament.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/deleteAntrenament.fxml"));
        stage.setScene(new Scene(root, 800,600));
    }

    @FXML
    private void handleLogoutAction() throws Exception{
        Stage stage = (Stage) logout.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/homepage.fxml"));
        stage.setScene(new Scene(root, 800,600));
    }

    @FXML
    private void handleViewReservationsAction() throws Exception{
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("fxml/viewAllBookings.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        ViewAllBookingsController controller = loader.getController();
        controller.initialize();
        Stage stage = (Stage) (viewReservations.getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }
}
