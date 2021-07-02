package com.ylluberes.dofus.collector.api.controllers;

import com.ylluberes.dofus.collector.api.domain.User;
import com.ylluberes.dofus.collector.api.dto.responses.GenericResponse;
import com.ylluberes.dofus.collector.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/dofus-collector/api/v1/users")
/**
 * TODO: Handled exception ?
 * TODO: log error | log input request
 */
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<GenericResponse> createUser(@RequestBody User user) {
        GenericResponse<User> genericResponse = userService.saveOrUpdate(user);
        return new ResponseEntity<>(genericResponse, genericResponse.getServerStatus());
    }

    @GetMapping("/all")
    public ResponseEntity<GenericResponse> getAllUsers() {
        GenericResponse<List<User>> genericResponse = userService.getAllUsers();
        return new ResponseEntity<>(genericResponse, genericResponse.getServerStatus());
    }

    @GetMapping("/findById/{userId}")
    public ResponseEntity<GenericResponse> findById(@PathVariable String userId) {
        GenericResponse genericResponse = userService.findById(userId);
        return new ResponseEntity<>(genericResponse, genericResponse.getServerStatus());
    }

    @PatchMapping("/attachMission/{userId}")
    public ResponseEntity<GenericResponse> attachMission (@PathVariable String userId){
        GenericResponse response = userService.attachMission(userId);
        return new ResponseEntity<>(response,response.getServerStatus());
    }


}
