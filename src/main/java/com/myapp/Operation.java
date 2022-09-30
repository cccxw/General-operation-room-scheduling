package com.myapp;

import org.optaplanner.core.api.domain.variable.PlanningVariable;
import org.optaplanner.core.api.domain.entity.PlanningEntity;

import java.util.Iterator;

@PlanningEntity(difficultyComparatorClass = OperationDifficultyComparator.class)
//@PlanningEntity
public class Operation {
    private int id;
    private int predictedBlock;
    private int patient;
    private Surgeon surgeon;

//    @PlanningVariable(valueRangeProviderRefs = "PeriodRange")
    private Period period;

//    @PlanningVariable(valueRangeProviderRefs = "roomRange")
    private Room room;

    public Operation() {
    }

    public Operation(int id, int predictBlock, int patient ) {
        this.id = id;
        this.predictedBlock = predictBlock;
        this.patient = patient;

    }

    // ********************************
    // Getters and setters
    // ********************************

    public int getId() {
        return id;
    }

    public int getPredictedBlock() {
        return predictedBlock;
    }

    public int getPatient() {
        return patient;
    }

    @PlanningVariable(valueRangeProviderRefs = {"surgeonRange"})
    public Surgeon getSurgeon() {
        return surgeon;
    }

    public void setSurgeon(Surgeon surgeon) {
        this.surgeon = surgeon;
    }

    @PlanningVariable(valueRangeProviderRefs = {"periodRange"})
    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    @PlanningVariable(valueRangeProviderRefs = {"roomRange"})
    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public boolean overlapsTime(Operation other) {
        return period != null && other.getPeriod() != null && period.overlapsTime(other.getPeriod());
    }

    public boolean overlapsSurgeon(Operation other) {
        if (this.surgeon.getName()==other.surgeon.getName()){
            return true;
        }
        return false;
    }

    public int BeforeOpeningTime() {
        if (period == null || room == null) {
            return 0;
        }
        return room.BeforeOpeningTime(period);
    }

    public int OverCloseTime() {
        if (period == null || room == null) {
            return 0;
        }
        return room.OverCloseTime(period);
    }

    public boolean inWeekend()
    {
        return  period.inWeekend();
//        return (period.getDayOfWeek()== 6 || period.getDayOfWeek()==7);
    }

    public boolean LessThanPredictedBlocks()
    {
        return ((period.getEndSlot().tag - period.getStartSlot().tag + 1) < predictedBlock);
    }

    public boolean SameRoom(Room room)
    {
        return(this.room.getName()== room.getName());
    }

    public boolean roomUnavailable()
    {
//        System.out.println("roomUnavailable");
        Iterator<Department> it = this.room.getDepartmentSet().iterator();
        while (it.hasNext()) {
            Department obj = it.next();
            Iterator<Operation> itt = obj.getOperationSet().iterator();
            while (itt.hasNext())
            {
                Operation ope= itt.next();
                if (ope.getId()==id)
                    return false;
            }
        }
        return true;
    }

    public boolean surgeonUnavailable()
    {
        Iterator<Department> it = this.surgeon.getDepartmentSet().iterator();
        while (it.hasNext()) {
            Department obj = it.next();
            Iterator<Operation> itt = obj.getOperationSet().iterator();
            while (itt.hasNext())
            {
                Operation ope= itt.next();
                if (ope.getId()==id)
                    return false;
            }
        }
        return true;
    }

    public boolean absentDay()
    {
        Iterator<Integer> it = this.surgeon.getWorkingWeekSet().iterator();
        while (it.hasNext()) {
            Integer obj= it.next();
            if(this.getPeriod().getDay().getDayOfWeek()==obj)
                return false;
        }

        return true;
    }
}
