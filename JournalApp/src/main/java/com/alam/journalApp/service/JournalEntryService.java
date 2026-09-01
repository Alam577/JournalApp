package com.alam.journalApp.service;

import com.alam.journalApp.entity.JournalEntry;
import com.alam.journalApp.entity.UserEntry;
import com.alam.journalApp.repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService
{
    @Autowired
    private JournalEntryRepo journalEntryRepo;

    @Autowired
    private UserService userService;


//    @Transactional
    public void saveEntry(JournalEntry journalEntry,String userName){
        try {
            journalEntry.setDate(LocalDateTime.now());
            UserEntry user=userService.findByUserName(userName);
            JournalEntry saved=journalEntryRepo.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveEntry(user);
        }catch (Exception e)
        {
            System.out.println(e);
            throw new RuntimeException("An error occured while saving entry "+e);
        }



    }
    public void saveEntry(JournalEntry journalEntry)
    {
        journalEntryRepo.save(journalEntry);
    }

    public List<JournalEntry> getAll()
    {
        return journalEntryRepo.findAll();

    }

    public Optional<JournalEntry> findById(ObjectId find_id)
    {
        return journalEntryRepo.findById(find_id);
    }


    public void deleteById(ObjectId del_Id,String userName)
    {
        UserEntry delUser=userService.findByUserName(userName);
        delUser.getJournalEntries().removeIf(x->x.getId().equals(del_Id));
        userService.saveEntry(delUser);
         journalEntryRepo.deleteById(del_Id);
    }

}
//controller call-->service call-->repository