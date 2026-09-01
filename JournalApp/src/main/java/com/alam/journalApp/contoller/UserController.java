package com.alam.journalApp.contoller;

import com.alam.journalApp.entity.JournalEntry;
import com.alam.journalApp.entity.UserEntry;
import com.alam.journalApp.service.JournalEntryService;
import com.alam.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping
     public List<UserEntry> getAllRecord()
    {
        return userService.getAllUser();
    }

    @GetMapping("/{userName}")
    public UserEntry getAllRecord(@PathVariable String userName)
    {
        return userService.findByUserName(userName);
    }




    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(
            @RequestBody UserEntry editUser,
            @PathVariable String userName) {

        UserEntry oldUserDb = userService.findByUserName(userName);

        if (oldUserDb == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        oldUserDb.setUserName(editUser.getUserName());
        oldUserDb.setPass(editUser.getPass());

        userService.saveEntry(oldUserDb);

        return new ResponseEntity<>(oldUserDb, HttpStatus.OK);
    }


    @DeleteMapping("/{userName}")
    public ResponseEntity<?> deleteUser(@PathVariable String userName) {

        UserEntry user = userService.findByUserName(userName);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        userService.deletUserbyId(user.getId());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}
