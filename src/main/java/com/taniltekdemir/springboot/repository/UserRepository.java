package com.taniltekdemir.springboot.repository;

import com.taniltekdemir.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findFirstByName(String name);

    User findFirstByPhone(String phone);

    User findFirstByUsernameAndPhone(String username, String phone);

    User findFirstByUsername(String username);
}
