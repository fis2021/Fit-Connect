package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.AccountCrdentialsException;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.exceptions.WrongPasswordException;
import org.loose.fis.sre.exceptions.WrongUsernameException;
import org.loose.fis.sre.model.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.loose.fis.sre.services.FileSystemService.getPathToFile;

public class UserService {

    private static ObjectRepository<User> userRepository;

    private static Nitrite database;
    public static void initDatabase() {
        FileSystemService.initDirectory();
        database = Nitrite.builder()
                .filePath(getPathToFile("fit-connect.db").toFile())
                .openOrCreate("test", "test");

        userRepository = database.getRepository(User.class);
    }

    public static void addUser(String name,String username, String password, String role) throws UsernameAlreadyExistsException {
        checkUserDoesNotAlreadyExist(username);
        userRepository.insert(new User(name,username, encodePassword(username, password), role));
    }

    static void checkUserDoesNotAlreadyExist(String username) throws UsernameAlreadyExistsException {
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername()))
                throw new UsernameAlreadyExistsException(username);
        }
    }

    public static String getUserRole(String username, String password) throws AccountCrdentialsException {
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername()))
            {
                if (Objects.equals(encodePassword(username,password), user.getPassword()))
                    return user.getRole();
                else
                    throw new WrongPasswordException(password);
            }
        }
        throw new WrongUsernameException(username);
    }

    public static ArrayList<String> users(String role) {
        ArrayList<String> list = new ArrayList<String>();
        for(User user : userRepository.find()) {
            if(Objects.equals(role,user.getRole()) && user.getName()!=null)
                list.add(user.getName());
        }
        return list;
    }

    public static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }

    public static String getUsername (String username) {
        for (User user : userRepository.find()){
            if(username.equals(user.getUsername()))
                return user.getName();
        }
        return null;
    }

    public static User getUser (String username) {
        for (User user : userRepository.find()){
            if(username.equals(user.getUsername()))
                return user;
        }
        return null;
    }

    public static void close() {
        userRepository.close();
        database.close();
    }

    public static List<User> getAllUsers() {
        return userRepository.find().toList();
    }
}

