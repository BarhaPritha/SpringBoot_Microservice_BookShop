package com.bjit.service;

import com.bjit.entity.UserEntity;
import com.bjit.model.UserRequestModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    ResponseEntity<Object> getUser(Long id);
    UserEntity getUserById(Long id);
    ResponseEntity<Iterable<UserEntity>> getAllUser();
    ResponseEntity<Object> createUser(UserRequestModel requestModel);
    ResponseEntity<Object> updateUser(Long id, UserRequestModel requestModel);
    ResponseEntity<Object> deleteUser(Long id);

}
