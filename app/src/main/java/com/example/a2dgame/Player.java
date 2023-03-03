package com.example.a2dgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;


/*
* Player is the main character of the game,
* which the user can control with the touch of joystick.
* The Player Class is an extension of circle which is
* an extension of an Game Object
*/

public class Player extends Circle {
    private static final double SPEED_PIXELS_PER_SECOND = 400;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;
    private final JoyStick joyStick;


    public Player(Context context,JoyStick joyStick, double positionX, double positionY, double radius) {
        super(ContextCompat.getColor(context,R.color.player),positionX,positionY,radius);

        this.joyStick = joyStick;

    }

    public void update() {
        //Update velocity based on actuator of joystick
        velocityX = joyStick.getActuatorX() * MAX_SPEED;
        velocityY = joyStick.getActuatorY() * MAX_SPEED;

        // Update position
        positionX += velocityX;
        positionY += velocityY;
    }
}
