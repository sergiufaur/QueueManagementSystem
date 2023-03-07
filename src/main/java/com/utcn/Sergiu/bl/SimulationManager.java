package com.utcn.Sergiu.bl;

import com.utcn.Sergiu.GUI.SimulationFrame;
import com.utcn.Sergiu.model.Server;
import com.utcn.Sergiu.model.Task;


import java.util.*;

public class SimulationManager implements Runnable {
    private int numberOfServers;
    private int numberOfTasks;
    private int timeLimit;
    private int minServiceTime;
    private int maxServiceTime;
    private int minArrivalTime;
    private int maxArrivalTime;

    private StrategyPolicy strategyPolicy = StrategyPolicy.SHORTEST_QUEUE;
    private Scheduler scheduler;
    private List<Task> generatedTasks;
    private SimulationFrame frame;

    public SimulationManager(int numberOfServers, int numberOfTasks, int timeLimit, int minArrivalTime, int maxArrivalTime,
                             int minServiceTime, int maxServiceTime, SimulationFrame simulationFrame) {
        this.numberOfServers = numberOfServers;
        this.numberOfTasks = numberOfTasks;
        this.timeLimit = timeLimit;
        this.minArrivalTime = minArrivalTime;
        this.maxArrivalTime = maxArrivalTime;
        this.minServiceTime = minServiceTime;
        this.maxServiceTime = maxServiceTime;

        frame = simulationFrame;
        frame.show();

        scheduler = new Scheduler(numberOfServers);
        generatedTasks = new ArrayList<>();
        scheduler.changePolicy(strategyPolicy);
        generateNRandomTasks();


    }


    public void generateNRandomTasks() {

        Random rand = new Random();
        int arrivalT;
        int serviceT;
        int id;
        for (int i = 0; i < numberOfTasks; i++) {
            id = rand.nextInt(numberOfTasks) + 1;
            arrivalT = rand.nextInt(maxArrivalTime - minArrivalTime) + minArrivalTime;
            serviceT = rand.nextInt(maxServiceTime - minServiceTime) + minServiceTime;
            generatedTasks.add(new Task(id, arrivalT, serviceT));
        }

        Collections.sort(generatedTasks);
    }

    @Override
    public void run() {
        int currentTime = 0;
        float averageWaitingTime = 0;
        float averageServiceTime = 0;
        int peakNumberOfTasks = 0;
        int peakTime = 0;
        List<Task> tasksToBeDeleted = new ArrayList<>();
        while (currentTime < timeLimit) {
            for (Iterator<Task> iterator = generatedTasks.iterator(); iterator.hasNext(); ) {
                Task task = iterator.next();
                if (task.getArrivalTime() == currentTime) {
                    scheduler.dispatchTask(task);
                    tasksToBeDeleted.add(task);
                    iterator.remove();
                }
            }
            for (Task task : tasksToBeDeleted
            ) {
                scheduler.deleteTasks(task, currentTime);
            }
            if (scheduler.getPeak() > peakNumberOfTasks) {
                peakNumberOfTasks = scheduler.getPeak();
                peakTime = currentTime;
            }
            String string = display(currentTime).toString();
            frame.appendTextArea(string);
            System.out.println(string);
            currentTime++;
            if (currentTime == timeLimit) {
                for (Server server : scheduler.getServers()
                ) {
                    averageWaitingTime = averageWaitingTime + server.getAverageWaitingTimePerQueue().intValue();
                    averageServiceTime = averageServiceTime + server.getAverageServiceTimePerQueue().intValue();
                }
                averageWaitingTime = ((float) (averageWaitingTime / ((float) numberOfTasks)));
                averageServiceTime = ((float) (averageServiceTime / ((float) numberOfTasks)));
                System.out.println("\nAverage Waiting time: " + averageWaitingTime);
                frame.appendTextArea("\nAverage Waiting time: " + averageWaitingTime);
                System.out.println("\nAverage Service time: " + averageServiceTime);
                frame.appendTextArea("\nAverage Service time: " + averageServiceTime);
                System.out.println("\nPeak hour: " + peakTime);
                frame.appendTextArea("\nPeak hour: " + peakTime);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public StringBuilder display(int currTime) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nTime " + currTime + "\n");
        stringBuilder.append("Waiting clients: ");
        for (Task task : generatedTasks
        ) {
            stringBuilder.append(task.toString() + ",");
        }
        for (int i = 1; i <= numberOfServers; i++) {
            stringBuilder.append("\n" + "Queue " + i + ":");
            if (scheduler.getServers().get(i - 1).getTasks().isEmpty())
                stringBuilder.append("closed");
            else
                for (Task task : scheduler.getServers().get(i - 1).getTasks()
                ) {
                    stringBuilder.append(task.toString() + ",");
                }
        }
        return stringBuilder;
    }
}
