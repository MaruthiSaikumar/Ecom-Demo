package com.Doodleblue.ecomDemo.repository;

import com.Doodleblue.ecomDemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String userEmail);
    User save(User user);

}
