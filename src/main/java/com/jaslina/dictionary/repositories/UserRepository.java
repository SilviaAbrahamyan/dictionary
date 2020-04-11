package com.jaslina.dictionary.repositories;

import com.jaslina.dictionary.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by home on 4/9/2020.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByUsername(String username);
    List<User> findByEmail(String email);


}
