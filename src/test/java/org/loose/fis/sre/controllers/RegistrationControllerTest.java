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
import org.loose.fis.sre.services.AntrenamentService;
import org.loose.fis.sre.services.FileSystemService;
import org.loose.fis.sre.services.UserService;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.Start;

import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.assertions.api.Assertions.assertThat;

class RegistrationControllerTest {
    private static final String CORRECT_NAME = "nameCorrect";
    private static final String CORRECT_USERNAME = "usernameCorrect";
    private static final String CORRECT_PASSWORD = "passwordCorrect";
    private static final String SPORTIV_ROLE = "Sportiv";
    private static final String MANAGER_ROLE = "Sala de sport";

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-registration";
        FileSystemService.initDirectory();
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
    }

    @AfterEach
    void tearDown() {
        System.out.println("After class");
        UserService.close();
    }

    @Start
    void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("register.fxml"));
        primaryStage.setTitle("Register");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    @Test
    @DisplayName("Creare cont de sportiv")
    void testRegistrationSportiv(FxRobot robot) {
        robot.clickOn("#name");
        robot.write(CORRECT_NAME);
        robot.clickOn("#username");
        robot.write(CORRECT_USERNAME);
        robot.clickOn("#password");
        robot.write(CORRECT_PASSWORD);
        robot.clickOn("#role");
        robot.clickOn(SPORTIV_ROLE);
        robot.clickOn("#register");

        assertThat(UserService.getAllUsers().size()).isEqualTo(1);
        assertThat(UserService.getAllUsers().get(0).getName()).isEqualTo(CORRECT_NAME);
        assertThat(UserService.getAllUsers().get(0).getUsername()).isEqualTo(CORRECT_USERNAME);
        assertThat(UserService.getAllUsers().get(0).getPassword()).isEqualTo(UserService.encodePassword(CORRECT_USERNAME, CORRECT_PASSWORD));
        assertThat(UserService.getAllUsers().get(0).getRole()).isEqualTo(SPORTIV_ROLE);
        assertThat(robot.lookup("#registrationMessage").queryText()).hasText("Contul a fost creat cu succes!");
    }

    @Test
    @DisplayName("Creare cont de manager sala de sport")
    void testRegistrationManager(FxRobot robot) {
        robot.clickOn("#name");
        robot.write(CORRECT_NAME);
        robot.clickOn("#username");
        robot.write(CORRECT_USERNAME);
        robot.clickOn("#password");
        robot.write(CORRECT_PASSWORD);
        robot.clickOn("#role");
        robot.clickOn(SPORTIV_ROLE);
        robot.clickOn("#register");

        assertThat(UserService.getAllUsers().size()).isEqualTo(1);
        assertThat(UserService.getAllUsers().get(0).getName()).isEqualTo(CORRECT_NAME);
        assertThat(UserService.getAllUsers().get(0).getUsername()).isEqualTo(CORRECT_USERNAME);
        assertThat(UserService.getAllUsers().get(0).getPassword()).isEqualTo(UserService.encodePassword(CORRECT_USERNAME, CORRECT_PASSWORD));
        assertThat(UserService.getAllUsers().get(0).getRole()).isEqualTo(MANAGER_ROLE);
        assertThat(robot.lookup("#registrationMessage").queryText()).hasText("Contul a fost creat cu succes!");
    }

    @Test
    @DisplayName("Registration failed. Account already exists")
    void testRegistrationAccountAlreadyExists(FxRobot robot) {
        robot.clickOn("#name");
        robot.write(CORRECT_NAME);
        robot.clickOn("#username");
        robot.write(CORRECT_USERNAME);
        robot.clickOn("#password");
        robot.write(CORRECT_PASSWORD);
        robot.clickOn("#role");
        robot.clickOn(SPORTIV_ROLE);
        robot.clickOn("#register");

        robot.clickOn("#register");
        assertThat(robot.lookup("#registrationMessage").queryText()).hasText(String.format("Un cont cu numele %s exista deja!", CORRECT_USERNAME));
    }

    @Test
    @DisplayName("Testing 'Back to login button' from registration")
    void testBackToLoginButtonFromRegistration(FxRobot robot) {
        robot.clickOn("#backMessage");
        //robot.clickOn("#loginFile");
    }
}