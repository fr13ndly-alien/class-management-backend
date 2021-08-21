package com.cm.core.repository;

import com.cm.core.entity.Centre;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface CentreRepository extends MongoRepository<Centre, ObjectId> {

    @Query("{ name : ?0 }")
    Optional<Centre> findByName(String name);
}
