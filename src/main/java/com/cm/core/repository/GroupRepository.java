package com.cm.core.repository;

import com.cm.core.entity.Group;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends MongoRepository<Group, ObjectId> {

    @Query("{ name : ?0 }")
    public Optional<Group> findByName(String name);

    @Query("{ teacher : ?0}")
    public List<Group> findByTeacher(String teacherId);
}
