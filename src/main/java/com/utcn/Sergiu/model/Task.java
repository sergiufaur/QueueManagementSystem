package com.utcn.Sergiu.model;

import java.util.Objects;

public class Task implements Comparable {
    private final int id;
    private final int arrivalTime;
    private int serviceTime;

    public Task(int id, int arrivalTime, int serviceTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }


    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    @Override
    public int compareTo(Object o) {
        Task t = (Task) o;
        return Integer.compare(this.arrivalTime, t.arrivalTime);
    }

    @Override
    public String toString() {
        return "( " + id + "," + arrivalTime + "," + serviceTime + ")";
    }
}
