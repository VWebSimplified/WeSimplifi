package com.Wesimplifi.WeSimplifiFirstProject.controller;


import com.Wesimplifi.WeSimplifiFirstProject.customresponsentity.ApiResponse;
import com.Wesimplifi.WeSimplifiFirstProject.entity.User;
import com.Wesimplifi.WeSimplifiFirstProject.repository.UserRepository;
import com.Wesimplifi.WeSimplifiFirstProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private User user;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PostMapping("/registration")
    public ResponseEntity<Object> userRegistration(@RequestBody User user){
        Map<String,Object>  controllerResponse  = userService.userRegister(user);

        if (controllerResponse.containsKey("Alert")){
            ApiResponse<Object> apiResponse = new ApiResponse<>("Error", controllerResponse, HttpStatus.BAD_REQUEST );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        }

        ApiResponse<Object> apiResponse = new ApiResponse<>("Successfully Registered!!", controllerResponse, HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}





