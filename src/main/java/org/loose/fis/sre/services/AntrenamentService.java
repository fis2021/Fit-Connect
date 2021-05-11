package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.AntrenamentAlreadyExistsException;
import org.loose.fis.sre.model.Antrenament;

import java.util.ArrayList;
import java.util.Objects;

import static org.loose.fis.sre.services.FileSystemService.getPathToFile;

public class AntrenamentService {
    private static ObjectRepository<Antrenament> antrenamentRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("Antrenament.db").toFile())
                .openOrCreate("test", "test");

        antrenamentRepository = database.getRepository(Antrenament.class);

    }
    public static void addAntrenament(String fitnessRoomName, String name,String antrenor, String timeSlot,int price) throws AntrenamentAlreadyExistsException {
        checkAntrenamentDoesNotAlreadyExist(name);
        antrenamentRepository.insert(new Antrenament(fitnessRoomName,name,antrenor,timeSlot,price));
    }

    private static void checkAntrenamentDoesNotAlreadyExist(String name) throws AntrenamentAlreadyExistsException {
        for (Antrenament antrenament : antrenamentRepository.find()) {
            if (Objects.equals(name, antrenament.getName()))
                throw new AntrenamentAlreadyExistsException(name);
        }
    }

    public static ArrayList<Antrenament> antrenaments(String fitnessRoomName){
        ArrayList<Antrenament> antrenaments=new ArrayList<>();
        for(Antrenament antrenament: antrenamentRepository.find())
            if(Objects.equals(antrenament.getFitnessRoomName(),fitnessRoomName))
                antrenaments.add(antrenament);
        return antrenaments;
    }
}
