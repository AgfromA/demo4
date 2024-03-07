package com.example.demo.security;


import com.example.demo.models.User;
import com.example.demo.services.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserSecurity implements UserDetailsService {

    private final UserService userService;

    public UserSecurity(UserService userService) {
        this.userService = userService;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", username));
        }
        return new User(user.getId(), user.getName(), user.getSurname(), user.getAge(),user.getUsername(), user.getPassword(), user.getRoles());
    }

}
