package com.ylluberes.dofus_collector_api.controllers;

import com.ylluberes.dofus_collector_api.domain.Users;
import com.ylluberes.dofus_collector_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/dofus-collector/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/createUser")
    public ResponseEntity<String> createUser (@RequestBody  Users user) {

        try{
            this.userService.saveOrUpdate(user);
        }catch (Exception ex){
            return new ResponseEntity<>("User cannot be created",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("User successfully created",HttpStatus.OK);
    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<Users>> getAllUsers () {
        List<Users> userList = null;
        try{
            userList = userService.getAllUsers();
        }catch (Exception e) {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(userList,HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Users> findById (@PathVariable String id){
        Users user = null;
        try{
            user = userService.findById(id);
        }catch (Exception ex) {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(user == null)
           return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(user,HttpStatus.OK);

    }


}
