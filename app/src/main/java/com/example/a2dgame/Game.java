package com.example.a2dgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;


/*
* Game Manages all objects in the game and
* is responsible for updating all states and
* render all objects to the screen
*/
public class Game extends SurfaceView implements SurfaceHolder.Callback {
    private final Player player;
    private final JoyStick joyStick;
    private GameLoop gameLoop;

    public Game(Context context) {
        super(context);

        //Get Surface Holder and add Callback
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        gameLoop = new GameLoop(this, surfaceHolder);

        //Initialize Game Objects
        joyStick = new JoyStick(275,700,70,40);
        player = new Player(getContext(), 2*500, 500, 30);

        setFocusable(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (joyStick.isPressed((double) event.getX(), (double) event.getY())) {
                    joyStick.setPressed(true);
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                if (joyStick.getIsPressed()) {
                    joyStick.setActuator((double) event.getX(), (double) event.getY());
                }
                return true;
            case MotionEvent.ACTION_UP:
                joyStick.setPressed(false);
                joyStick.resetActuator();
                return true;
        }

        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawUPS(canvas);
        drawFPS(canvas);

        player.draw(canvas);
        joyStick.draw(canvas);
    }

    public void drawUPS(Canvas canvas) {
        String averageUPS = Double.toString(gameLoop.getAverageUPS());
        Paint paint = new Paint();
        paint.setTextSize(50);
        paint.setColor(ContextCompat.getColor(getContext(),R.color.magenta));
        canvas.drawText("UPS : " + averageUPS, 100, 150, paint);
    }

    public void drawFPS(Canvas canvas) {
        String averageFPS = Double.toString(gameLoop.getAverageFPS());
        Paint paint = new Paint();
        paint.setTextSize(50);
        paint.setColor(ContextCompat.getColor(getContext(),R.color.magenta));
        canvas.drawText("FPS : " + averageFPS, 100, 250, paint);
    }

    public void update() {
        //Update game state
        joyStick.update();
        player.update(joyStick);
    }
}
