package org.loose.fis.sre.exceptions;

public class UsernameAlreadyExistsException extends Exception {

    private final String username;

    public UsernameAlreadyExistsException(String username) {
        super(String.format("Un cont cu numele %s exista deja!", username));
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}

