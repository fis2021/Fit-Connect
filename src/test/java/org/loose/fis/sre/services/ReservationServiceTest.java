package org.loose.fis.sre.services;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.loose.fis.sre.exceptions.ReservationAlreadyExistsException;
import org.loose.fis.sre.model.Antrenament;
import org.loose.fis.sre.model.Reservation;
import org.loose.fis.sre.model.User;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReservationServiceTest {
    private static final User SPORTIV = new User("SPORTIV","SPORTIV","SPORTIV","SPORTIV");
    private static final Antrenament ANTRENAMENT= new Antrenament("FITNESSROOM_NAME","NAME","ANTRENOR","TIMESLOT",0);
    @BeforeEach
    void setUp() throws Exception{
        FileSystemService.APPLICATION_FOLDER = ".reservation-test";
        FileSystemService.initDirectory();
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        ReservationService.initDatabase();
    }

    @AfterEach
    void tearDown() {
        ReservationService.close();
    }

    @Test
    @DisplayName("Baza de date este initializata si nu are nicio rezervare")
    void testDatabaseIsInitializedAndNoRezervationIsPersisted() {
        assertThat(ReservationService.getAllReservations()).isNotNull();
        assertThat(ReservationService.getAllReservations()).isEmpty();
    }

    @Test
    @DisplayName("Reservation is successfully persisted to Database")
    void testReservationIsAddedToDatabase() throws ReservationAlreadyExistsException{
        ReservationService.addReservation(SPORTIV,ANTRENAMENT);
        assertThat(ReservationService.getAllReservations()).isNotEmpty();
        assertThat(ReservationService.getAllReservations().size()).isEqualTo(1);
        Reservation reservation=ReservationService.getAllReservations().get(0);
        assertThat(reservation.getSportsMan()).isEqualTo(SPORTIV);
        assertThat(reservation.getAntrenament()).isEqualTo(ANTRENAMENT);
    }

    @Test
    @DisplayName("Reservation can not be added twice")
    void testReservationCanNotBeAddedTwice() {
        assertThrows(ReservationAlreadyExistsException.class, () -> {
            ReservationService.addReservation(SPORTIV,ANTRENAMENT);
            ReservationService.addReservation(SPORTIV,ANTRENAMENT);
        });
    }

    @Test
    @DisplayName("A specific exception thrown if the name of the reservation can be found in the database")
    void testCheckAntrenamentDoesNotAlreadyExists() throws ReservationAlreadyExistsException {
        ReservationService.addReservation(SPORTIV,ANTRENAMENT);
        assertThrows(ReservationAlreadyExistsException.class, () -> ReservationService.checkReservationDoesNotAlreadyExist(ANTRENAMENT));
    }



}