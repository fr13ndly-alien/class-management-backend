package com.cm.core.rest;

import com.cm.core.entity.User;
import com.cm.core.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
public class UserRest {
    @Autowired
    private UserService userService;

    @GetMapping("/greeting/{name}")
    public String greeting(@PathVariable String name) {

        return "Hello " + name;
    }

    @GetMapping("/")
    public List<User> getAllUser() {

        return userService.getAllUsers();
    }

    @PostMapping("/")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User updateUser) {
        return userService.updateUser(new ObjectId(id), updateUser);
    }

    @DeleteMapping("/")
    public HttpStatus deleteUser(@PathVariable String id) {
        userService.deleteUser(new ObjectId(id));
        return HttpStatus.OK;
    }
}
