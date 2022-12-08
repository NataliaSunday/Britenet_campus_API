package pl.britenet.campus_api_spring.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.britenet.campus_api.model.User;
import pl.britenet.campus_api.service.tableService.UserService;
import pl.britenet.campus_api_spring.model.Credentials;
import pl.britenet.campus_api_spring.model.LoginResponse;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class AuthService {
    private final UserService userService;
    @Autowired
    public AuthService(UserService userService){
        this.userService = userService;
    }

    private  final Map<String, Integer> activeTokenMap = new HashMap<>();

    public LoginResponse login(Credentials credentials){

        try{
            MessageDigest md = MessageDigest.getInstance("SHA");
            md.update(credentials.getPassword().getBytes());
            byte[] byteArray = md.digest();
            StringBuilder newCredentialsPassword = new StringBuilder();

            for(byte b : byteArray){
                newCredentialsPassword.append(String.format("%02x", b));
            }

            String newCredentialsPasswordString = newCredentialsPassword.toString();
            User user = this.userService.getUserAuth(credentials.getNickname(),newCredentialsPasswordString);

           if(user != null){

               String token = UUID.randomUUID().toString();
               this.activeTokenMap.put(token, user.getIdUser());

               LoginResponse loginResponse = new LoginResponse(true, token);
               System.out.println(token);
               return loginResponse;

           }else{
              throw new NullPointerException();
           }

        }catch (NoSuchAlgorithmException e){
            System.out.println(e);
        }catch (NullPointerException e){
            System.out.println("User doesn't exist");
        }

        LoginResponse loginResponse = new LoginResponse(false, null);
        System.out.println("Error");
        return loginResponse;

    }

    public boolean isLoggedIn(String token) {
        return this.activeTokenMap.containsKey(token);
    }

    public void register(User user) {

        try{
            MessageDigest md = MessageDigest.getInstance("SHA");
            md.update(user.getPassword().getBytes());
            byte[] byteArray = md.digest();
            StringBuilder newCredentialsPassword = new StringBuilder();

            for(byte b : byteArray){
                newCredentialsPassword.append(String.format("%02x", b));
            }

            String newCredentialsPasswordString = newCredentialsPassword.toString();

            user.setPassword(newCredentialsPasswordString);
            userService.insertUser(user);

        }catch (NoSuchAlgorithmException e){
            System.out.println(e.getMessage());
        }
    }
}
