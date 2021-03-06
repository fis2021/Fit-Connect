package org.loose.fis.sre.model;

import org.dizitart.no2.objects.Id;

import java.util.Objects;

public class User {
    @Id
    private String name;
    private String username;
    private String password;
    private String role;


    public User(String name, String username, String password, String role) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
    }
    private String selectedFitnessRoom;

    public String getSelectedFitnessRoom() {
        return selectedFitnessRoom;
    }

    public void setSelectedFitnessRoom(String selectedFitnessRoom) {
        this.selectedFitnessRoom = selectedFitnessRoom;
    }
    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    private static String currentUser;

    public static String getCurrentUser() {
        return currentUser;
    }
    public static void setCurrentUser(String user){
        currentUser=user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, username, password, role);
    }
}

