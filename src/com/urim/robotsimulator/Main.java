package com.urim.robotsimulator;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        if (args.length > 0)
            System.out.println(args[0]);
        else
            System.out.println("No Args");


        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                SimulatorFrame sf = new SimulatorFrame();
                sf.setVisible(true);
            }
        });

    }
/*
    static int[][] points =
            {
                    {0,3},
                    {3,3},
                    {3,-3},
                    {-1,-3},
                    {-1,0},
                    {-3,0},
                    {-3,-3},
                    {-6,-3},
                    {-4,3}
            };
*/
}
