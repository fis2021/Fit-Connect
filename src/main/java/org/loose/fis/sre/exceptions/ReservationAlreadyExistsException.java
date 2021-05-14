package org.loose.fis.sre.exceptions;

import org.loose.fis.sre.model.Antrenament;

public class ReservationAlreadyExistsException extends Exception{
    private Antrenament antrenament;

    public ReservationAlreadyExistsException(Antrenament antrenament) {
        super(String.format("Ati rezervat deja o clasa de antrenament cu numele %s!", antrenament.getName()));
    }

    public Antrenament getAntrenament() {
        return antrenament;
    }
}
