package com.myapp;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.drools.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@PlanningSolution
public class OperationRoomSchedule {
    Logger logger = LoggerFactory.getLogger("OperationRoomSchedule");

//    @ValueRangeProvider(id = "periodRange")
//    @ProblemFactCollectionProperty
    private List<Period> periodList;

//    @ValueRangeProvider(id = "roomRange")
//    @ProblemFactCollectionProperty
    private List<Room> roomList;

    private List<Surgeon>surgeonList;

    private List<TimeSlot>timeSlotList;

    private List<Day>dayList;

    private List<Department> departmentList;

    private List<Operation> operationList;

//    @ProblemFactCollectionProperty
//    private final List<OperationRoomAssignment> operationRoomAssignmentList;

//    @PlanningScore
    private HardSoftScore score;

    public OperationRoomSchedule(){
        roomList = new ArrayList<>();
        periodList = new ArrayList<>();
        dayList = new ArrayList<>();
        timeSlotList = new ArrayList<>();
        departmentList = new ArrayList<>();
        operationList = new ArrayList<>();
        surgeonList=new ArrayList<>();
//        operationRoomAssignmentList = new ArrayList<>();
    }

    public OperationRoomSchedule(List<Period> periodList, List<Room> roomList,List<Surgeon>surgeonList,
                                 List<TimeSlot>timeSlotList, List<Day>dayList,
                                 List<Department> departmentList, List<Operation> operationList
                                ) {
        this.periodList = periodList;
        this.roomList = roomList;
        this.surgeonList=surgeonList;
        this.dayList = dayList;
        this.timeSlotList = timeSlotList;
        this.departmentList = departmentList;
        this.operationList = operationList;
//        this.operationRoomAssignmentList=operationRoomAssignmentList;

    }

    // ********************************
    // Getters and setters
    // ********************************
    @ValueRangeProvider(id = "periodRange")
    @ProblemFactCollectionProperty
    public List<Period> getPeriodList() {
        return periodList;
    }

    public void setPeriodList(List<Period> periodList) {
        this.periodList = periodList;
    }

    @ValueRangeProvider(id = "roomRange")
    @ProblemFactCollectionProperty
    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

    @ValueRangeProvider(id = "surgeonRange")
    @ProblemFactCollectionProperty
    public List<Surgeon> getSurgeonList() {
        return surgeonList;
    }

    public void setSurgeonList(List<Surgeon> surgeonList){this.surgeonList=surgeonList;}

    @ProblemFactCollectionProperty
    public List<Day> getDayList() {
        return dayList;
    }

    public void setDayList(List<Day> dayList) {
        this.dayList = dayList;
    }

    @ProblemFactCollectionProperty
    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }

//    @PlanningEntityCollectionProperty()
//    public List<OperationRoomAssignment> getOperationRoomAssignmentList() {
//        return this.operationRoomAssignmentList;
//    }

    @PlanningEntityCollectionProperty
    public List<Operation> getOperationList() {
        return operationList;
    }

    public void setOperationList(List<Operation> operationList) {
        this.operationList = operationList;
    }

    @ProblemFactCollectionProperty
    public List<TimeSlot> getTimeSlotList() {
        return timeSlotList;
    }

    public void setTimeSlotList(List<TimeSlot> timeSlotList) {
        this.timeSlotList = timeSlotList;
    }

    @PlanningScore
    public HardSoftScore getScore() {
        return score;
    }

    public void setScore(HardSoftScore score) {
        this.score = score;
    }

}
