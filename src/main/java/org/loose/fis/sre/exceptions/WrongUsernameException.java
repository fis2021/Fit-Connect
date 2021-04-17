package org.loose.fis.sre.exceptions;

public class WrongUsernameException extends Exception{
    private String username;
    public WrongUsernameException(){
        super("Username-ul este gresit!Incearca din nou!");
    }
}
