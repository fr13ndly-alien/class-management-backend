package com.cm.core.service;

import com.cm.core.entity.Group;
import com.cm.core.repository.GroupRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class GroupService {
    @Autowired
    GroupRepository groupRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    public List<Group> getAll() { return groupRepository.findAll(); }

    public Optional<Group> findById(String id) {
        return findById(new ObjectId(id));
    }

    public Optional<Group> findById(ObjectId id) {
        return groupRepository.findById(id);
    }

    public List<Group> ofTeacher(String teacherId) {
        return groupRepository.findByTeacher(teacherId);
}

    public Group create(Group group) {
        return groupRepository.save(group);
    }

    public Group addStudent(String groupId, String studentId) {
        Set<String> studentIds = new HashSet<>();
        studentIds.add(studentId);

        // should be HTTP status
        return addStudents(groupId, studentIds);
    }

    public Group addStudents(String groupId, Set<String> studentIds) {
        Group group = getGroup(groupId);
        group.addStudents(studentIds);

        // should be HTTP status
        return mongoTemplate.save(group);
    }

    public void deleteGroup(String groupId) {
        Group group = getGroup(groupId);
        groupRepository.delete(group);
    }

    public Group assignTeacher(String groupId, String teacherId) {
        Group group = getGroup(groupId);
        group.setTeacher(teacherId);

        return mongoTemplate.save(group);
    }

    private Group getGroup(String groupId) {
        Optional<Group> existingGroup = groupRepository.findById(new ObjectId(groupId));
        if (!existingGroup.isPresent())
            throw new IllegalStateException("Group id " + groupId + " not found");

        return existingGroup.get();
    }

    public List<Group> findByStudentId(String studentId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("students").in(studentId));
        return mongoTemplate.find(query, Group.class);
    }
}
