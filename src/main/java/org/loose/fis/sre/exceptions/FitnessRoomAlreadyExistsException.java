package org.loose.fis.sre.exceptions;

public class FitnessRoomAlreadyExistsException extends Exception{
    private final String name;
    public FitnessRoomAlreadyExistsException(String name) {
        super(String.format("O sala cu numele %s exista deja!", name));
        this.name=name;
    }

    public String getName() {
        return name;
    }

}
