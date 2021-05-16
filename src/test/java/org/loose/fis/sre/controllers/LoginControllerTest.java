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
import org.loose.fis.sre.services.FileSystemService;
import org.loose.fis.sre.services.UserService;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
class LoginControllerTest {
    private static final String CORRECT_NAME = "nameCorrect";
    private static final String CORRECT_USERNAME = "usernameCorrect";
    private static final String INCORRECT_USERNAME = "usernameIncorrect";
    private static final String CORRECT_PASSWORD = "passwordCorrect";
    private static final String INCORRECT_PASSWORD = "passwordIncorrect";
    private static final String SPORTIV_ROLE = "Sportiv";
    private static final String MANAGER_ROLE = "Sala de sport";
    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-login";
        FileSystemService.initDirectory();
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
    }

    @AfterEach
    void tearDown() {
        UserService.close();
    }
    @Start
    void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/login.fxml"));
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    @Test
    @DisplayName("Testare login pentru sportiv")
    void testSportivLogin(FxRobot robot) throws Exception{
        UserService.addUser(CORRECT_NAME,CORRECT_USERNAME, CORRECT_PASSWORD, SPORTIV_ROLE);
        robot.clickOn("#username");
        robot.write(CORRECT_USERNAME);
        robot.clickOn("#password");
        robot.write(CORRECT_PASSWORD);
        robot.clickOn("#loginButton");

        robot.clickOn("#sportsmanPage");
    }

    @Test
    @DisplayName("Testare login pentru manager sala de sport")
    void testManagerSuccessfullyLogin(FxRobot robot) throws Exception{
        UserService.addUser(CORRECT_NAME,CORRECT_USERNAME, CORRECT_PASSWORD, MANAGER_ROLE);
        robot.clickOn("#username");
        robot.write(CORRECT_USERNAME);
        robot.clickOn("#password");
        robot.write(CORRECT_PASSWORD);
        robot.clickOn("#loginButton");
        robot.clickOn("#fitnessRoomPage");
    }

    @Test
    @DisplayName("Incorrect username")
    void testLoginButtonIncorrectUsername(FxRobot robot) {
        robot.clickOn("#username");
        robot.write(INCORRECT_USERNAME);
        robot.clickOn("#password");
        robot.write(CORRECT_PASSWORD);
        robot.clickOn("#loginButton");

        assertThat(robot.lookup("#loginMessage").queryText()).hasText("Username-ul este gresit!Incearca din nou!");
    }

    @Test
    @DisplayName("Incorrect password")
    void testLoginButtonIncorrectPassword(FxRobot robot) throws Exception{
        UserService.addUser(CORRECT_NAME,CORRECT_USERNAME, CORRECT_PASSWORD, SPORTIV_ROLE);

        robot.clickOn("#username");
        robot.write(CORRECT_USERNAME);
        robot.clickOn("#password");
        robot.write(INCORRECT_PASSWORD);
        robot.clickOn("#loginButton");

        assertThat(robot.lookup("#loginMessage").queryText()).hasText("Parola este gresita!Incearca din nou!");
    }

    @Test
    @DisplayName("Empty username")
    void testLoginButtonEmptyUsername(FxRobot robot) throws Exception{
        UserService.addUser(CORRECT_NAME,CORRECT_USERNAME, CORRECT_PASSWORD, SPORTIV_ROLE);

        robot.clickOn("#username");
        robot.write("");
        robot.clickOn("#password");
        robot.write(CORRECT_PASSWORD);
        robot.clickOn("#loginButton");

        assertThat(robot.lookup("#loginMessage").queryText()).hasText("Campul username este gol!");
    }

    @Test
    @DisplayName("Empty password")
    void testLoginButtonEmptyPassword(FxRobot robot) throws Exception{
        UserService.addUser(CORRECT_NAME,CORRECT_USERNAME, CORRECT_PASSWORD, SPORTIV_ROLE);

        robot.clickOn("#username");
        robot.write(CORRECT_USERNAME);
        robot.clickOn("#password");
        robot.write("");
        robot.clickOn("#loginButton");

        assertThat(robot.lookup("#loginMessage").queryText()).hasText("Campul password este gol!");
    }



}