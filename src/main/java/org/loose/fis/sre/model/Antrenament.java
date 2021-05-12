package org.loose.fis.sre.model;

import java.util.Objects;

public class Antrenament {
    private String fitnessRoomName;
    private String name;
    private String antrenor;
    private String timeSlot;
    private int price;

    public Antrenament(String fitnessRoomName, String name, String antrenor, String timeSlot, int price) {
        this.fitnessRoomName = fitnessRoomName;
        this.name = name;
        this.antrenor = antrenor;
        this.timeSlot = timeSlot;
        this.price = price;
    }

    public Antrenament() {
    }

    public String getAntrenor() {
        return antrenor;
    }

    public void setAntrenor(String antrenor) {
        this.antrenor = antrenor;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getFitnessRoomName() {
        return fitnessRoomName;
    }

    public void setFitnessRoomName(String fitnessRoomName) {
        this.fitnessRoomName = fitnessRoomName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Antrenament that = (Antrenament) o;
        return price == that.price && Objects.equals(fitnessRoomName, that.fitnessRoomName) && Objects.equals(name, that.name) && Objects.equals(antrenor, that.antrenor) && Objects.equals(timeSlot, that.timeSlot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fitnessRoomName, name, antrenor, timeSlot, price);
    }

    @Override
    public String toString() {
        return fitnessRoomName +
                ", name='" + name  +
                ", antrenor='" + antrenor +
                ", timeSlot='" + timeSlot +
                ", price=" + price;
    }
}

