package com.myapp;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.Set;

public class Room {
    private int name;
    private LocalTime openTime;
    private LocalTime closeTime;
    private Set<Department> departmentSet;

    public Room() {
    }

    public Room(int name, LocalTime openTime, LocalTime closeTime,Set<Department> departmentSet) {
        this.name = name;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.departmentSet=departmentSet;
    }

    @Override
    public String toString() {
        return name+ " " + openTime + " " + closeTime.toString();
    }

    // ********************************
    // Getters and setters
    // ********************************

    public int getName() {
        return name;
    }

    public LocalTime getStartTime() {
        return openTime;
    }

    public LocalTime getEndTime() {
        return closeTime;
    }
    public Set<Department> getDepartmentSet() {
        return departmentSet;
    }

    public boolean UnavailableForDepartment(Department department)
    {
        Iterator<Operation> it = department.getOperationSet().iterator();
        while (it.hasNext()) {
            Operation obj = it.next();
            if (!obj.getRoom().departmentSet.contains(department))
                return true;
        }
        return false;
    }

    public int BeforeOpeningTime(Period period)
    {
        if(openTime.compareTo(period.getStartTime())<0)
            return 0;
        return (int) Duration.between(period.getStartTime(),openTime).toMinutes() ;
    }

    public int OverCloseTime(Period period)
    {
        if(closeTime.compareTo(period.getEndTime()) > 0)
            return 0;
        return (int) Duration.between(closeTime,period.getEndTime()).toMinutes();
    }
}
