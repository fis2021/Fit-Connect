package org.loose.fis.sre.exceptions;
public class AccountCrdentialsException extends Exception{
    private String message;

    public AccountCrdentialsException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
