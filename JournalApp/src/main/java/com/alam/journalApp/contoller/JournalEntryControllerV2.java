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
import java.util.*;


@RestController
@RequestMapping("/Journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping("/{userName}")
    public ResponseEntity<?> getAll(@PathVariable String userName) {

        UserEntry user = userService.findByUserName(userName);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<JournalEntry> journalEntries = user.getJournalEntries();

        if (journalEntries == null || journalEntries.isEmpty()) {
            return new ResponseEntity<>(journalEntries, HttpStatus.OK);
        }

        return new ResponseEntity<>(journalEntries, HttpStatus.OK);
    }

    @PostMapping("/{userName}")
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry createUser, @PathVariable String userName)
    {
        try {
            journalEntryService.saveEntry(createUser,userName);
            return new ResponseEntity<>(createUser, HttpStatus.CREATED);
        }catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }


    @GetMapping("id/{getById}")
    public ResponseEntity<?> getJournalEntryById(@PathVariable ObjectId getById)
    {
        Optional<JournalEntry> journalEntryOptional = journalEntryService.findById(getById);
            if (journalEntryOptional.isPresent()) {
                return new ResponseEntity<>(journalEntryOptional.get(), HttpStatus.OK);
            }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("delete/{userName}/{del_Id}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId del_Id,@PathVariable String userName) {
        journalEntryService.deleteById(del_Id,userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

   }

   @PutMapping("update/{userName}/{update_Id}")
    public ResponseEntity<?> updateEntryById(
            @PathVariable ObjectId update_Id,
            @RequestBody JournalEntry newEntry,
            @PathVariable String userName) {

        JournalEntry oldEntry = journalEntryService.findById(update_Id).orElse(null);

        if (oldEntry == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (newEntry.getTitle() != null && !newEntry.getTitle().isEmpty()) {
            oldEntry.setTitle(newEntry.getTitle());
        }

        if (newEntry.getContent() != null && !newEntry.getContent().isEmpty()) {
            oldEntry.setContent(newEntry.getContent());
        }

        journalEntryService.saveEntry(oldEntry);

        return new ResponseEntity<>(oldEntry, HttpStatus.OK);
    }

}
