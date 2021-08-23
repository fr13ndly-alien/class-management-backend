package com.cm.core.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Getter @Setter @ToString
@Document(collection = "group")
public class Group {

    @Indexed(unique = true)
    @Field("_id")
    ObjectId id;

    String teacher;
    Set<String> students;

    String name;
    String subject;
    Long price;
    Set<Schedule> schedule;

    public Group(String name, String subject, String teacher, Long price) {
        this.id = new ObjectId();
        this.name = name;
        this.subject = subject;
        this.teacher = teacher;
        this.price = price;
        students = Collections.emptySet();
    }

    public void addStudent(String id) {
        students.add(id);
    }

    public void addStudents(Set<String> ids) {
        students.addAll(ids);
    }

    public void addStudent(Set<String> ids) {
        students.addAll(ids);
    }
}
