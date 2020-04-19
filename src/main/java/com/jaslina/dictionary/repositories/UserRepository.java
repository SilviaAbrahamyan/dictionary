package com.jaslina.dictionary.repositories;

import com.jaslina.dictionary.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by home on 4/9/2020.
 */
@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByUsername(String username);
    List<User> findByEmail(String email);


}
