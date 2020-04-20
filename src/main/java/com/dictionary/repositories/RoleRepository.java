package com.dictionary.repositories;

import com.dictionary.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by home on 4/11/2020.
 */

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Integer> {

}