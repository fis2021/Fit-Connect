package org.loose.fis.sre.exceptions;

public class WrongPasswordException extends AccountCrdentialsException{
    private String password;
    public WrongPasswordException(String password){
        super("Parola este gresita!Incearca din nou!");
        this.password=password;
    }

    public String getPassword() {
        return password;
    }
}
