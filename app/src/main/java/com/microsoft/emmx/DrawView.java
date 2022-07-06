package com.microsoft.emmx;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.SystemClock;
import android.view.View;
import android.view.WindowManager;
import java.io.BufferedReader;
import java.io.FileReader;
import android.util.Log;

public class DrawView extends View implements Runnable {
    private static Point point;
    static long sleepTime;
    private static WindowManager wm;
    String FileName= "/sdcard/b.log";
    private float mFPS = 0;
    private float mFPSCounter = 0;
    private long mFPSTime = 0;
    Thread mThread;
    Paint paint = new Paint();
    int FPS = 144;
    int mfps;
    Float X;
    Float Y;
    Float W;
    float Distance;
    float HP;
    int AI;
    String ZY;
    int DX;
    Float H;

    String Name;
    int pmx=MainActivity.height,pmy=MainActivity.width,th;
    Paint HZFK = new Paint();
    Paint HZBK =new Paint();
    Paint HZHP = new Paint();
    Paint HZXT =new Paint();
    Paint HZWZ = new Paint();
    Paint SX = new Paint();
    Paint HZName = new Paint();
    Paint HZDistancr = new Paint();
    Paint HZXX = new Paint();
    Paint HZZY = new Paint();
    
    private void init() {
        
        HZFK.setStrokeWidth(2);
        HZFK.setAntiAlias(true);
        HZFK.setDither(true);
        HZFK.setStrokeCap(Paint.Cap.ROUND);
        HZFK.setStyle(Paint.Style.STROKE);
        HZFK.setStrokeJoin(Paint.Join.ROUND);
        

        HZBK.setColor(Color.parseColor("#FFFFFFFF"));
        HZBK.setStrokeWidth(1);
        HZBK.setAntiAlias(true);
        HZBK.setDither(true);
        HZBK.setStyle(Paint.Style.FILL);
        HZBK.setAlpha(30);
        HZBK.setStrokeJoin(Paint.Join.ROUND);
        
        HZHP.setARGB(150, 0, 255, 127);
        HZHP.setAntiAlias(true);
        HZHP.setDither(true);
        HZHP.setStrokeWidth(6);
        HZHP.setStyle(Paint.Style.FILL);

        HZXT.setARGB(225, 255, 0, 0);
        HZXT.setAntiAlias(true);
        HZXT.setDither(true);
        HZXT.setStyle(Paint.Style.FILL);

        SX.setColor(Color.RED);
        SX.setStrokeCap(Paint.Cap.ROUND);
        SX.setStrokeWidth(2);
        SX.setDither(true);
        SX.setAntiAlias(true);

        HZName.setDither(true);
        HZName.setStrokeCap(Paint.Cap.ROUND);
        HZName.setAntiAlias(true);
        HZName.setTextSize(20);
        HZName.setFakeBoldText(true);
        HZName.setTextAlign(Paint.Align.CENTER);
        HZName.setColor(Color.WHITE);
        HZName.setFakeBoldText(true);
        
        HZDistancr.setAntiAlias(true);
        HZDistancr.setStrokeCap(Paint.Cap.ROUND);
        HZDistancr.setTextSize(20);
        HZDistancr.setFakeBoldText(true);
        HZDistancr.setColor(Color.WHITE);
        HZDistancr.setTextAlign(Paint.Align.RIGHT);
        HZDistancr.setFakeBoldText(true);

        HZXX.setAntiAlias(true);
        HZXX.setStrokeCap(Paint.Cap.ROUND);
        HZXX.setColor(0xaf303030);
        HZXX.setStrokeJoin(Paint.Join.ROUND);

        HZZY.setAntiAlias(true);
        HZZY.setStrokeCap(Paint.Cap.ROUND);
        HZZY.setTextSize(23);
        HZZY.setFakeBoldText(true);
        HZZY.setColor(Color.WHITE);
        HZZY.setTextAlign(Paint.Align.CENTER);
        HZZY.setFakeBoldText(true);
        
    }
    public void cleanCanvas(Canvas mCanvas) {
        mCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
    }

    public DrawView(Context context) {
        super(context);
        setFocusableInTouchMode(false);
        setBackgroundColor(0);
        init();
        wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        point = new Point();
        sleepTime = (1000 / FPS);
        this.mThread = new Thread(this);
        this.mThread.setDaemon(true);
        this.mThread.start();
    }

