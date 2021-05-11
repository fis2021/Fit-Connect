package org.loose.fis.sre.exceptions;

public class AntrenamentAlreadyExistsException extends Exception{
    private final String name;

    public AntrenamentAlreadyExistsException(String name) {
        super(String.format("O clasa de antrenament cu numele %s exista deja!", name));
        this.name=name;
    }

    public String getName() {
        return name;
    }

}
