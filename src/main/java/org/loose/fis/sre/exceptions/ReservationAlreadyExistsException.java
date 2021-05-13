package org.loose.fis.sre.exceptions;

import org.loose.fis.sre.model.Antrenament;

public class ReservationAlreadyExistsException extends Exception{
    private final Antrenament antrenament=new Antrenament();

    public ReservationAlreadyExistsException(Antrenament antrenament) {
        super(String.format("Ati reservat deja o clasa de antrenament cu numele %s!", antrenament.getName()));
    }

    public Antrenament getAntrenament() {
        return antrenament;
    }
}

