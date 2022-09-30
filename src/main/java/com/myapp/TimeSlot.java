package com.myapp;

import java.time.LocalTime;
import java.util.Set;

public class TimeSlot {
    private LocalTime startTime;
    private LocalTime endTime;
    int tag;

    public TimeSlot() {
    }

    public TimeSlot(LocalTime startTime, LocalTime endTime, int tag) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.tag=tag;
    }

    public int getTag() {
        return tag;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }
}
