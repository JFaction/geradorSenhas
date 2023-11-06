package com.security.Senhas.controller;

import com.security.Senhas.domain.UserModel;
import com.security.Senhas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<UserModel> create(@Validated @RequestBody UserModel user){
        UserModel newUser = userService.create(user);
        return ResponseEntity.created(URI.create("/Users/" + newUser.getUserId())).body(newUser);
    }

}
