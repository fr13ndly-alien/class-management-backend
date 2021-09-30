package com.cm.core.entity;

import com.mongodb.DBObject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Schedule {
    private String day; // todo: Week day
    private int hh;
    private int mm;
    private int durationMin = 90;

    public Schedule(DBObject obj) {
        String[] requiredFields = { "day", "hh", "mm"};
        for (String field : requiredFields)
            if (!obj.containsField(field))
                throw new IllegalStateException("Parsing Schedule data error, missing field: " +  field);

        this.day = (String) obj.get("day");
        this.hh = (Integer) obj.get("hh");
        this.mm = (Integer) obj.get("mm");
    }
}
