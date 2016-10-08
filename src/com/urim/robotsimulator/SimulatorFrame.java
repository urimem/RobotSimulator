package com.urim.robotsimulator;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by uri on 9/30/16.
 */
public class SimulatorFrame extends JFrame {

    public SimulatorFrame() {
        initUI();
    }

    Arena arena = new Arena();

    private void initUI() {
        add(arena);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Timer timer = arena.getTimer();
                timer.stop();
                arena.stopRobots();
            }
        });

        setTitle("Arena testing v0.1");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setVisible(true);

    }

}