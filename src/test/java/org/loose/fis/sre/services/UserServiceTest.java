package org.loose.fis.sre.services;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.exceptions.WrongPasswordException;
import org.loose.fis.sre.exceptions.WrongUsernameException;
import org.loose.fis.sre.model.User;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.testfx.assertions.api.Assertions.assertThat;

class UserServiceTest {
    public static final String ADMIN = "admin";

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".user-test";
        FileSystemService.initDirectory();
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
    }

    @AfterEach
    void tearDown() {
        UserService.close();
    }

    @Test
    @DisplayName("Baza de date este initializata si nu are niciun utilizator")
    void testDatabaseIsInitializedAndNoUserIsPersisted() {
        assertThat(UserService.getAllUsers()).isNotNull();
        assertThat(UserService.getAllUsers()).isEmpty();
    }

    @Test
    @DisplayName("User is successfully persisted to Database")
    void testUserIsAddedToDatabase() throws UsernameAlreadyExistsException {
        UserService.addUser(ADMIN, ADMIN, ADMIN, ADMIN);
        assertThat(UserService.getAllUsers()).isNotEmpty();
        assertThat(UserService.getAllUsers()).size().isEqualTo(1);
        User user = UserService.getAllUsers().get(0);
        assertThat(user).isNotNull();
        assertThat(user.getUsername()).isEqualTo(ADMIN);
        assertThat(user.getPassword()).isEqualTo(UserService.encodePassword(ADMIN, ADMIN));
        assertThat(user.getRole()).isEqualTo(ADMIN);
    }

    @Test
    @DisplayName("User can not be added twice")
    void testUserCanNotBeAddedTwice() {
        assertThrows(UsernameAlreadyExistsException.class, () -> {
            UserService.addUser(ADMIN, ADMIN, ADMIN, ADMIN);
            UserService.addUser(ADMIN, ADMIN, ADMIN, ADMIN);
        });
    }

    @Test
    @DisplayName("A specific exception thrown if the username can be found in the database")
    void testCheckUserDoesNotAlreadyExists() throws UsernameAlreadyExistsException {
        UserService.addUser(ADMIN, ADMIN, ADMIN, ADMIN);
        assertThrows(UsernameAlreadyExistsException.class, () -> UserService.checkUserDoesNotAlreadyExist(ADMIN));
    }

    @Test
    @DisplayName("Correct parameters return correct user role")
    void testCorrectUserRoleReturned() throws Exception {
        UserService.addUser(ADMIN, ADMIN, ADMIN, ADMIN);
        assertThat(UserService.getUserRole(ADMIN, ADMIN)).isEqualTo(ADMIN);
    }

    @Test
    @DisplayName("A role cannot be returned if the user has no account")
    void testNoUserForUserRoleCall() throws Exception {
        UserService.addUser(ADMIN, ADMIN, ADMIN, ADMIN);
        assertThrows(WrongUsernameException.class, () -> UserService.getUserRole(ADMIN + "1", ADMIN));
    }

    @Test
    @DisplayName("Wrong password returns a password exception")
    void testWrongPasswordForUserRole() throws Exception {
        UserService.addUser(ADMIN, ADMIN, ADMIN, ADMIN);
        assertThrows(WrongPasswordException.class, () -> UserService.getUserRole(ADMIN, ADMIN + "1"));
    }

    @Test
    @DisplayName("Correct parameters returns a correct username")
    void testCorrectUsernameReturned() throws Exception {
        UserService.addUser(ADMIN, ADMIN, ADMIN, ADMIN);
        assertThat(UserService.getUsername(ADMIN).isEmpty());
    }

    @Test
    @DisplayName("Correct parameters returns a correct User")
    void testCorrectUserReturned() throws Exception {
        UserService.addUser(ADMIN, ADMIN, ADMIN, ADMIN);
        assertThat(UserService.getUser(ADMIN).getName().isEmpty());
    }


}