package com.utcn.Sergiu.bl;

import com.utcn.Sergiu.model.Server;
import com.utcn.Sergiu.model.Task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Scheduler {
    private List<Server> servers;
    private Strategy strategy;
    private int numberOfServers;

    public Scheduler(int numberOfServers) {

        servers = new ArrayList<>();
        for (int i = 0; i < numberOfServers; i++)
            servers.add(new Server());
        for (Server server : servers
        ) {
            Thread t = new Thread(server);
            t.start();
        }

    }

    public void changePolicy(StrategyPolicy policy) {
        switch (policy) {
            case SHORTEST_TIME:
                strategy = new ShortestTimeStrategy();
                break;

            case SHORTEST_QUEUE:
                strategy = new ShortestQueueStrategy();
                break;
            default:
                break;
        }

    }

    public List<Server> getServers() {
        return servers;
    }

    public void deleteTasks(Task task, int time) {
        for (Iterator<Server> serverIterator = servers.iterator(); serverIterator.hasNext(); ) {
            Server server = serverIterator.next();
            server.deleteTask(task, time);
        }
    }

    public void dispatchTask(Task task) {
        strategy.addTask(servers, task);
    }

    public int getPeak() {
        int qCount = 0;
        for (Server server : servers
        ) {
            qCount = qCount + server.getTasks().size();
        }
        return qCount;
    }
}
