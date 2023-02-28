package com.learnSpringBootRESTapis.SpringBootRestApis.Users;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserJPARepository extends JpaRepository<User,Integer> {

    List<User> findByRole(String Role);
}
