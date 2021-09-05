package com.cm.core.rest;

import com.cm.core.entity.Group;
import com.cm.core.service.GroupService;
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

    @PostMapping("/")
    Group createGroup(@RequestBody Group group) {
        return groupService.create(group);
    }

    @PutMapping("/{groupId}/add-students/")
    Group addStudent(@PathVariable String groupId, @RequestBody Set<String> studentIds) {
        return groupService.addStudents(groupId, studentIds);
    }

    @PutMapping("/{groupId}/assign-teacher/")
    Group assignTeacher(@PathVariable String groupId, @RequestBody Set<String> studentIds) {
        return groupService.addStudents(groupId, studentIds);
    }

    @DeleteMapping("/{id}/")
    void deleteGroup(@PathVariable String groupId) {
        groupService.deleteGroup(groupId);
    }
}
