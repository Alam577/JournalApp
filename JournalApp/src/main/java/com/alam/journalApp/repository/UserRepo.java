package com.alam.journalApp.repository;

import com.alam.journalApp.entity.UserEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<UserEntry, ObjectId> {
    UserEntry findByUserName(String username);

}