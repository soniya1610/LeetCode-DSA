package Math;

import java.util.Scanner;
                
class Angle_Btw_Hands_Of_Clock {
    public double angleClock(int hour, int minutes) {
        double x = hour + minutes / 60.0;
        double diff = (11.0 * x) % 12.0;

        return Math.min(diff , 12.0-diff) * 30;
    }
}