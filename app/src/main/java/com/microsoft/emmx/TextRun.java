package com.microsoft.emmx;

import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

public class TextRun {

    private ViewUtils AnimationUtils;
    private static ScrollView ak;
    private static TextView tv;
    private static String s;
    private static int length;
    private static long time;
    static int n = 0;
    private static int nn;


    public TextRun(ScrollView ak,TextView tv, String s, long time) {
        this.tv = tv;//textview
        this.s = s;//字符串
        this.ak=ak;
        this.time = time;//间隔时间
        this.length = s.length();
        startTv(n);//开启线程
        //ak.setVisibility(View.VISIBLE);
        AnimationUtils.showAndHiddenAnimation(ak,ViewUtils.AnimationState.STATE_SHOW,1000);

    }


    public static void startTv(final int n) {

        new Thread(
            new Runnable() {
                @Override
                public void run() {
                    try {
                        final String stv = s.substring(0, n);//截取要填充的字符串
                        tv.post(new Runnable() {
                                @Override
                                public void run() {
                                    if (nn < length) 
                                    {
                                        tv.setText(stv+"_");
                                    }
                                    else
                                    {
                                        tv.setText(stv);
                                    }
                                }
                            }
                            );
                        Thread.sleep(time);//休息片刻
                        nn = n + 1;//n+1；多截取一个
                        if (nn <= length) 
                            {//如果还有汉字，那么继续开启线程，相当于递归的感觉
                            startTv(nn);
                            }
                            nn = n + 1;//n+1；多截取一个
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }
            }

        ).start();
 }}
 
 
