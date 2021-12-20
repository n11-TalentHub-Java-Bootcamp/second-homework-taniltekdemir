package com.taniltekdemir.springboot.service;

import com.taniltekdemir.springboot.dto.UserDto;
import com.taniltekdemir.springboot.entity.User;
import com.taniltekdemir.springboot.exception.UserNotFoundException;
import com.taniltekdemir.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserDto> findAll() {
        List<User> userList = userRepository.findAll();

        List<UserDto> dtoList = convertUserListToUserDtoList(userList);

        return dtoList;
    }

    public UserDto findByName(String name) {
        User user = userRepository.findFirstByName(name);
        return convertUserToUserDto(user);
    }

    public UserDto findByUsername(String username) {
        User user = userRepository.findFirstByUsername(username);
        return convertUserToUserDto(user);
    }


    public UserDto findByPhone(String phone) {
        User user = userRepository.findFirstByPhone(phone);
        return convertUserToUserDto(user);
    }

    public User saveUser(UserDto dto) {
        User user = convertUserDtoToUser(dto);
        return userRepository.save(user);
    }

    public User updateUser(UserDto dto) {
        User user = convertUserDtoToUser(dto);
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

    private User convertUserDtoToUser(UserDto dto) {

        User user = new User();
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setPhone(dto.getPhone());
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());

        return user;
    }

    private UserDto convertUserToUserDto(User user) {

            UserDto dto = new UserDto();
            dto.setName(user.getName());
            dto.setSurname(user.getSurname());
            dto.setEmail(user.getEmail());
            dto.setPhone(user.getPhone());
            dto.setUsername(user.getUsername());
            return dto;
    }

    private List<UserDto> convertUserListToUserDtoList(List<User> userList) {

        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : userList) {
            UserDto dto = new UserDto();
            dto.setName(user.getName());
            dto.setSurname(user.getSurname());
            dto.setEmail(user.getEmail());
            dto.setPhone(user.getPhone());
            dto.setUsername(user.getUsername());
            userDtoList.add(dto);
        }
        return userDtoList;
    }


}
