package com.urim.robotsimulator;

/**
 * Created by uri on 9/12/15.
 * Algorithm reference: http://www.mathopenref.com/coordpolygonarea2.html
 */
public class Area {


    public static double CalculateArea(double[][] points)
    {
        double area = 0;
        int j = points.length -1; // previous point

        for (int i=0; i < points.length; i++) {
            // (X0+X1)*(Y0-Y1)
            area = area + (points[j][0] + points[i][0]) * (points[j][1] - points[i][1]);
            j=i;
        }
        if (area != 0)
            return area/2;
        else
            return 0;
    }
}
