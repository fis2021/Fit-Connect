package org.loose.fis.sre.exceptions;

public class WrongPasswordException extends Exception{
    private String password;
    public WrongPasswordException(){
        super("Parola este gresita!Incearca din nou!");
    }
}
