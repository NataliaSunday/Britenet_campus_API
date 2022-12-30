package pl.britenet.campus_api_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.britenet.campus_api.model.User;
import pl.britenet.campus_api.service.tableService.UserService;
import pl.britenet.campus_api_spring.service.AuthService;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/v1/user")
public class UsersController {

    private final UserService userService;

    private  final AuthService authService;

    @Autowired
    public UsersController(UserService userService, AuthService authService){
        this.userService = userService;
        this.authService = authService;
    }

    @GetMapping
    public List<User> getUserAll(){
        return this.userService.getUserAll();
    }

    @GetMapping("/getUser")
    public User getUserOne(@RequestHeader ("Authorization") String token){
        int userId = this.authService.getUserId(token);
        return this.userService.getUserOne(userId);
    }

    @PostMapping
    public User insertUser(@RequestBody User user){
         this.userService.insertUser(user);
         return user;
    }

    @PutMapping
    public void updateUser(@RequestBody User user){
        this.userService.updateUser(user);
    }

    @DeleteMapping("/{userId}")
    public void delUser(@PathVariable int userId) {
        this.userService.delUser(userId);
    }
}