    public void paintView(Canvas canvas) {
            int people=0,ai=0;
            if (SystemClock.uptimeMillis() - this.mFPSTime > 1000) {
                this.mFPSTime = SystemClock.uptimeMillis();
                this.mFPS = this.mFPSCounter;
                this.mFPSCounter = 0;
            } else {
                this.mFPSCounter++;
            }
           canvas.drawText("Ailice" , 100.0f, 120.0f, HZName);
           if(FPS==144)
           {
               canvas.drawText("帧率:" + mFPS, 100.0f, 150.0f, HZName);
           }
           else
           {
               canvas.drawText("动态调频:" + mFPS, 100.0f, 150.0f, HZName);
           }
           canvas.drawText("延迟:"+-((MainActivity.process/100)-15) , 100.0f, 180.0f, HZName);
        try {
            try(BufferedReader idate = new BufferedReader(new FileReader(FileName));){
                    String Infomation=null;
                    while ((Infomation = idate.readLine()) != null) {
                    if(Infomation.contains(","))
                    {
                    String []Date = Infomation.split(",");
                    Y = Float.parseFloat(Date[1]);
                    W = Float.parseFloat(Date[2]);
                    X = Float.parseFloat(Date[0]);
                    H = Float.parseFloat(Date[3]);
                    Distance = Float.parseFloat(Date[4]);
                    HP = Float.parseFloat(Date[5]);
                    AI = Integer.parseInt(Date[6]);
                    ZY = String.valueOf(Date[7]);
                    DX = Integer.parseInt(Date[8]);
                    int hzzy = Integer.valueOf(ZY);
                    Name = Date[9];
                    //绘制背敌
                    if (X != null && Y != null && Distance > 0 && W > 0) {
                        X = X + (W / 2);
                        //绘制条件
                        if (HP > 0 && MainActivity.HZXX) {
                            //绘制信息
                           
                            /////////
                            
                            if(AI==0)
                            {
                                int H=hzzy-1000;
                                ai+=1;
                                HZXX.setColor(Color.TRANSPARENT);
                                canvas.drawRect(X - 130, (Y - W) - 66, X + 130, (Y - W) - 27, HZXX);
                                canvas.drawRect(X - 130, (Y - W) - 66, X - 90, (Y - W) - 33, HZXX);
                                canvas.drawText("AI:"+H, X - 100, (Y - W) - 42, HZZY);
                            }
                            else
                            {
                                people+=1;
                                HZXX.setColor(MainActivity.ColorT[hzzy]);
                                canvas.drawRect(X - 130, (Y - W) - 66, X + 130, (Y - W) - 27, HZXX);
                                canvas.drawRect(X - 130, (Y - W) - 66, X - 90, (Y - W) - 33, HZXX);
                                canvas.drawText(ZY, X - 111, (Y - W) - 42, HZZY);
                            }
                            canvas.drawLine(X - 126, (Y - W) - 31, (X - 126) + ((float)2.54 * HP), (Y - W) - 31, HZHP);
                            canvas.drawText(Name, X, (Y - W) - 42, HZName);
                            canvas.drawText("▼", X, (Y - W) - 7, HZName);
                            canvas.drawText((int)Distance + "m", X + 120, (Y - W) - 42, HZDistancr);
                            
                                
                        }
                        if (HP > 0 && MainActivity.HZFK) {
                            //绘制方框
                            if (Y > (pmy / 2) - W && Y < (pmy / 2) + W && X > (pmx / 2) - (W / 2) && X < (pmx / 2) + (W / 2)) {
                                HZFK.setColor(Color.RED);
                                canvas.drawRect(X - (W / 2), (Y - W), X + (W / 2), Y + W, HZFK);
                            } else {
                                HZFK.setColor(Color.WHITE);
                                canvas.drawRect(X - (W / 2), (Y - W), X + (W / 2), Y + W, HZFK);
                            }
                        }
                        if (HP > 0 && MainActivity.HZSX ) {
                            //绘制射线
                            if(hzzy>=1000)
                            {
                                SX.setColor(Color.WHITE);
                                canvas.drawLine(pmx / 2, 125, X , (Y - W), SX);
                            }
                            else
                            {
                                SX.setColor(Color.RED);
                                canvas.drawLine(pmx / 2, 125, X , (Y - W), SX);
                            }
                        }
                   }else{
                       //背后
                       if(AI==0)
                       {
                           ai+=1;
                       }
                       else
                       {
                           people+=1;
                       }
                   }
                 }}
            }
        } catch (Exception e) {}
        HZXX.setColor(Color.GREEN);
        canvas.drawRect((pmx/2)-60,55,pmx/2,100,HZXX);
        canvas.drawText(ai+"",pmx/2-30,(float)87.5,HZZY);
        HZXX.setColor(Color.RED);
        canvas.drawRect((pmx/2)+60,55,pmx/2,100,HZXX);
        canvas.drawText(people+"",pmx/2+30,(float)87.5,HZZY);
        if(people + ai ==0)
        {
            FPS = 6;
        }
        else
        {
            FPS = 144;
        }
        people=0;
        ai=0;
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        cleanCanvas(canvas);
        paintView(canvas);
        wm.getDefaultDisplay().getRealSize(point);
        sleepTime = (1000 / FPS);
    }

    public void run() {
        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_URGENT_DISPLAY);
        while (this.mThread.isAlive() && !this.mThread.isInterrupted()) {
            try {
                long t1 = System.currentTimeMillis();
                postInvalidate();        
                Thread.sleep(Math.max(Math.min(0, sleepTime - (System.currentTimeMillis() - t1)), sleepTime));
                Thread.sleep(-((MainActivity.process/100)-15));
            } catch (Exception e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
