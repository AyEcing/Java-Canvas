package com.microsoft.emmx;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.microsoft.emmx.DrawView;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DrawActivity extends Service {

	private WindowManager.LayoutParams params;
    public WindowManager windowManager;
    public DrawView espLayout;
    public int type;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        WindowManager win=(WindowManager)getSystemService(Context.WINDOW_SERVICE);
        assert win != null;
        DrawView DrawView;
        espLayout = DrawView = new DrawView((Context)this);
        ////////*
        params = new WindowManager.LayoutParams();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        if (Build.VERSION.SDK_INT >= 28) {
            params.layoutInDisplayCutoutMode = 1;
        }
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            // android 8.0及以上使用
            params.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
            params.flags = computeFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
        } else {
            // android 8.0以下使用
            params.type = WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY;
            params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        }
        params.gravity = Gravity.LEFT | Gravity.TOP;
        params.format = PixelFormat.RGBA_8888;
        
        /////
        windowManager.addView((View)DrawView, (ViewGroup.LayoutParams)params);
        super.onCreate();
    }
    private int computeFlags(int curFlags) {
        boolean mTouchable = false;
        if (!mTouchable) {
            curFlags |= WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
        } else {
            curFlags &= ~WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
        }

        boolean mFocusable = false;
        if (!mFocusable) {
            curFlags |= WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        } else {
            curFlags &= ~WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        }

        boolean mTouchModal = true;
        if (!mTouchModal) {
            curFlags |= WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        } else {
            curFlags &= ~WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        }

        boolean mOutsideTouchable = false;
        if (mOutsideTouchable) {
            curFlags |= WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
        } else {
            curFlags &= ~WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
        }

        return curFlags;
    }
    @Override
    public void onDestroy() {
        windowManager.removeView(espLayout);
        WriteFile("/sdcard/stop","栀子守护!");
        super.onDestroy();
    }
    
    public void WriteFile(String file1 , String file2)
    {
        //文件写入 
        try {
            try (FileWriter writer = new FileWriter(file1);
            BufferedWriter bw = new BufferedWriter(writer)) {
            bw.write(file2);
            }
        } catch (IOException e) {}
    }
    
    }
