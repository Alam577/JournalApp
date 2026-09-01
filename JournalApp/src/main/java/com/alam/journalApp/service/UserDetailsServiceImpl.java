package com.alam.journalApp.service;

import com.alam.journalApp.entity.UserEntry;
import com.alam.journalApp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
public class UserDetailsServiceImpl  implements  UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public 	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntry user = userRepo.findByUserName(username);

        if (user != null) {
            return User.builder()
                    .username(user.getUserName())
                    .password(user.getPass())
                    .roles(user.getRoles().toArray(new String[0]))
                    .build();
        }
        throw new UsernameNotFoundException("Username not found: " + username);
    }
}