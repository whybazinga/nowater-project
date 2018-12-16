package com.nowater;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable {
    public static float sizeX = 100;
    public static float sizeY = 100;
    private boolean firstTime = true;
    private boolean gameRunning = true;
    private Device device;
    private Thread gameThread;
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;

    public GameView(Context context) {
        super(context);
        //инициализируем обьекты для рисования
        surfaceHolder = getHolder();
        paint = new Paint();
        device = null;
        System.out.println("new");
        // инициализируем поток
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void setDevice(Device device) {
        if(device.pic == null) device.init(getContext(), 0);
        this.device = device;
    }

    public void stopGame(){
        gameRunning = false;
    }

    public void resumeGame(){
        gameRunning = true;
    }

    @Override
    public void run() {
        while (gameRunning) {
            update();
            draw();
            control();
        }
    }

    private void update() {
        if (!firstTime && device != null) {
            device.update();
        }
    }

    private void draw() {
        if (surfaceHolder.getSurface().isValid()) {  //проверяем валидный ли surface

            if (firstTime) { // инициализация при первом запуске
                firstTime = false;

                sizeX = surfaceHolder.getSurfaceFrame().width();
                sizeY = surfaceHolder.getSurfaceFrame().height();

                this.setDevice(GameActivity.device);
            }

            canvas = surfaceHolder.lockCanvas(); // закрываем canvas
            canvas.drawColor(getResources().getColor(R.color.colorPrimaryLight)); // заполняем фон чёрным
            if (device != null) {
                device.draw(paint, canvas); // рисуем девайс
            }
            surfaceHolder.unlockCanvasAndPost(canvas); // открываем canvas
        }
    }

    private void control() { // пауза на 17 миллисекунд
        try {
            gameThread.sleep(170);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
