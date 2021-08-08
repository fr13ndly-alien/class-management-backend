package com.cm.core.repository;

import com.cm.core.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, ObjectId> {

    @Query("{ email : ?0 }")
    Optional<User> findByEmail(String email);

}
