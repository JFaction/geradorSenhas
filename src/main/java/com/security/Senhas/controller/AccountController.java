package com.security.Senhas.controller;

import com.security.Senhas.domain.AccountModel;
import com.security.Senhas.dto.AccountDTO;
import com.security.Senhas.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("accounts")
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountModel> create(@Validated @RequestBody AccountDTO accountDTO){
        AccountModel newAccount = accountService.create(accountDTO);
        return ResponseEntity.created(URI.create("/account/" + newAccount.getId())).body(newAccount);
    }



}