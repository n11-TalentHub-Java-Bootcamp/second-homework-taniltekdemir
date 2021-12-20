package com.taniltekdemir.springboot.controller;


import com.taniltekdemir.springboot.dto.UserDto;
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

    @GetMapping("/{name}")
    public UserDto findByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    @GetMapping("/{phone}")
    public UserDto findByPhone(@PathVariable String phone) {
        return userService.findByPhone(phone);
    }

    @PostMapping("")
    public ResponseEntity<User> saveUser(UserDto userDto) {
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

    @PostMapping("")
    public ResponseEntity<?> updateUser(UserDto userDto) {
        User user = userService.updateUser(userDto);
        return ResponseEntity.ok("Kullanıcı Güncellendi");
    }


}
