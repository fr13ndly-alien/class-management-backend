package com.cm.core.rest;


import com.cm.core.entity.Centre;
import com.cm.core.service.CentreService;
import com.cm.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/centre")
public class CentreRest {

    @Autowired
    CentreService centreService;

    @Autowired
    UserService userService;

    @GetMapping("/")
    public List<Centre> findAllCentre() {
        return centreService.getAll();
    }

    @GetMapping("/{id}/")
    public Optional<Centre> findById(@PathVariable String id) {
        return centreService.findById(id);
    }

    @PostMapping("/")
    public Centre establishNewCentre(@RequestBody Centre centre) {
        return centreService.create(centre);
    }

    @PutMapping("/{id}")
    public Centre updateCentre(@PathVariable String id, @RequestBody Centre centre) {
        return centreService.update(id, centre);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeCentre() {

        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
