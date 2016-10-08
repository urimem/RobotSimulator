package com.urim.robotsimulator;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.AffineTransform;
import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by uri on 9/28/16.
 */
public class Arena extends JPanel implements ActionListener {

    private final int DELAY = 150; //msec
    private javax.swing.Timer timer;

    private Line2D[] arenaLines;
    private RoboLogo rl = new RoboLogo(this);

    public Arena() {
        initTimer();
        init();

        Thread robotThread = new Thread(rl);
        robotThread.start();
    }

    private void initTimer() {

        timer = new Timer(DELAY, this);
        timer.start();
    }

    public Timer getTimer() {
        return timer;
    }

    public void init() {
        // Fill arena line array
        arenaLines = new Line2D.Double[arenaPoints.length];
        Point2D.Double prevPoint = arenaPoints[0];

        for (int i=1; i < arenaPoints.length; i++) {
            arenaLines[i-1] = new Line2D.Double(prevPoint, arenaPoints[i]);
            prevPoint = arenaPoints[i];
        }
        arenaLines[arenaPoints.length-1] = new Line2D.Double(prevPoint, arenaPoints[0]);
    }

    public boolean checkColision(double x, double y) {
        for (Line2D arenaLine : arenaLines) if (arenaLine.contains(x, y)) return true;
        return false;
    }

    public void stopRobots() {
        rl.stop();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;

        // Scale support
        int MaxX = this.getWidth();
        int MinX = 0;
        int MaxY = -1*this.getHeight(); // flipping y axis
        int MinY = 0;

        AffineTransform transform = new AffineTransform();
        double scaleX = (double)this.getWidth() / (MaxX - MinX);
        double scaleY = (double)this.getHeight() / (MaxY - MinY);
        transform.setToScale(scaleX, scaleY);   // 1,-1
        double deltaX = (MaxX - MinX) / 2;
        double deltaY = (MaxY - MinY) / 2;
        transform.translate(deltaX, deltaY);    //width/2,height/-2

        g2d.setTransform(transform);


        for (int i = 0; i < arenaLines.length; i++) {
            g2d.draw(arenaLines[i]);
        }

        //g2d.drawString("S",150,150);
        //g2d.drawString("A",0,0);
        //g2d.drawString("Z",-150,-150);

        //g2d.drawString("Testing Simulator",50,50);

        rl.draw(g2d);
    }

    //@Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    static Point2D.Double[] arenaPoints = {
            new Point2D.Double(0,150),
            new Point2D.Double(150,150),
            new Point2D.Double(150,-150),
            new Point2D.Double(-50,-150),
            new Point2D.Double(-50,0),
            new Point2D.Double(-150,0),
            new Point2D.Double(-150,-150),
            new Point2D.Double(-300,-150),
            new Point2D.Double(-200,150)
    };

}

