package com.cm.core.rest;

import com.cm.core.service.GroupService;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupRest {
    @Autowired
    GroupService groupService;

    @GetMapping("/") // todo: disable
    List<Document> getAllGroups() {
        return groupService.findAll();
    }

    @GetMapping("/{groupId}")
    Document findById(@PathVariable String groupId) {
        // return 404 if null
        return groupService.findById(new ObjectId(groupId));
    }

    @GetMapping("/by-teacher/{teacherId}")
    List<Document> findByTeacher(@PathVariable String teacherId) {
        return groupService.ofTeacher(teacherId);
    }

    @PostMapping("/")
    Document createGroup(@RequestBody Document groupDoc) {
        return groupService.create(groupDoc);
    }

    @DeleteMapping("/delete/{groupId}")
    void deleteGroup(@PathVariable String groupId) {
        groupService.deleteGroup(groupId);
    }

//    @PutMapping("/{groupId}/add-students/")
//    Group addStudent(@PathVariable String groupId, @RequestBody Set<String> studentIds) {
//        return groupService.addStudents(groupId, studentIds);
//    }
//
//    @PutMapping("/{groupId}/assign-teacher/")
//    Group assignTeacher(@PathVariable String groupId, @RequestBody DBObject teacher) {
//        return groupService.assignTeacher(groupId, teacher);
//    }
}
