package com.utcn.Sergiu;

import com.utcn.Sergiu.GUI.SimulationFrame;
import com.utcn.Sergiu.bl.SimulationManager;

public class Controller {

    public Controller(SimulationFrame frame) {
        frame.show();
        frame.startListener(e -> {
            SimulationManager simulationManager = new SimulationManager(Integer.parseInt(frame.getNumberOfQueues()), Integer.parseInt(frame.getNumberOfClients())
                    , Integer.parseInt(frame.getSimulationTime()), Integer.parseInt(frame.getMinArrivalTime()), Integer.parseInt(frame.getMaxArrivalTime()),
                    Integer.parseInt(frame.getMinServiceTime()), Integer.parseInt(frame.getMaxServiceTime()), frame);
            Thread t = new Thread(simulationManager);
            t.start();
        });

    }
}
