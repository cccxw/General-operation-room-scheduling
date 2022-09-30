package com.myapp;


import java.time.LocalDate;
import java.time.LocalTime;

public class Day {
    private LocalDate date;
    private int dayOfWeek;

    public Day() {
    }

    public Day(LocalDate date, int dayOfWeek) {
        this.date = date;
        this.dayOfWeek = dayOfWeek;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public boolean inWeekend(){
        return (getDayOfWeek()== 6 || getDayOfWeek()==7);
    }
}
