package com.ms.user.services;

import org.springframework.stereotype.Service;

import com.ms.user.models.UserModel;
import com.ms.user.producers.UserProducer;
import com.ms.user.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserServices {

    
    final UserRepository userRepository;
    final UserProducer userProducer;
    
    public UserServices(UserRepository userRepository, UserProducer userProducer){
        this.userRepository = userRepository;
        this.userProducer = userProducer;
    }
    
    @Transactional
    public UserModel save(UserModel userModel){
        userModel = userRepository.save(userModel); 
        userProducer.publishMessageEmail(userModel); 
        return userModel;
    }

}
