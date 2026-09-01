package com.alam.journalApp.service;

import com.alam.journalApp.entity.JournalEntry;
import com.alam.journalApp.entity.UserEntry;
import com.alam.journalApp.repository.JournalEntryRepo;
import com.alam.journalApp.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepo userRepo;

    private  static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();


    public void saveEntry(UserEntry saveEntry)
    {
        userRepo.save(saveEntry);
    }

    public void saveNewUser(UserEntry user)
    {
        user.setPass(passwordEncoder.encode(user.getPass()));
        userRepo.save(user);
    }

    public List<UserEntry> getAllUser() {return userRepo.findAll();}

    public Optional<UserEntry> findbyId(ObjectId userId)
    {
        return userRepo.findById(userId);
    }

    public void deletUserbyId(ObjectId userId)
    {
         userRepo.deleteById(userId);
    }
    public UserEntry findByUserName(String userName)
    {
        return userRepo.findByUserName(userName);

    }
}

//controller call-->service call-->repository