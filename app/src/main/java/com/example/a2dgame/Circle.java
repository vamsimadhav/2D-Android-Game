package com.example.a2dgame;

import android.graphics.Canvas;
import android.graphics.Paint;

/*Circle is an abstract class which implements draw method from
*GameObject for drawing an object as circle
*/

public abstract class Circle extends GameObject{
    protected double radius;
    protected Paint paint;

    public Circle(int color, double positionX, double positionY, double radius) {
        super(positionX, positionY);
        this.radius = radius;

        //Set Colors of circle
        paint = new Paint();
        paint.setColor(color);
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle((float) positionX, (float) positionY, (float) radius, paint);
    }
}
