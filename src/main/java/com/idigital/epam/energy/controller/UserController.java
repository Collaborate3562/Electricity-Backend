package com.idigital.epam.energy.controller;

import com.idigital.epam.energy.entity.User;
import com.idigital.epam.energy.repository.HomeRepository;
import com.idigital.epam.energy.repository.UserRepository;
import com.idigital.epam.energy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("api/user")
@CrossOrigin(origins = "*",maxAge = 3600)
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;
    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        User user = userService.getById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUser() {
        List<User> userList = userService.getAll();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    //    @ApiOperation(value = "Delete User",consumes = "userId",
//            response =String.class)
//    @DeleteMapping("/{id}")
//    public String deleteUser(@PathVariable Integer id) {
//        try {
//            Integer status=userService.deleteUser(id);
//            if(status==1) {
//                return "user: "+id+" deleted from database";
//            }
//            else {
//                return "Unable to delete user from database";
//            }
//        }catch(ExecutionControl.UserException userException)
//        {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,userException.getMessage());
//        }
//    }
//
//    @ApiOperation(value = "Update User",
//            consumes = "receives User object as request body",
//            response = User.class)
//    @PutMapping("/{id}")
//    public ResponseEntity<User> updateUserProfile(@PathVariable Long id, @RequestBody User user){
//        try {
//            User updatedUser =userService.updateUserProfile(user);
//            return new ResponseEntity<>(updatedUser,HttpStatus.OK);
//        }catch(ExecutionControl.UserException userException)
//        {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,userException.getMessage());
//        }
//    }




}