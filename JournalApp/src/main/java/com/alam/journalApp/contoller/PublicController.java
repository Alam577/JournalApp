package com.alam.journalApp.contoller;

import com.alam.journalApp.entity.JournalEntry;
import com.alam.journalApp.entity.UserEntry;
import com.alam.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController
{
    @Autowired
    private UserService userService;

    @GetMapping("/health-check")
    public  String healthCheck()
    {
        return  "Ok";
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserEntry newUser)
    {
        userService.saveEntry(newUser);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
