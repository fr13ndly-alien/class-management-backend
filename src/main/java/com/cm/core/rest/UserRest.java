package com.cm.core.rest;

import com.cm.core.entity.User;
import com.cm.core.request_object.LoginRequest;
import com.cm.core.service.UserService;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserRest {
    @Autowired
    private UserService userService;

    @GetMapping("/greeting/{name}")
    public String greeting(@PathVariable String name) {

        return "Hello " + name;
    }

    @GetMapping("/")
    public List<Document> getAllUser() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public Document findById(@PathVariable String id) {
        return userService.findById(new ObjectId(id));
    }

    @PostMapping("/")
    public Document addUser(@RequestBody Document user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public Document updateUser(@PathVariable String id, @RequestBody Document updateUser) {
        return userService.updateUser(new ObjectId(id), updateUser);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteUser(@PathVariable String id) {
        userService.delete(new ObjectId(id));
        return HttpStatus.OK;
    }


    @PostMapping("/login")
    public Document login(@RequestBody LoginRequest req) {
        return userService.login(req);
    }
}
