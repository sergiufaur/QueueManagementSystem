package com.utcn.Sergiu.model;

import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {
    private final BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;
    private AtomicInteger averageWaitingTimePerQueue;
    private AtomicInteger averageServiceTimePerQueue;

    public Server() {
        tasks = new LinkedBlockingQueue<>();
        waitingPeriod = new AtomicInteger(0);
        averageWaitingTimePerQueue = new AtomicInteger(0);
        averageServiceTimePerQueue = new AtomicInteger(0);
    }

    public void addTask(Task task) {
        tasks.add(task);
        waitingPeriod.addAndGet(task.getServiceTime());
        averageWaitingTimePerQueue.addAndGet(waitingPeriod.intValue());
        averageServiceTimePerQueue.addAndGet(task.getServiceTime());
    }

    public void deleteTask(Task task, int time) {
        for (Iterator<Task> taskIterator = tasks.iterator(); taskIterator.hasNext(); ) {
            Task currentTask = taskIterator.next();
            if (currentTask.getServiceTime() == 0) {
                taskIterator.remove();

            }

        }
    }

    @Override
    public void run() {
        while (true) {
            if (!tasks.isEmpty()) {
                Task currentTask = tasks.peek();
                if (currentTask != null) {

                    try {
                        int sleepTime = currentTask.getServiceTime();
                        for (int i = 0; i < sleepTime; i++) {

                            Thread.sleep(1000);

                            currentTask.setServiceTime(currentTask.getServiceTime() - 1);

                            waitingPeriod.decrementAndGet();
                        }


                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }

    public BlockingQueue<Task> getTasks() {
        return tasks;
    }

    public int getWaitingPeriod() {
        return waitingPeriod.intValue();
    }

    public AtomicInteger getAverageWaitingTimePerQueue() {
        return averageWaitingTimePerQueue;
    }

    public AtomicInteger getAverageServiceTimePerQueue() {
        return averageServiceTimePerQueue;
    }


}
