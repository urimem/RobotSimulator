package com.urim.robotsimulator;

import java.awt.*;

/**
 * Created by uri on 9/28/16.
 * RoboLogo - reminds me of the LOGO turtle
 */
public class RoboLogo implements Runnable {

    private boolean active = true;

    // Current status
    private double cx, cy;   // Current location
    private double nx, ny;  // next location
    private int direction;  // angel
    private double step;    // pixel/second

    private int[][] path;
    private String message;

    private Arena arena;

    public RoboLogo(Arena arena) {
        init();
        this.arena = arena;
    }

    private void init() {
        cx = 0; cy = 0;
        nx = 0; ny = 0;

        direction = 0;
        step = 5;
    }

    protected void move(int some_angle){
        cx = cx + (double) (Math.sin(Math.toRadians(some_angle)) * step);
        cy = cy + (double) (Math.cos(Math.toRadians(some_angle)) * step);
    }

    protected boolean checkCollision() {
        //Line2D.linesIntersect(cx,cy,nx,ny)
        if (this.arena.checkColision(cx, cy))
            return true;
        return false;
    }

    public void draw(Graphics2D g2d){
        //move(direction);
        g2d.drawLine((int) cx - 1, (int) cy - 1, (int) cx + 1, (int) cy + 1);
        System.out.println("RoboLogo draw");
    }

    @Override
    public void run() {
        while (active) {
            move(direction);
            System.out.println(cx+ "," +cy);

            // TODO: check for collision
            if (checkCollision()) this.active = false;

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("RoboLogo shutdown");
    }

    public void stop() {
        this.active = false;
        System.out.println("RoboLogo stop");

    }
}
