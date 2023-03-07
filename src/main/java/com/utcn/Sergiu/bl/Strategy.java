package com.utcn.Sergiu.bl;

import com.utcn.Sergiu.model.Server;
import com.utcn.Sergiu.model.Task;

import java.util.List;

public interface Strategy {
    void addTask(List<Server> servers, Task task);
}
