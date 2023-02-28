package com.learnSpringBootRESTapis.SpringBootRestApis.Users;

import org.springframework.boot.CommandLineRunner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public class UserCommandLineRunner implements CommandLineRunner {
    UserJPARepository userJPARepository;

    public UserCommandLineRunner(UserJPARepository userJPARepository) {
        this.userJPARepository = userJPARepository;
    }

    @Override
    public void run(String... args) throws Exception {
        userJPARepository.save(new User(1,"Sneha","User"));
        userJPARepository.save(new User(2,"Sruthi","Admin"));
        userJPARepository.save(new User(3,"Sisira","User"));
        userJPARepository.save(new User(4,"Usha","Admin"));
      for (User user : userJPARepository.findByRole("User")) {
          System.out.println(user.toString());
        }


    }
}
