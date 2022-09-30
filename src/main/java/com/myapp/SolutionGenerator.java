package com.myapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class SolutionGenerator {

    public OperationRoomSchedule OperationRoomSchedule() {

        String RoomFileName = this.getClass().getClassLoader()
                .getResource("Room.csv").getFile();
        String DepartmentFileName = this.getClass().getClassLoader()
                .getResource("Department.csv").getFile();
        String DayFileName = this.getClass().getClassLoader()
                .getResource("Day.csv").getFile();
        String TimeSlotFileName = this.getClass().getClassLoader()
                .getResource("TimeSlot.csv").getFile();
        String OperationFileName = this.getClass().getClassLoader()
                .getResource("Operation.csv").getFile();
        String SurgeonFileName = this.getClass().getClassLoader()
                .getResource("Surgeon.csv").getFile();

        int id = 0;
        Hashtable<Integer, Operation> operationTable = new Hashtable<Integer, Operation>();
        Hashtable<String, Department> departmentTable = new Hashtable<String, Department>();


        try {
            File roomFile = new File(RoomFileName);
            File departmentFile = new File(DepartmentFileName);
            File dayFile = new File(DayFileName);
            File timeSlotFile = new File(TimeSlotFileName);
            File operationFile = new File(OperationFileName);
            File surgeonFile = new File(SurgeonFileName);

            BufferedReader dayBufferedReader;
            BufferedReader roomBufferedReader;
            BufferedReader operationBufferedReader;
            BufferedReader timeSlotBufferedReader;
            BufferedReader departmentBufferedReader;
            BufferedReader surgeonBufferedReader;

            departmentBufferedReader = new BufferedReader(new FileReader(departmentFile));
            roomBufferedReader = new BufferedReader(new FileReader(roomFile));
            timeSlotBufferedReader= new BufferedReader(new FileReader(timeSlotFile));
            dayBufferedReader= new BufferedReader(new FileReader(dayFile));
            operationBufferedReader= new BufferedReader(new FileReader(operationFile));
            surgeonBufferedReader= new BufferedReader(new FileReader(surgeonFile));

            int departmentCount = 0;
            while (departmentBufferedReader.readLine() != null) {
                departmentCount++;
            }
            departmentCount = departmentCount - 1;
            departmentBufferedReader.close();

            int surgeonCount = 0;
            while (surgeonBufferedReader.readLine() != null) {
                surgeonCount++;
            }
            surgeonCount = surgeonCount - 1;
            surgeonBufferedReader.close();

            int operationCount = 0;
            while (operationBufferedReader.readLine() != null) {
                operationCount++;
            }
            operationCount = operationCount - 1;
            operationBufferedReader.close();


            int timeSlotCount = 0;
            while (timeSlotBufferedReader.readLine() != null) {
                timeSlotCount++;
            }
            timeSlotCount = timeSlotCount - 1;
            timeSlotBufferedReader.close();

            int roomCount = 0;
            while (roomBufferedReader.readLine() != null) {
                roomCount++;
            }
            roomCount = roomCount - 1;
            roomBufferedReader.close();

            int dayCount = 0;
            while (dayBufferedReader.readLine() != null) {
                dayCount++;
            }
            dayCount = dayCount - 1;
            dayBufferedReader.close();

            departmentBufferedReader = new BufferedReader(new FileReader(departmentFile));
            roomBufferedReader = new BufferedReader(new FileReader(roomFile));
            timeSlotBufferedReader= new BufferedReader(new FileReader(timeSlotFile));
            dayBufferedReader= new BufferedReader(new FileReader(dayFile));
            operationBufferedReader= new BufferedReader(new FileReader(operationFile));
           surgeonBufferedReader= new BufferedReader(new FileReader(surgeonFile));

            dayBufferedReader.readLine();
            List<Day> dayList = new ArrayList<>();
            for (int i = 0; i < dayCount; i++) {
                String[] dayArray = dayBufferedReader.readLine()
                        .split(",");

                LocalDate date = LocalDate.parse(dayArray[0]);
                int dayOfWeek= Integer.parseInt(dayArray[1]);

                Day day= new Day(date,dayOfWeek);

                dayList.add(day);
            }


            operationBufferedReader.readLine();
            List<Operation> operationList = new ArrayList<>();
//            List<OperationRoomAssignment> operationRoomAssignmentList = new ArrayList<>();
            for (int i = 0; i < operationCount; i++) {
                String[] operationArray = operationBufferedReader.readLine()
                        .split(",");

                int operationId= Integer.parseInt(operationArray[0]);
                int predictedBlocks= Integer.parseInt(operationArray[1]);
                int patient= Integer.parseInt(operationArray[2]);

//                int surgeonNum= Integer.parseInt(operationArray[3]);

//                String surgeons= operationArray[3];
//                String[] surge=surgeons.split(";");
//                int[] surges=Arrays.asList(surge).stream().mapToInt(Integer::parseInt).toArray();
//                List<Integer>surges=new ArrayList<>();
//                for(int m=0;m<surgeons.length();m++)
//                {
//                    int n =Integer.parseInt(surgeons[m]);
//                    surges.add(n);
//                }
//                Set<Integer> surgeonSet= new HashSet<>();
//                for(int integer: surges)
//                {
//                    surgeonSet.add(integer);
//                }
//                System.out.println(surgeonSet);

                Operation operation = new Operation(operationId, predictedBlocks, patient);

                operationList.add(operation);
                operationTable.put(Integer.valueOf(operationArray[0]), operation);
                System.out.println(operationTable);
//                OperationRoomAssignment operationRoomAssignment = new OperationRoomAssignment(operation);
//                operationRoomAssignmentList.add(operationRoomAssignment);
            }

            departmentBufferedReader.readLine();
            List<Department> departmentList = new ArrayList<>();
            for (int i = 0; i < departmentCount; i++) {
                String[] departmentArray = departmentBufferedReader.readLine()
                        .split(",");

                String name= departmentArray[0];

//                Set<Operation> operationSet= (Set<Operation>) operationTable.get(departmentArray[1]);
                String[] op=departmentArray[1].split(";");
                int[] ops=Arrays.asList(op).stream().mapToInt(Integer::parseInt).toArray();
                Set<Operation> operationSet = new HashSet<Operation>();
                for(int integer: ops)
                {
                    operationSet.add(operationTable.get(integer));
                }
//                System.out.println(operationSet);
                Department department = new Department(name, operationSet);

                departmentList.add(department);
                departmentTable.put(departmentArray[0],department);
            }

            timeSlotBufferedReader.readLine();
            List<TimeSlot> timeSlotList = new ArrayList<>();
            for (int i = 0; i < timeSlotCount; i++) {
                String[] timeSlotArray = timeSlotBufferedReader.readLine()
                        .split(",");

                LocalTime startTime = LocalTime.parse(timeSlotArray[0]);
                LocalTime endTime = LocalTime.parse(timeSlotArray[1]);
                int timeSlotSet= Integer.parseInt(timeSlotArray[2]);

                TimeSlot timeSlot = new TimeSlot(startTime, endTime, timeSlotSet);

                timeSlotList.add(timeSlot);
            }


            surgeonBufferedReader.readLine();
            List<Surgeon> surgeonList = new ArrayList<>();
            for (int i = 0; i < surgeonCount; i++) {
                String[] surgeonArray = surgeonBufferedReader.readLine()
                        .split(",");

                int name = Integer.parseInt(surgeonArray[0]);

                Set<Department> departmentSet1 = new HashSet<Department>();
                String[] dpm=surgeonArray[1].split(";");
                for(String s: dpm)
                {
                    departmentSet1.add(departmentTable.get(s));
                }
//                System.out.println(departmentSet1);

                String weeks= surgeonArray[2];
                String[] week=weeks.split(";");
                int[] workingWeek=Arrays.asList(week).stream().mapToInt(Integer::parseInt).toArray();

                Set<Integer> workingWeekSet= new HashSet<>();
                for(int integer: workingWeek)
                {
                    workingWeekSet.add(integer);
                }

                Surgeon surgeon= new Surgeon(name,departmentSet1,workingWeekSet);

                surgeonList.add(surgeon);
            }


            roomBufferedReader.readLine();
            List<Room> roomList = new ArrayList<>();
            for (int i = 0; i < roomCount; i++) {
                String[] roomArray = roomBufferedReader.readLine()
                        .split(",");

                int roomId = Integer.parseInt(roomArray[0]);
                LocalTime openTime = LocalTime.parse(roomArray[1]);
                LocalTime closeTime = LocalTime.parse(roomArray[2]);
                Set<Department> departmentSet = new HashSet<Department>();
                String[] dpm=roomArray[3].split(";");
                for(String s: dpm)
                {
                    departmentSet.add(departmentTable.get(s));
                }
//               System.out.println(departmentSet);
//                departmentSet.add(departmentTable.get(roomArray[3]));

                Room room = new Room(roomId, openTime, closeTime, departmentSet);

                roomList.add(room);
            }

            List<Period> periodList = new ArrayList<>();
//            int timeSlotSize=timeSlotCount*(timeSlotCount-1)/2;
            for (Day day: dayList) {
                for (TimeSlot timeslot1 : timeSlotList) {
                    for (TimeSlot timeslot2 : timeSlotList) {
                        if(timeslot1.tag< timeslot2.tag){

                            Period period = new Period(day, timeslot1, timeslot2);
                            periodList.add(period);

                        }
                    }
                }
            }


            return new OperationRoomSchedule(periodList, roomList,surgeonList,
                    timeSlotList, dayList,departmentList,operationList);

        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
