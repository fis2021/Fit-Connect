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
}
