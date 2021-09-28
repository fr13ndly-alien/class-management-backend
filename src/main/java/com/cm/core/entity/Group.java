package com.cm.core.entity;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.*;

@Getter @Setter @ToString
@Document(collection = "group")
public class Group {

    @Indexed(unique = true)
    @Field("_id")
    ObjectId id;

    DBObject teacher;
    Set<String> students;

    String name;
    String subject;
    Integer price;
    ArrayList<Schedule> schedule;


    public Group(DBObject obj) {
        validateRequiredFields(obj);
        this.name = (String) obj.get("name");
        this.subject = (String) obj.get("subject");
        this.price = (Integer) obj.get("price");

        if (obj.containsField("student"))
            setStudents((Set<String>) obj.get("student"));

        if (obj.containsField("teacher")) {
            HashMap mapObj = (HashMap) obj.get("teacher");
            setTeacher(new BasicDBObject(mapObj));
        }

        this.schedule = new ArrayList<>();
        if (obj.containsField("schedule")) {
            // todo: BasicDbList
            ArrayList<LinkedHashMap> listSchedule = (ArrayList<LinkedHashMap>) obj.get("schedule");
            for (LinkedHashMap mapObj : listSchedule) {
                Schedule s = new Schedule(new BasicDBObject(mapObj));
                schedule.add(s);
            }
        }
    }

    private void validateRequiredFields(DBObject obj) {
        String[] requiredFields = { "name", "subject", "price" };
        for (String field : requiredFields) {
            if (!obj.containsField(field))
                throw new IllegalStateException("Missing field: " + field);
        }
    }

    public void setTeacher(DBObject teacherInfo) throws IllegalStateException {
        String id = (String) teacherInfo.get("id");
        String name = (String) teacherInfo.get("name");

        BasicDBObject teacher = new BasicDBObject();
        teacher.put("id", new ObjectId(id));
        teacher.put("name", name);

        this.teacher = teacher;
    }

    public void addStudents(Set<String> ids) {
        students.addAll(ids);
    }

}
