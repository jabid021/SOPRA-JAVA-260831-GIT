package fr.formation.config;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

// @Service
public class CustomUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return User
            .withUsername(username)
            .password("$2a$10$7LUTWk1B5nqyykF5vUPcF.QD4/8r77GU/BUgAzGUrGC5LY9Ypa7su")
            .roles(username.toUpperCase())
            .build()
        ;
    }
}
