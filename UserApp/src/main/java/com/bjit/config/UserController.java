package com.bjit.config;

import com.bjit.entity.UserEntity;
import com.bjit.model.UserRequestModel;
import com.bjit.repository.UserRepository;
import com.bjit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    Logger logger = Logger.getLogger("UserController");

    @GetMapping("balance/{userId}-{pay}")
    public Long balance(@PathVariable String userId, @PathVariable String pay) {
        UserEntity user = userService.getUserById(Long.parseLong(userId));
        if(user==null){
            logger.info("No user found with this ID!");
            return null;
        }
        else {
            Long balance = user.getBalance();
            Long payment = Long.parseLong(pay);
            if (balance >= payment) {
                balance = balance - payment;
                user.setBalance(balance);
                UserEntity savedUser = userRepository.save(user);
            }
            logger.info("Username: " + user.getUsername() + ", Balance: " + balance);
            return balance;
        }
    }
    
    @GetMapping("/id/{userId}")
    public ResponseEntity<Object> getUser(@PathVariable String userId) {
        return userService.getUser(Long.parseLong(userId));
    }

    @PostMapping("/create")
    public ResponseEntity<Object> newUser(@RequestBody UserRequestModel user) {
        return userService.createUser(user);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable String id, @RequestBody UserRequestModel user) {
        return userService.updateUser(Long.parseLong(id), user);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable String id) {
        return userService.deleteUser(Long.parseLong(id));
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<UserEntity>> allUser() {
        return userService.getAllUser();
    }

}
