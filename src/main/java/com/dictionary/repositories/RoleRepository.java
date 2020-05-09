package com.dictionary.repositories;

import com.dictionary.models.Role;
import com.dictionary.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by home on 4/11/2020.
 */

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Integer> {

  //  List<User> findByUsername(String username);
    Role findByUsername(String username);
}