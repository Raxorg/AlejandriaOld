package com.frontanilla.alejandria.turrettest;

public class Utils {

    public static float getAngle(float x1, float x2, float y1, float y2) {
        float angle = (float) Math.toDegrees(Math.atan2(
                y2 - y1,
                x2 - x1));

        if (angle < 0) {
            angle += 360;
        }

        return angle;
    }
}
