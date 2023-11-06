package com.security.Senhas.service;

import com.security.Senhas.domain.AccountModel;
import com.security.Senhas.domain.UserModel;
import com.security.Senhas.dto.AccountDTO;
import com.security.Senhas.repository.AccountRepository;
import com.security.Senhas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public AccountModel create(AccountDTO accountDTO){
        AccountModel accountModel = new AccountModel(accountDTO);
        String randomPassword = generateRandomString();
        accountModel.setPassword(randomPassword);

        GetUserAuthentication(accountModel);
        return accountRepository.save(accountModel);
    }

    public String generateRandomString() {
        UUID uuid = UUID.randomUUID();
        String randomString = uuid.toString().substring(0, 28);
        return randomString;
    }

    public void GetUserAuthentication(AccountModel accountModel){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserDetails){
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            System.out.println("Usuário autenticado -> " + userDetails.getUsername());

            Optional<UserModel> user =  userRepository.findByUsername(userDetails.getUsername());

            if(user.isPresent()){
                var userModel = user.get();
                System.out.println("ID do usuario autenticado: " + userModel.userId);
                accountModel.setIdUser(userModel.userId);
            }

        }else{
            System.out.println("Erro ao autenticar usuario");
        }
    }


}
