package com.cm.core.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Getter
@Setter
@ToString

@Document(collection = "user")
public class User {

    @Indexed(unique = true)
    @Field("_id")
    @Id
    private ObjectId id;

    private String name;

    @Indexed(unique = true)
    private String email;

    private String password;

    private Date createdDate;

    private Date lastModified;

    private String role;

    public User(String name, String email, String password, String role) {
        this.id = new ObjectId();
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.createdDate = new Date();
        this.lastModified = createdDate;
    }

}
