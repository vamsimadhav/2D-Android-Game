package com.example.a2dgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class JoyStick {

    private final int outerCircleRadius;
    private final int innerCircleRadius;
    private final Paint outerCirclePaint;
    private final Paint innerCirclePaint;
    private int outerCircleCenterPosX;
    private int outerCircleCenterPosY;
    private int innerCircleCenterPosX;
    private int innerCircleCenterPosY;
    private double joyStickCenterToTouchDistance;
    private boolean isPressed;
    private double actuatorX;
    private double actuatorY;

    public JoyStick(int centerPosX, int centerPosY, int outerCircleRadius, int innerCircleRadius) {

        //Draw the outer and innner circle of joystick
        outerCircleCenterPosX = centerPosX;
        outerCircleCenterPosY = centerPosY;
        innerCircleCenterPosX = centerPosX;
        innerCircleCenterPosY = centerPosY;

        //Radii of the circles
        this.outerCircleRadius = outerCircleRadius;
        this.innerCircleRadius = innerCircleRadius;

        //Setting Paint Object
        outerCirclePaint = new Paint();
        outerCirclePaint.setColor(Color.GRAY);
        outerCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        innerCirclePaint = new Paint();
        innerCirclePaint.setColor(Color.MAGENTA);
        innerCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle(outerCircleCenterPosX, outerCircleCenterPosY, outerCircleRadius, outerCirclePaint);
        canvas.drawCircle(innerCircleCenterPosX, innerCircleCenterPosY, innerCircleRadius, innerCirclePaint);
    }

    public void update() {
        updateInnerCirclePos();
    }

    private void updateInnerCirclePos() {
        innerCircleCenterPosX = (int)(outerCircleCenterPosX + actuatorX * outerCircleRadius);
        innerCircleCenterPosY = (int)(outerCircleCenterPosY + actuatorY * outerCircleRadius);
    }

    public boolean isPressed(double touchPosX, double touchPosY) {
        joyStickCenterToTouchDistance = Math.sqrt(
                Math.pow(outerCircleCenterPosX - touchPosX, 2) +
                Math.pow(outerCircleCenterPosY - touchPosY, 2)
        );
        return joyStickCenterToTouchDistance < outerCircleRadius;
    }

    public boolean getIsPressed() {
        return isPressed;
    }

    public void setPressed(boolean isPressed) {
        this.isPressed = isPressed;
    }

    public void setActuator(double touchPosX, double touchPosY) {
        double deltaX = touchPosX - outerCircleCenterPosX;
        double deltaY = touchPosY - outerCircleCenterPosY;
        double deltaDistance = Math.sqrt(Math.pow(deltaX,2) + Math.pow(deltaY,2));

        if (deltaDistance < outerCircleRadius) {
            actuatorX = deltaX/outerCircleRadius;
            actuatorY = deltaY/outerCircleRadius;
        } else {
            actuatorX = deltaX/deltaDistance;
            actuatorY = deltaY/deltaDistance;
        }

    }

    public void resetActuator() {
        actuatorX = 0.0;
        actuatorY = 0.0;
    }

    public double getActuatorX() {
        return actuatorX;
    }

    public double getActuatorY() {
        return actuatorY;
    }
}
