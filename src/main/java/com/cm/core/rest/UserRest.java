package com.cm.core.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user")
public class UserRest {

    @GetMapping("/greeting/{name}")
    public String greeting(@PathVariable String name) {
        return "Hello " + name;
    }
}
