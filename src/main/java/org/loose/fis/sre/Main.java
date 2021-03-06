package org.loose.fis.sre;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.loose.fis.sre.services.AntrenamentService;
import org.loose.fis.sre.services.FileSystemService;
import org.loose.fis.sre.services.ReservationService;
import org.loose.fis.sre.services.UserService;

import java.nio.file.Files;
import java.nio.file.Path;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        UserService.initDatabase();
        AntrenamentService.initDatabase();
        ReservationService.initDatabase();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/homepage.fxml"));
        primaryStage.setTitle("Fit-Connect");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

