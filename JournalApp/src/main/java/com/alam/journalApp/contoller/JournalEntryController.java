//package com.alam.journalApp.contoller;
//
//import com.alam.journalApp.entity.JournalEntry;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//
//import java.util.List;
//
//
//@RestController
//@RequestMapping("/Journal")
//public class JournalEntryController {
//    private Map<Long, JournalEntry> journalEntries=new HashMap<>();
//
//    @GetMapping("/getAll")
//    public List<JournalEntry> getAll()
//    {
//        return new ArrayList<>(journalEntries.values());
//    }
//    @PostMapping
//    public boolean createEntry(@RequestBody JournalEntry myEntry)
//    {
//        journalEntries.put(myEntry.getId(),myEntry);
//        return true;
//
//    }
//    @GetMapping("id/{myId}")
//    public JournalEntry getJournalEnryById(@PathVariable long myId)
//    {
//        return journalEntries.get(myId);
//    }
//
//    @DeleteMapping("delete/{myId}")
//    public JournalEntry deleteJournalEntryById(@PathVariable long myId)
//    {
//        return journalEntries.remove(myId);
//    }
//@PutMapping("/id/{myId}")
//    public JournalEntry updateEntryById(@PathVariable long myId,@RequestBody JournalEntry myEntry)
//{
//    return journalEntries.put(myId,myEntry);
//}
//}
