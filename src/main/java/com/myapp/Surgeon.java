package com.myapp;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Set;

public class Surgeon {
    private int name;
    private Set<Department> departmentSet;
    private Set<Integer> workingWeekSet;


    public Surgeon(){

    }

    public Surgeon(int name,Set<Department> departmentSet,Set<Integer> workingWeekSet)
    {
        this.name=name;
        this.departmentSet=departmentSet;
        this.workingWeekSet=workingWeekSet;

    }

    public int getName(){return name;}

    public Set<Department> getDepartmentSet(){return departmentSet;}

    public Set<Integer> getWorkingWeekSet(){return workingWeekSet;}

}
