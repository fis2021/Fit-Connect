package org.loose.fis.sre.exceptions;

import org.loose.fis.sre.model.Antrenament;

public class ReservationAlreadyExistsException extends Exception{
    private final Antrenament antrenament=new Antrenament();

    public ReservationAlreadyExistsException(Antrenament antrenament) {
        super(String.format("O clasa de antrenament cu numele %s exista deja!", antrenament.getName()));
    }

    public Antrenament getAntrenament() {
        return antrenament;
    }
}

