package com.myapp;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Set;

public class Period {

    private Day day;
    private TimeSlot startSlot;
    private TimeSlot endSlot;


    public Period() {
    }

    public Period(Day day, TimeSlot startSlot, TimeSlot endSlot) {
        this.day= day;
        this.startSlot = startSlot;
        this.endSlot = endSlot;
    }

    // ********************************
    // Getters and setters
    // ********************************

    public Day getDay() {
        return day;
    }


    public TimeSlot getStartSlot() {
        return startSlot;
    }

    public TimeSlot getEndSlot() {
        return endSlot;
    }

//    public LocalTime getStartTime()
//    {
//        Integer min=TimeslotSet.stream().min(Integer::compare).get();
//
//        LocalTime t= LocalTime.parse("8:00:00");
//        int minutes = 15;
//        t=t.plusMinutes(minutes*min);
//
//        return t;
//    }

    public LocalTime getStartTime()
    {
        return startSlot.getStartTime();
    }

//    public LocalTime getCloseTime()
//    {
//        Integer max=TimeslotSet.stream().max(Integer::compare).get();
//
//        LocalTime t= LocalTime.parse("8:00:00");
//        int minutes = 15;
//        t=t.plusMinutes(minutes*max);
//
//        return t;
//    }

    public LocalTime getEndTime()
    {
        return endSlot.getEndTime();
    }


    public boolean overlapsTime(Period other) {
        if(startSlot== null || other.getStartSlot() == null || endSlot==null || other.getEndSlot() == null)
        {
            return false;
        }
        if (this.day.getDate() != other.day.getDate()) {
            return false;
        }

//        Iterator<Integer> it = this.TimeslotSet.iterator();
//        while (it.hasNext()) {
//            Integer obj = it.next();
//            if (other.TimeslotSet.contains(obj))
//                return true;
//        }
//        return false;
        return (this.getStartTime().compareTo(other.getEndTime()) < 0
                && other.getStartTime().compareTo(this.getEndTime()) < 0);

    }

    public boolean inWeekend(){
        return day.inWeekend();
    }

}
