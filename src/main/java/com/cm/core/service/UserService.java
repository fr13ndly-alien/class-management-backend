package com.cm.core.service;

import com.cm.core.request_object.LoginRequest;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {
    @Autowired
    MongoTemplate mongoTemplate;

    private final String collectionName = "user";
    private final Class<Document> docClass = Document.class;

    public Document createUser(Document userDoc) {
        validateAndFixGroup(userDoc);

        return mongoTemplate.save(userDoc, collectionName);
    }

    public List<Document> findAll() {
        return mongoTemplate.findAll(docClass, collectionName);
    }

    public Document updateUser(ObjectId id, Document userToUpdate) {
        validateExistingUser(id);

        // todo: validate user info.
        Update update = new Update();
        update.set("name", userToUpdate.get("name"));
        update.set("email", userToUpdate.get("email"));
        update.set("lastModified", new Date());

        mongoTemplate.updateFirst(queryById("_id", id), update, collectionName);

        return findById(id);
    }

    public void delete(ObjectId id) {
        validateExistingUser(id);
        mongoTemplate.remove(queryById("_id", id), collectionName);
    }

    private void validateExistingUser(ObjectId id) {
        Document foundUser = findById(id);
        if (foundUser.isEmpty())
            throw new IllegalStateException("User id: " + id.toHexString() + " not found!");
    }

    public Document findById(ObjectId userId) {
        return mongoTemplate.findById(userId, docClass, collectionName);
    }

    public Document login(LoginRequest req) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(req.getEmail()));
        query.addCriteria(Criteria.where("password").is(req.getPassword()));

        Document foundUser = mongoTemplate.findOne(query, docClass, collectionName);
        if (foundUser.isEmpty())
            throw new IllegalStateException("Authentication failed!");
        foundUser.remove("password"); // todo: replaced by find with fields
        return foundUser;
    }

    private void validateAndFixGroup(Document userDoc) {
        String[] requiredFields = {"name", "email", "password"};
        for ( String field: requiredFields)
            if (!userDoc.containsKey(field))
                throw new IllegalStateException("Missing field: " + field);

        if (!userDoc.containsKey("role"))
            userDoc.append("role", "user");

        userDoc.append("createdDate", new Date());
        userDoc.append("lastModified", new Date());
    }

    // Todo: duplicated with GroupRepository
    private Query queryById(String path, ObjectId id) {
        Query query = new Query();
        query.addCriteria(Criteria.where(path).is(id));
        return query;
    }
}
