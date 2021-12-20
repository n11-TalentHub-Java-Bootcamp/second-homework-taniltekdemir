package com.taniltekdemir.springboot.controller;


import com.taniltekdemir.springboot.dto.UserDto;
import com.taniltekdemir.springboot.dto.UserSaveDto;
import com.taniltekdemir.springboot.entity.User;
import com.taniltekdemir.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public List<UserDto> findAllUserList() {
        return userService.findAll();
    }

    @GetMapping("/findByUsername")
    public UserDto findByUsername(@RequestParam String username) {
        return userService.findByUsername(username);
    }

    @GetMapping("/findByPhone")
    public UserDto findByPhone(@RequestParam String phone) {
        return userService.findByPhone(phone);
    }

    @PostMapping("/saveUser")
    public ResponseEntity<User> saveUser(@RequestBody UserDto userDto) {
       User user = userService.saveUser(userDto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/deleteUser")
    public void deleteUser(@RequestParam String username, @RequestParam String phone) {
        userService.deleteUser(username, phone);
    }

    @PostMapping("/updateUser")
    public ResponseEntity<?> updateUser(@RequestBody UserSaveDto userDto) {
        User user = userService.updateUser(userDto);
        return ResponseEntity.ok("Kullanıcı Güncellendi");
    }


}
