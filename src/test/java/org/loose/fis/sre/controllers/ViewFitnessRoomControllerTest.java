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
import org.loose.fis.sre.services.UserService;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
class ViewFitnessRoomControllerTest {

    private static final String ADMIN = "admin";
    private static final String ROLE = "Sala de sport";
    private ViewFitnessRoomController controller;
    @BeforeEach
    void setUp() throws Exception{
        FileSystemService.APPLICATION_FOLDER = ".test-fitnessRoomsList";

    }

    @AfterEach
    void tearDown() {
        UserService.close();
        AntrenamentService.close();
    }

    @Start
    void start(Stage primaryStage) throws Exception {
        FileSystemService.initDirectory();
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
        AntrenamentService.initDatabase();
        UserService.addUser(ADMIN,ADMIN,ADMIN,ROLE);
        AntrenamentService.addAntrenament(ADMIN,ADMIN,ADMIN,ADMIN,0);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/viewFitnessRoomList.fxml"));
        Parent root = fxmlLoader.load();
        controller = fxmlLoader.getController();
        controller.set();
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    @Test
    @DisplayName("Correct fitnessRoom added in list")
    void testCorrectList(){
        assertThat(controller.getFitnessRooms().size()).isEqualTo(1);
        String name=controller.getFitnessRooms().get(0);
        assertThat(name.equals(ADMIN));
    }

    @Test
    @DisplayName("Testing select button")
    void testSelectButton(FxRobot robot){
        robot.clickOn(controller.getFitnessRooms().get(0));
        robot.clickOn("#select");
        robot.clickOn("#antrenamentsPage");
    }

    @Test
    @DisplayName("Testing back button")
    void testBackButtonFunctionality(FxRobot robot) {
        robot.clickOn("#back");
        robot.clickOn("#sportsmanPage");
    }


}