package pl.britenet.campus_api_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.britenet.campus_api.model.User;
import pl.britenet.campus_api_spring.model.Credentials;
import pl.britenet.campus_api_spring.model.LoginResponse;
import pl.britenet.campus_api_spring.service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private  final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public LoginResponse login(@RequestBody Credentials credentials){
        return this.authService.login(credentials);
    }

    @GetMapping
    public boolean getUser(@RequestHeader("Authorization") String token ){
        return this.authService.isLoggedIn(token);
    }

    @PostMapping("/register")
    public void register(@RequestBody User user){
         this.authService.register(user);
    }






}//dokonczyc tutaj i aurotryzavha + hashowanie hasła np metoda z klasy (w api dodac get usera i zeb sie hashowało podczas rejestarcji)


