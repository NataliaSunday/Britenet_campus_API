package pl.britenet.campus_api_spring.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.britenet.campus_api.model.User;
import pl.britenet.campus_api.service.tableService.UserService;
import pl.britenet.campus_api_spring.controller.UsersController;
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


        User user = this.userService.getUserAuth(credentials.getNickname(), credentials.getPassword());

        if(user == null){
            throw new IllegalStateException();
        }

        String token = UUID.randomUUID().toString();
        this.activeTokenMap.put(token, user.getIdUser());

        LoginResponse loginResponse = new LoginResponse(true, token);
        System.out.println(token);
        return loginResponse;
    }

    public boolean isLoggedIn(String token) {
        return this.activeTokenMap.containsKey(token);
    }

    public void register(User user) {

       try{
           String password = user.getPassword();
           MessageDigest md = MessageDigest.getInstance("SHA");
           md.update(password.getBytes());
           byte[] byteArray = md.digest();
           StringBuilder newPassword = new StringBuilder();

           for(byte b : byteArray){
               newPassword.append(String.format("%02x", b));
           }

           user.setPassword(newPassword.toString());
           userService.insertUser(user);

       }catch (NoSuchAlgorithmException e){
           System.out.println("Error");
       }


// najpierw za hashować
        //potem dodac usera
    }
}
