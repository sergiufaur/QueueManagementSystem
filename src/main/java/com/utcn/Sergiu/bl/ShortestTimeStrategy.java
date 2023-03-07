package com.utcn.Sergiu.bl;

import com.utcn.Sergiu.model.Server;
import com.utcn.Sergiu.model.Task;

import java.util.List;

public class ShortestTimeStrategy implements Strategy {

    @Override
    public void addTask(List<Server> servers, Task task) {
        for (Server server : servers
        ) {
            if (server.getWaitingPeriod() == getMinTime(servers))
                server.addTask(task);
        }
    }

    private int getMinTime(List<Server> servers) {
        int minTime = Integer.MAX_VALUE;

        for (Server server : servers
        ) {
            if (server.getWaitingPeriod() < minTime)
                minTime = server.getWaitingPeriod();
        }
        return minTime;
    }
}
