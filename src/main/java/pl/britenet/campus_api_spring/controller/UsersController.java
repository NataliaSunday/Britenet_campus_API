package pl.britenet.campus_api_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.britenet.campus_api.model.User;
import pl.britenet.campus_api.service.tableService.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUserAll(){
        return this.userService.getUserAll();
    }

    @GetMapping("/{userId}")
    public User getUserOne(@PathVariable int userId){
        return this.userService.getUserOne(userId);
    }

    @PostMapping
    public User insertUser(@RequestBody User user){
         this.userService.insertUser(user);
         return user;
    }
    @DeleteMapping("/{userId}")
    public void delUser(@PathVariable int userId) {
        this.userService.delUser(userId);
    }




}
