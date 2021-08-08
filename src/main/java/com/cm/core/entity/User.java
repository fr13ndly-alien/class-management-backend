package com.cm.core.entity;

import com.mongodb.DBObject;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Employee")
public class User {
    String documentType;
    DBObject object;

}
