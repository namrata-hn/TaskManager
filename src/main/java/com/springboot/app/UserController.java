package com.springboot.app;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {

    private final UserService service;

    public UserController(UserService userService) {
        this.service = userService;
    }

    @GetMapping("/users/{username}")
    public User getUserByUsername(@PathVariable String username){
        return service.findUserByUsername(username);
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user){
        System.out.println(user.getUsername() + user.getPassword() + user.getEmail());
        return this.service.createUser(user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable Long id){
        this.service.deleteUser(id);
    }

    @PostMapping(path = "/users/login")
    public User login(@RequestBody User auth_user){
        User user = service.findUserById(auth_user.getId());
        if(user!=null && user.getPassword().equals(auth_user.getPassword()))
            return user;
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
