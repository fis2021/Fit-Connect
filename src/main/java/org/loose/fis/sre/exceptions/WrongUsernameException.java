package org.loose.fis.sre.exceptions;

public class WrongUsernameException extends AccountCrdentialsException{
    private final String username;
    public WrongUsernameException(String username){
        super("Username-ul este gresit!Incearca din nou!");
        this.username=username;
    }

    public String getUsername() {
        return username;
    }
}
