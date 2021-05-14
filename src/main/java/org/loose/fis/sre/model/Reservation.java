package org.loose.fis.sre.model;

import java.util.Objects;

public class Reservation {
    private User sportsMan;
    private Antrenament antrenament;

    public Reservation(User sportsMan,Antrenament antrenament)
    {
        this.sportsMan=sportsMan;
        this.antrenament=antrenament;
    }
    public Reservation(){

    }
    public User getSportsMan() {
        return sportsMan;
    }

    public void setSportsMan(User sportsMan) {
        this.sportsMan = sportsMan;
    }

    public Antrenament getAntrenament() {
        return antrenament;
    }

    public void setAntrenament(Antrenament antrenament) {
        this.antrenament = antrenament;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(sportsMan, that.sportsMan) && Objects.equals(antrenament, that.antrenament);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sportsMan, antrenament);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "sportsMan=" + sportsMan +
                ", antrenament=" + antrenament +
                '}';
    }
}
