package com.cm.core.rest;

import com.cm.core.entity.Group;
import com.cm.core.service.GroupService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/group")
public class GroupRest {
    @Autowired
    GroupService groupService;

    @GetMapping("/") // todo: disable
    List<Group> getAllGroups() {
        return groupService.getAll();
    }

    @GetMapping("/{teacherId}")
    List<Group> findByTeacher(@PathVariable String teacherId) {
        return groupService.ofTeacher(teacherId);
    }

    @PostMapping("/")
    Group createGroup(@RequestBody BasicDBObject groupObj) {
        System.out.println(">> Creating group: " + groupObj);
        return groupService.create(groupObj);
    }

    @PutMapping("/{groupId}/add-students/")
    Group addStudent(@PathVariable String groupId, @RequestBody Set<String> studentIds) {
        return groupService.addStudents(groupId, studentIds);
    }

    @PutMapping("/{groupId}/assign-teacher/")
    Group assignTeacher(@PathVariable String groupId, @RequestBody DBObject teacher) {
        return groupService.assignTeacher(groupId, teacher);
    }

    @DeleteMapping("/{id}/")
    void deleteGroup(@PathVariable String groupId) {
        groupService.deleteGroup(groupId);
    }
}
