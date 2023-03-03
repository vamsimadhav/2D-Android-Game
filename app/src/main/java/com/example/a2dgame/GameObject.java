package com.example.a2dgame;

import android.graphics.Canvas;

/*Game Object is an abstract class which
*is the foundation of all world objects in a game
*/
public abstract class GameObject {
    protected double positionX;
    protected double positionY;
    protected double velocityX;
    protected double velocityY;

    public GameObject(double positionX, double positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public abstract void draw(Canvas canvas);

    public abstract void update();
}
