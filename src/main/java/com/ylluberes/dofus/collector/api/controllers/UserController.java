package com.ylluberes.dofus.collector.api.controllers;

import com.ylluberes.dofus.collector.api.domain.User;
import com.ylluberes.dofus.collector.api.dto.request.InAuthSignUp;
import com.ylluberes.dofus.collector.api.dto.responses.GenericResponse;
import com.ylluberes.dofus.collector.api.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/dofus-collector/api/v1/users")
@AllArgsConstructor
/**
 * TODO: Handled exception ?
 * TODO: log error | log input request
 */
public class UserController {

    private final UserService userService;

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
