package com.taniltekdemir.springboot.service;

import com.taniltekdemir.springboot.dto.UserDto;
import com.taniltekdemir.springboot.dto.UserSaveDto;
import com.taniltekdemir.springboot.entity.User;
import com.taniltekdemir.springboot.exception.UserNotFoundException;
import com.taniltekdemir.springboot.mapper.UserMapper;
import com.taniltekdemir.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserDto> findAll() {
        List<User> userList = userRepository.findAll();

        return userList.stream().map(UserMapper.INSTANCE::convertUserToUserDto).collect(Collectors.toList());
    }

    public UserDto findByName(String name) {
        User user = userRepository.findFirstByName(name);
        return UserMapper.INSTANCE.convertUserToUserDto(user);
    }

    public UserDto findByUsername(String username) {
        User user = userRepository.findFirstByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("Belirtilen " + username + " isminde kullanıcı bulunamadı");
        }
        return UserMapper.INSTANCE.convertUserToUserDto(user);
    }


    public UserDto findByPhone(String phone) {
        User user = userRepository.findFirstByPhone(phone);
        if (user == null) {
            throw new UserNotFoundException("Belirtilen " + phone + " telefon numarasıyla kayıtlı kullanıcı bulunamadı");
        }
        return UserMapper.INSTANCE.convertUserToUserDto(user);
    }

    public User saveUser(UserDto dto) {
        User user = UserMapper.INSTANCE.convertUserDtoToUser(dto);
        return userRepository.save(user);
    }

    public User updateUser(UserSaveDto dto) {
        User user = UserMapper.INSTANCE.convertUserSaveDtoToUser(dto);
        return userRepository.save(user);
    }

    public void deleteUser(String username, String phone) {
        User user = userRepository.findFirstByUsernameAndPhone(username, phone);

        if (user != null) {
            userRepository.delete(user);
        } else {
            throw new UserNotFoundException(username + "kullanici adi ile " + phone + "telefon bilgileri uyuşmamaktadır.");
        }
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }


}
