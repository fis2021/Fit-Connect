package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.ReservationAlreadyExistsException;
import org.loose.fis.sre.model.Antrenament;
import org.loose.fis.sre.model.Reservation;
import org.loose.fis.sre.model.User;

import java.util.ArrayList;
import java.util.Objects;

import static org.loose.fis.sre.services.FileSystemService.getPathToFile;

public class ReservationService {

    private static ObjectRepository<Reservation> reservationRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("reservation.db").toFile())
                .openOrCreate("test", "test");

        reservationRepository = database.getRepository(Reservation.class);
    }

    public static void addReservation(User sportsMan, Antrenament antrenament) throws ReservationAlreadyExistsException {
        checkReservationDoesNotAlreadyExist(antrenament);
        reservationRepository.insert(new Reservation(sportsMan,antrenament));
    }

    private static void checkReservationDoesNotAlreadyExist(Antrenament antrenament) throws ReservationAlreadyExistsException {
        for (Reservation reservation: reservationRepository.find()) {
            if(Objects.equals(antrenament, reservation.getAntrenament()))
                throw new ReservationAlreadyExistsException(antrenament);
        }
    }

    public static ArrayList<Reservation> reservations(String fitnessRoomName){
        ArrayList<Reservation> list = new ArrayList<>();
        for(Reservation reservation : reservationRepository.find()) {
            if(fitnessRoomName.equals(reservation.getAntrenament().getFitnessRoomName()))
                list.add(reservation);
        }
        return list;
    }

    public static ArrayList<Reservation> myReservations(String username){
        ArrayList<Reservation> list = new ArrayList<>();
        for(Reservation reservation : reservationRepository.find()) {
            if(username.equals(reservation.getSportsMan().getUsername()))
                list.add(reservation);
        }
        return list;
    }
}