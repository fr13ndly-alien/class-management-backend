package com.cm.core.service;

import com.cm.core.entity.Group;
import com.cm.core.entity.UserRef;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GroupService {
    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    UserService userService;

    private final String collectionName = "group";
    private final Class<Document> groupEntity = Document.class;

    public Document create(Document groupDoc) {
        validateAndFixGroup(groupDoc);
        return mongoTemplate.save(groupDoc, collectionName);
    }

    private void validateAndFixGroup(Document groupDoc) {
        String[] requiredFields = {"teacher", "name", "subject"};
        for ( String field: requiredFields) {
            if (!groupDoc.containsKey(field))
                throw new IllegalStateException("Missing field: " + field);
        }

        String teacherStrId = groupDoc.getString("teacher");
        Document foundTeacher = userService.findById(new ObjectId(teacherStrId));
        groupDoc.replace(
            "teacher",
            new UserRef(
                foundTeacher.getObjectId("_id").toHexString(),
                foundTeacher.getString("name")
            )
        );

        groupDoc.append("createdDate", new Date());
        groupDoc.append("lastModified", new Date());
        groupDoc.append("_id", new ObjectId());
    }

    public List<Document> findAll() {
        return mongoTemplate.findAll(groupEntity, collectionName);
    }

    public Document findById(ObjectId id) {
        return mongoTemplate.findById(id, groupEntity, collectionName);
    }

    public List<Document> ofTeacher(String teacherId) {
        return mongoTemplate.find(
            queryById("teacher.userId", teacherId),
            groupEntity,
            collectionName
        );
    }

    public void deleteGroup(String id) {
        validateExistingGroup(id);
        mongoTemplate.remove(queryById("_id", new ObjectId(id)), collectionName);
    }

    private Query queryById(String path, Object id) {
        Query query = new Query();
        query.addCriteria(Criteria.where(path).is(id));
        return query;
    }

    private void validateExistingGroup(String groupId) {
        Document existingGroup = findById(new ObjectId(groupId));
        if (existingGroup == null)
            throw new IllegalStateException("Group id " + groupId + " not found");
    }

    public List<Group> findByStudentId(String studentId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("students").in(studentId));
        return mongoTemplate.find(query, Group.class);
    }
}
