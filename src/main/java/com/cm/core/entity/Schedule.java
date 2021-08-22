package com.cm.core.entity;

import com.cm.core.enumeration.Weekday;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Schedule {
    private Weekday day;
    private int hh;
    private int mm;
    private int minDuration = 90;

    public Schedule(Weekday weekday, int hh, int mm) {
        this.day = weekday;
        this.hh = hh;
        this.mm = mm;
    }
}
