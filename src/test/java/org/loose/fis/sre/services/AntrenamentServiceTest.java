package org.loose.fis.sre.services;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.loose.fis.sre.exceptions.AntrenamentAlreadyExistsException;
import org.loose.fis.sre.exceptions.EmptyTextfieldsException;
import org.loose.fis.sre.model.Antrenament;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AntrenamentServiceTest {
    private static final String FITNESSROOM_NAME = "FITNESSROOM_NAME";
    private static final String NAME = "NAME";
    private static final String ANTRENOR = "ANTRENOR";
    private static final String TIMESLOT = "TIMESLOT";
    private static final int PRICE = 0;

    @BeforeEach
    void setUp() throws Exception{
        FileSystemService.APPLICATION_FOLDER = ".antrenament-test";
        FileSystemService.initDirectory();
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        AntrenamentService.initDatabase();
    }

    @AfterEach
    void tearDown() {
        AntrenamentService.close();
    }

    @Test
    @DisplayName("Baza de date este initializata si nu are nicio clasa de antrenament")
    void testDatabaseIsInitializedAndNoAntrenamentIsPersisted() {
        assertThat(AntrenamentService.getAllAntrenaments()).isNotNull();
        assertThat(AntrenamentService.getAllAntrenaments()).isEmpty();
    }

    @Test
    @DisplayName("Antrenament is successfully persisted to Database")
    void testAntrenamentIsAddedToDatabase() throws AntrenamentAlreadyExistsException {
        AntrenamentService.addAntrenament(FITNESSROOM_NAME,NAME,ANTRENOR,TIMESLOT,PRICE);
        assertThat(AntrenamentService.getAllAntrenaments()).isNotEmpty();
        assertThat(AntrenamentService.getAllAntrenaments().size()).isEqualTo(1);
        Antrenament antrenament = AntrenamentService.getAllAntrenaments().get(0);
        assertThat(antrenament).isNotNull();
        assertThat(antrenament.getFitnessRoomName()).isEqualTo(FITNESSROOM_NAME);
        assertThat(antrenament.getName()).isEqualTo(NAME);
        assertThat(antrenament.getAntrenor()).isEqualTo(ANTRENOR);
        assertThat(antrenament.getTimeSlot()).isEqualTo(TIMESLOT);
        assertThat(antrenament.getPrice()).isEqualTo(0);
    }

    @Test
    @DisplayName("Antrenament can not be added twice")
    void testAntrenamentCanNotBeAddedTwice() {
        assertThrows(AntrenamentAlreadyExistsException.class, () -> {
            AntrenamentService.addAntrenament(FITNESSROOM_NAME,NAME,ANTRENOR,TIMESLOT,PRICE);
            AntrenamentService.addAntrenament(FITNESSROOM_NAME,NAME,ANTRENOR,TIMESLOT,PRICE);
        });
    }

    @Test
    @DisplayName("A specific exception thrown if the name of the antrenament can be found in the database")
    void testCheckAntrenamentDoesNotAlreadyExists() throws AntrenamentAlreadyExistsException {
        AntrenamentService.addAntrenament(FITNESSROOM_NAME,NAME,ANTRENOR,TIMESLOT,PRICE);
        assertThrows(AntrenamentAlreadyExistsException.class, () -> AntrenamentService.checkAntrenamentDoesNotAlreadyExist(NAME));
    }


    @Test
    @DisplayName("Antrenament is successfully edit")
    void testEditAntrenament() throws EmptyTextfieldsException,AntrenamentAlreadyExistsException{
        AntrenamentService.addAntrenament(FITNESSROOM_NAME,NAME,ANTRENOR,TIMESLOT,PRICE);
        AntrenamentService.editAntrenament(FITNESSROOM_NAME+"1",NAME,ANTRENOR+"1",TIMESLOT+"1",PRICE+1);
        Antrenament antrenament = AntrenamentService.getAllAntrenaments().get(0);
        assertThat(antrenament).isNotNull();
        assertThat(antrenament.getFitnessRoomName()).isEqualTo(FITNESSROOM_NAME);
        assertThat(antrenament.getName()).isEqualTo(NAME);
        assertThat(antrenament.getAntrenor()).isEqualTo(ANTRENOR);
        assertThat(antrenament.getTimeSlot()).isEqualTo(TIMESLOT);
        assertThat(antrenament.getPrice()).isEqualTo(0);
    }


    @Test
    @DisplayName("Antrenament is successfully delete")
    void testDeleteAntrenament() throws EmptyTextfieldsException,AntrenamentAlreadyExistsException{
        AntrenamentService.addAntrenament(FITNESSROOM_NAME,NAME,ANTRENOR,TIMESLOT,PRICE);
        AntrenamentService.deleteAntrenament(FITNESSROOM_NAME,NAME,ANTRENOR,TIMESLOT,PRICE);
        assertThat(AntrenamentService.getAllAntrenaments()).isEmpty();
    }

}