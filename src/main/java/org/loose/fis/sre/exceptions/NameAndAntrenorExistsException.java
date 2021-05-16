package org.loose.fis.sre.exceptions;
import java.util.concurrent.ExecutionException;

public class NameAndAntrenorExistsException extends ExecutionException{
    public String getMessage;

    public NameAndAntrenorExistsException(){
        super(String.format("O clasa cu aceste informatii nu exista. Reintroduceti informatiile!"));
    }
}
