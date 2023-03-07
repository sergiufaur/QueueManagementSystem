package com.utcn.Sergiu.GUI;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;

public class SimulationFrame {
    private JFrame frame;
    private JPanel panelWithLabels;

    private JPanel panel1WithLabels;
    private JPanel panel2WithLabels;
    private JPanel panel3WithLabels;
    private JPanel panelWithButton;
    private JPanel mainPanel;
    private JLabel clients;
    private JLabel queues;
    private JLabel time;
    private JLabel minArrival;
    private JLabel maxArrival;
    private JLabel minService;
    private JLabel maxService;
    private JTextField numberOfClients;
    private JTextField numberOfQueues;
    private JTextField simulationTime;
    private JTextField minArrivalTime;
    private JTextField maxArrivalTime;
    private JTextField maxServiceTime;
    private JTextField minServiceTime;
    private JTextArea results;
    private JButton start;
    private JScrollPane scrollPane;

    public SimulationFrame() {
        frame = new JFrame();
        panel1WithLabels = new JPanel();
        panel2WithLabels = new JPanel();
        panel3WithLabels = new JPanel();
        panelWithLabels = new JPanel();
        panelWithButton = new JPanel();

        mainPanel = new JPanel();
        clients = new JLabel("Number of clients: ");
        queues = new JLabel("Number of servers: ");
        time = new JLabel("Simulation Time: ");
        minArrival = new JLabel("Min. Arrival Time: ");
        minService = new JLabel("Min. Service Time: ");
        maxService = new JLabel("Max. Service Time: ");
        maxArrival = new JLabel("Max. Arrival Time: ");
        numberOfClients = new JTextField(10);
        numberOfQueues = new JTextField(10);
        simulationTime = new JTextField(10);
        minArrivalTime = new JTextField(10);
        maxArrivalTime = new JTextField(10);
        minServiceTime = new JTextField(10);
        maxServiceTime = new JTextField(10);
        results = new JTextArea();
        start = new JButton("Start simulation");
        scrollPane = new JScrollPane(results);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));


        panel1WithLabels.add(clients);
        panel1WithLabels.add(numberOfClients);
        panel1WithLabels.add(queues);
        panel1WithLabels.add(numberOfQueues);

        panel2WithLabels.add(time);
        panel2WithLabels.add(simulationTime);
        panel2WithLabels.add(minArrival);
        panel2WithLabels.add(minArrivalTime);


        panel3WithLabels.add(maxArrival);
        panel3WithLabels.add(maxArrivalTime);
        panel3WithLabels.add(minService);
        panel3WithLabels.add(minServiceTime);
        panel3WithLabels.add(maxService);
        panel3WithLabels.add(maxServiceTime);

        panelWithButton.add(start);

        panelWithLabels.add(panel1WithLabels);
        panelWithLabels.add(panel2WithLabels);
        panelWithLabels.add(panel3WithLabels);
        panelWithLabels.add(panelWithButton);


        mainPanel.add(panelWithLabels);
        mainPanel.add(scrollPane);

        frame.setSize(750, 600);
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void show() {
        frame.setVisible(true);
    }

    public void startListener(ActionListener actionListener) {
        start.addActionListener(actionListener);
    }

    public String getNumberOfClients() {
        return numberOfClients.getText();
    }

    public String getNumberOfQueues() {
        return numberOfQueues.getText();
    }

    public String getSimulationTime() {
        return simulationTime.getText();
    }

    public String getMinArrivalTime() {
        return minArrivalTime.getText();
    }

    public String getMaxArrivalTime() {
        return maxArrivalTime.getText();
    }

    public String getMaxServiceTime() {
        return maxServiceTime.getText();
    }

    public String getMinServiceTime() {
        return minServiceTime.getText();
    }

    public void appendTextArea(String s) {
        results.append(s);
    }
}
