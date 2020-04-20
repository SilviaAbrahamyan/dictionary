package com.dictionary.services;
import java.util.List;

import com.dictionary.models.Role;
import com.dictionary.models.User;
import com.dictionary.repositories.RoleRepository;
import com.dictionary.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



@Service("userService")
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findUserByUsername(String login) {
        return userRepository.findByUsername(login);
    }

    public List<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }



    public void saveUser(User user, boolean updateMode) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setConfirmPassword(new BCryptPasswordEncoder().encode(user.getPasswordConfirm()));
        int i = 0;
        if (i == 0){
        user.setEnable(++i);
        }
        else {
            user.setEnable(0);
        }
        User userReturned = userRepository.save(user);

        if (updateMode) {
            Role role = new Role();
            role.setUsername(userReturned.getUsername());
            role.setAuthority("ROLE_USER");

            roleRepository.save(role);
        }
    }

}
