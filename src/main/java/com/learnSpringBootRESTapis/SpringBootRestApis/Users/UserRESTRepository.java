package com.learnSpringBootRESTapis.SpringBootRestApis.Users;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserRESTRepository extends PagingAndSortingRepository<User,Integer> {
    List<User> findByRole(String Role);
}
