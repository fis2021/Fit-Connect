package org.loose.fis.sre.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.loose.fis.sre.services.AntrenamentService;
import org.loose.fis.sre.services.FileSystemService;
import org.loose.fis.sre.services.ReservationService;
import org.loose.fis.sre.services.UserService;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

@ExtendWith(ApplicationExtension.class)
class SportsmanPageControllerTest {

    @BeforeEach
    void setUp() throws Exception{
        FileSystemService.APPLICATION_FOLDER = ".test-sportiv";
        FileSystemService.initDirectory();
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
        AntrenamentService.initDatabase();
        ReservationService.initDatabase();
    }

    @AfterEach
    void tearDown() {
        UserService.close();
        AntrenamentService.close();
        ReservationService.close();
    }

    @Start
    void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/sportsmanPage.fxml"));
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    @Test
    @DisplayName("LogOut button correct functionality")
    void testLogOutButton(FxRobot robot) {
        robot.clickOn("#logout");
        robot.clickOn("#homepage");
    }

    @Test
    @DisplayName("View FitnessRooms button correct functionality")
    void testViewFitnessRoomsButton(FxRobot robot) {
        robot.clickOn("#viewFitnessRoom");
        robot.clickOn("#viewFitnessRooms");
    }

    @Test
    @DisplayName("View my rezervations button correct functionality")
    void testViewMyRezervationsButton(FxRobot robot) {
        robot.clickOn("#viewMyReservations");
        robot.clickOn("#viewMyBookings");
    }




}