package org.loose.fis.sre.exceptions;

public class EmptyTextfieldsException extends Exception{
    public String getMessage;

    public EmptyTextfieldsException(){
        super(String.format("Trebuie sa completezi toate campurile!"));
    }
}
