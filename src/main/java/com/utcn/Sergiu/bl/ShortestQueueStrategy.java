package com.utcn.Sergiu.bl;

import com.utcn.Sergiu.model.Server;
import com.utcn.Sergiu.model.Task;

import java.util.List;

public class ShortestQueueStrategy implements Strategy {

    @Override
    public void addTask(List<Server> servers, Task task) {

        for (Server server : servers
        ) {
            if (server.getTasks().size() == getMinSize(servers)) {
                server.addTask(task);
                break;
            }

        }
    }

    private int getMinSize(List<Server> servers) {
        int minTasks = Integer.MAX_VALUE;
        for (Server server : servers
        ) {
            if (server.getTasks().size() < minTasks)
                minTasks = server.getTasks().size();
        }
        return minTasks;
    }
}
