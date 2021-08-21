package com.cm.core.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter @Setter @ToString
@Document(collection = "user")
public class Centre {
    @Field(name = "_id")
    private ObjectId id;
    private String name;
    private ObjectId admin;
    private List<ObjectId> teachers;

    // and further parameters
    public Centre(String name, String adminId, List<String> teacherIds) {
        this.id = new ObjectId();
        this.name = name;
        this.admin = new ObjectId(adminId);
        for (String teacherId: teacherIds)
            teachers.add(new ObjectId(teacherId));
    }
}
