package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.AntrenamentAlreadyExistsException;
import org.loose.fis.sre.exceptions.EmptyTextfieldsException;
import org.loose.fis.sre.model.Antrenament;
import static org.dizitart.no2.objects.filters.ObjectFilters.eq;

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

    public static void editAntrenament(String fitnessRoomName, String name,String antrenor, String timeSlot,int price) throws EmptyTextfieldsException, AntrenamentAlreadyExistsException {
        checkEmptyTextfieldsForEdit(fitnessRoomName,name,antrenor,timeSlot,price);
        checkAntrenamentAlreadyExists(name);

        Antrenament antrenament_aux = new Antrenament();

        for(Antrenament antrenament : antrenamentRepository.find()){
            if(Objects.equals(fitnessRoomName,antrenament.getFitnessRoomName()) && Objects.equals(name,antrenament.getName()) && Objects.equals(antrenor,antrenament.getAntrenor()) && Objects.equals(timeSlot,antrenament.getTimeSlot()) && Objects.equals(price,antrenament.getPrice()))
                antrenament_aux = antrenament;
        }

        if(!Objects.equals(fitnessRoomName,"")){
            antrenament_aux.setFitnessRoomName(fitnessRoomName);
        }

        if(!Objects.equals(name,"")){
            antrenament_aux.setName(name);
        }

        if(!Objects.equals(antrenor,"")){
            antrenament_aux.setAntrenor(antrenor);
        }

        if(!Objects.equals(timeSlot,"")){
            antrenament_aux.setTimeSlot(timeSlot);
        }

        if(!Objects.equals(price,"")){
            antrenament_aux.setPrice(price);
        }

        antrenamentRepository.update(eq("fitnessRoomName",fitnessRoomName), antrenament_aux);
    }

    public static void checkEmptyTextfieldsForEdit(String fitnessRoomName, String name,String antrenor, String timeSlot,int price) throws EmptyTextfieldsException {
        if(Objects.equals(fitnessRoomName,""))
            throw new EmptyTextfieldsException();
        else if(Objects.equals(name,""))
            throw new EmptyTextfieldsException();
        else if(Objects.equals(antrenor,""))
            throw new EmptyTextfieldsException();
        else if(Objects.equals(timeSlot,""))
            throw new EmptyTextfieldsException();
        else if(Objects.equals(price,""))
            throw new EmptyTextfieldsException();
    }

    public static void checkAntrenamentAlreadyExists(String name) throws AntrenamentAlreadyExistsException{
        for (Antrenament antrenament : antrenamentRepository.find()) {
            if (!Objects.equals(name, antrenament.getName()))
                throw new AntrenamentAlreadyExistsException(name);
        }
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
