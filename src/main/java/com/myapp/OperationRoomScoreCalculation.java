package com.myapp;

import org.optaplanner.core.api.score.Score;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.impl.score.director.easy.EasyScoreCalculator;

import java.util.HashSet;
import java.util.List;

public class OperationRoomScoreCalculation implements EasyScoreCalculator<OperationRoomSchedule> {

    @Override
    public Score calculateScore(OperationRoomSchedule operationRoomSchedule) {
        int hardScore = 0;
        int softScore = 0;

        HashSet<String> occupiedRooms = new HashSet<>();
        for (Operation operation : operationRoomSchedule.getOperationList()) {
            if (operation.getPeriod() != null && operation.getRoom() != null) {
                String roomInUse = operation.getPeriod().toString() + ":" + operation.getRoom().toString();
                if (occupiedRooms.contains(roomInUse)) {
                    hardScore += -1;
                } else {
                    occupiedRooms.add(roomInUse);
                }
            } else {
                hardScore += -1;
            }
        }

        return HardSoftScore.valueOf(hardScore, softScore);
    }

}

//public class OperationRoomScoreCalculation implements EasyScoreCalculator<OperationRoomSchedule> {
//
//    @Override
//    public HardSoftScore calculateScore(OperationRoomSchedule operationRoomSchedule) {
//        List<Operation> operationList = OperationRoomSchedule.getOperationList();
//        int hardScore = 0;
//        for (Operation a : operationList) {
//            for (Operation b : operationList) {
//                if (a.getPeriod() != null && a.getPeriod().equals(b.getPeriod())
//                        && a.getId() < b.getId()) {
//
//                    if (a.getRoom() != null && a.getRoom().equals(b.getRoom())) {
//                        hardScore--;
//                    }
//
//                    if (a.getPatient().equals(b.getPatient())) {
//                        hardScore--;
//                    }
//
//                    if (a.getSurgerySet().equals(b.getSurgerySet())){
//                        hardScore--;
//                    }
//                }
//            }
//        }
//        int softScore = 0;
//        // Soft constraints are only implemented in the optaplanner-quickstarts code
//        return HardSoftScore.valueOf(hardScore, softScore);
//    }
//
//}