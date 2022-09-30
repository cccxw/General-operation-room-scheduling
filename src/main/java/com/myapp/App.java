package com.myapp;


import java.util.Date;

import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.api.solver.event.BestSolutionChangedEvent;
import org.optaplanner.core.api.solver.event.SolverEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class App implements SolverEventListener
{
    protected final transient Logger logger = LoggerFactory.getLogger(this
            .getClass());

    private long startDateTime;

    public static void main(final String[] args) {

        App app = new App();

        app.execute();
    }

    private void execute() {
        // Build the Solver
        SolverFactory solverFactory = SolverFactory
                .createFromXmlResource("OperationRoomScheduleSolverConfigDrools.xml");
        Solver<OperationRoomSchedule> solver = solverFactory.buildSolver();

        OperationRoomSchedule unsolvedOperationRoomSchedule = new SolutionGenerator()
                .OperationRoomSchedule();


        solver.addEventListener(this);

        this.startDateTime = new Date().getTime();


        solver.solve(unsolvedOperationRoomSchedule);
        OperationRoomSchedule solvedSolution = solver.getBestSolution();

        //
        this.printScore(solvedSolution);

        // Display the result
        String Result = toDisplayJobString((OperationRoomSchedule) solvedSolution);

        System.out.println(Result);
    }

    private String toDisplayJobString(final OperationRoomSchedule OperationRoomSchedule) {
        StringBuilder displayString = new StringBuilder();

        for (Operation operation : OperationRoomSchedule.getOperationList()){
//            Operation operation = operationRoomAssignment.getOperation();
            displayString
                    .append(operation.getPeriod().getDay().getDate())
                    .append("\t")
                    .append(operation.getPeriod().getDay().getDayOfWeek())
                    .append("\t")
                    .append(operation.getPeriod().getStartTime())
                    .append("\t")
                    .append(operation.getPeriod().getEndTime())
                    .append("\t")
                    .append(operation.getRoom().getName())
                    .append("\t")
                    .append(operation.getSurgeon().getName())
                    .append("\n");

        }


        return displayString.toString();
    }


    @Override
    public void bestSolutionChanged(BestSolutionChangedEvent event) {
        this.printScore(event.getNewBestSolution());
    }

    private void printScore(Object newBestSolution) {
    }

    public void printTest(String test) {
        {
            System.out.println(test);
        }
    }
}
