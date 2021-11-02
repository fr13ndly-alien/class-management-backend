package com.cm.core.entity;

import org.bson.Document;

public class UserRef extends Document {

    public UserRef(String id, String name) {
        this.put("userId", id);
        this.put("name", name);
    }
}
