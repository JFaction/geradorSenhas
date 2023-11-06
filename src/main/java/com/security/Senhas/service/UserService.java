package com.security.Senhas.service;

import com.security.Senhas.domain.UserModel;
import com.security.Senhas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    public UserModel create(UserModel user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserModel newUser = userRepository.save(user);
        return userRepository.save(newUser);
    }

}
