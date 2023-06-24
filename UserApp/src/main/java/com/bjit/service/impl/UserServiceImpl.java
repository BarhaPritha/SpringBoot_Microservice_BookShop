package com.bjit.service.impl;

import com.bjit.entity.UserEntity;
import com.bjit.model.UserRequestModel;
import com.bjit.repository.UserRepository;
import com.bjit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public ResponseEntity<Object> getUser(Long id) {
        Optional<UserEntity> user;
        user = userRepository.findById(id);
        if (user.isEmpty()){
            return new ResponseEntity<>("No user found with this id!", HttpStatus.NOT_FOUND);
        }
        else {
            UserEntity userEntity = UserEntity.builder()
                    .customerId(user.get().getCustomerId())
                    .username(user.get().getUsername())
                    .email(user.get().getEmail())
                    .balance(user.get().getBalance())
                    .build();
            return new ResponseEntity<>(userEntity, HttpStatus.FOUND);
        }
    }

    @Override
    public UserEntity getUserById(Long id) {
        Optional<UserEntity> user;
        user = userRepository.findById(id);
        return user.map(userEntity -> UserEntity.builder()
            .customerId(userEntity.getCustomerId())
            .username(userEntity.getUsername())
            .email(userEntity.getEmail())
            .balance(userEntity.getBalance())
            .build()).orElse(null);
    }

    @Override
    public ResponseEntity<Iterable<UserEntity>> getAllUser() {
        Iterable<UserEntity> users = userRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> createUser(UserRequestModel requestModel) {
        UserEntity user = UserEntity.builder()
                .username(requestModel.getUsername())
                .email(requestModel.getEmail())
                .balance(requestModel.getBalance())
                .build();

        UserEntity savedUser = userRepository.save(user);

        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> updateUser(Long id, UserRequestModel requestModel) {
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isEmpty()){
            return new ResponseEntity<>("No user found with this id!", HttpStatus.NOT_FOUND);
        }
        else {
            user.get().setUsername(requestModel.getUsername());
            user.get().setBalance(requestModel.getBalance());
            user.get().setEmail(requestModel.getEmail());

            UserEntity savedUser = userRepository.save(user.get());

            return new ResponseEntity<>(savedUser, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<Object> deleteUser(Long id){
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isEmpty()){
            return new ResponseEntity<>("No user found with this id!", HttpStatus.NOT_FOUND);
        }
        else{
            userRepository.deleteById(id);
            return new ResponseEntity<>("User Deleted!", HttpStatus.OK);
        }
    }

}
