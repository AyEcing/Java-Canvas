package com.microsoft.emmx;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.microsoft.emmx.GetPremiss;
import com.microsoft.emmx.R;
import java.util.Timer;
import java.util.TimerTask;
public class GetPremiss extends Activity {
    
    private TextRun TextRun;
    private TextView UI1,UI2,UI3,UI4,T4;
    private ViewUtils ViewChange;
    private ScrollView L;
    private LinearLayout BU1,BU2,BU3,BU4;
    private ImageView img;

    public static boolean Q1;

    public static boolean Q2;

    public static boolean Q3;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.premiss);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        BU1 = findViewById(R.id.PremissLinearLayout1);
        BU2 = findViewById(R.id.PremissLinearLayout2);
        BU3 = findViewById(R.id.PremissLinearLayout3);
        BU4 = findViewById(R.id.PremissLinearLayout4);
        UI1 = findViewById(R.id.PremissTextView0);
        UI2 = findViewById(R.id.PremissTextView1);
        UI3 = findViewById(R.id.PremissTextView2);
        UI4 = findViewById(R.id.PremissTextView3);
        T4 = findViewById(R.id.PremissTextView4);
        L = findViewById(R.id.PremissScrollView);
        img = findViewById(R.id.PremissImageView1);
        
        //启动多线程
        Run at1 = new Run();
     
        at1.start();
        
        UI1.setVisibility(View.GONE);
        UI2.setVisibility(View.GONE);
        UI3.setVisibility(View.GONE);
        UI4.setVisibility(View.GONE);
        BU1.setVisibility(View.INVISIBLE);
        BU2.setVisibility(View.INVISIBLE);
        BU3.setVisibility(View.INVISIBLE);
        BU4.setVisibility(View.INVISIBLE);
        img.setVisibility(View.INVISIBLE);
        
        SetColor();
        //动态设置背景
        SetClick();
        //设置控件监听
        timer.schedule(task, 0, 500);
    }
    
    Timer timer = new Timer(true);  

    TimerTask task = new TimerTask() {  
        public void run() {  
            CheckPermission();
            SetColor();
        }     
    };  
    private void SetClick() {
        //
        BU1.setOnClickListener(new OnClickListener(){

                @Override
                public void onClick(View p1) {
                    if(Q1)
                    {
                        ShowToast("老板这个权限已经获取了!",false);
                    }else
                    {
                        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,Uri.parse("package:" + getPackageName()));
                        startActivityForResult(intent,1);
                    }
                }
            });
        BU2.setOnClickListener(new OnClickListener(){

                @Override
                public void onClick(View p2) {
                    if(Q2)
                    {
                        ShowToast("老板这个权限已经获取了!",false);
                    }else
                    {
                        String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        ActivityCompat.requestPermissions(GetPremiss.this, permissions, 2);
                    }
                }
            });
        BU3.setOnClickListener(new OnClickListener(){

                @Override
                public void onClick(View p3) {
                    if(Q3)
                    {
                        ShowToast("老板这个权限已经获取了!",false);
                    }else
                    {
                        String[] permissions = new String[]{Manifest.permission.READ_PHONE_STATE};
                        ActivityCompat.requestPermissions(GetPremiss.this, permissions, 3);
                    }
                }
            });
        BU4.setOnClickListener(new OnClickListener(){

                @Override
                public void onClick(View p4) {
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,Uri.parse("package:" + getPackageName()));
                    startActivityForResult(intent,1);
                }
            });
        img.setOnClickListener(new OnClickListener(){

                @Override
                public void onClick(View p5) {
                    if(Q1&&Q2&&Q3)
                    {
                        ShowToast("不得不承认，你手速好快!",false);
                    }else
                    {
                        ShowToast("等全部权限获取后再点我～",false);
                    }
                }
            });
    }

    private void SetColor() {
        if(!Q1)
        {
            BU1.setBackgroundColor(Color.RED);
        }
        else
        {
            BU1.setBackgroundColor(Color.GREEN);
        }
        if(!Q2)
        {
            BU2.setBackgroundColor(Color.RED);
        }
        else
        {
            BU2.setBackgroundColor(Color.GREEN);
        }
        if(!Q3)
        {
            BU3.setBackgroundColor(Color.RED);
        }
        else
        {
            BU3.setBackgroundColor(Color.GREEN);
        }
    }

    private void Task() {
        //开始打印
        TextRun =new TextRun(L,T4,"使用需知\n\n      首先要明白，该软件必须使用悬浮窗权限，存储权限，手机信息!  所以请放心授权。但是!\n      为什么会有获取设备信息的授权，在授权之前我先说一下，软件是必须要卡密才能使用，使用卡密会绑定您的设备，不用于其他用途，软件仅供学习交流! 软件请求失败，请点击软件设置手动授权------by.阿夜",80);
        new Thread(new Runnable(){
                @Override
                public void run() {
                    try
                    {
                        Thread.sleep(10000);
                        ViewChange.showAndHiddenAnimation(BU1,ViewUtils.AnimationState.STATE_SHOW_DOWN,1000);
                        Thread.sleep(1000);
                        ViewChange.showAndHiddenAnimation(BU2,ViewUtils.AnimationState.STATE_SHOW_LEFT,1000);
                        Thread.sleep(1000);
                        ViewChange.showAndHiddenAnimation(BU3,ViewUtils.AnimationState.STATE_SHOW_UP,1000);
                        Thread.sleep(1000);
                        ViewChange.showAndHiddenAnimation(BU4,ViewUtils.AnimationState.STATE_SHOW_UP,1000);
                        Thread.sleep(1000);
                        ViewChange.showAndHiddenAnimation(img,ViewUtils.AnimationState.STATE_SHOW_RIGHT,1000);
                        Thread.sleep(1000);
                    }catch (Exception e)
                    {
                        ShowToast("运行错误!",false);
                    }
                }
            }).start();
    }

    class Run extends Thread {
        //重写run方法
        @Override
        public void run() {
            try {
                UI1.setText("喵喵喵~");
                ViewChange.showAndHiddenAnimation(UI1,ViewUtils.AnimationState.STATE_SHOW,1000);
                Thread.sleep(1000);
                ViewChange.showAndHiddenAnimation(UI1,ViewUtils.AnimationState.STATE_HIDDEN,1000);
                Thread.sleep(1000);
                UI2.setText("正常工作需必要权限");
                ViewChange.showAndHiddenAnimation(UI2,ViewUtils.AnimationState.STATE_SHOW,1000);
                Thread.sleep(1000);
                ViewChange.showAndHiddenAnimation(UI2,ViewUtils.AnimationState.STATE_HIDDEN,1000);
                Thread.sleep(1000);
                UI3.setText("这个界面将引导授权");
                ViewChange.showAndHiddenAnimation(UI3,ViewUtils.AnimationState.STATE_SHOW,1000);
                Thread.sleep(1000);
                ViewChange.showAndHiddenAnimation(UI3,ViewUtils.AnimationState.STATE_HIDDEN,1000);
                Thread.sleep(1000);
                UI4.setText("授权后请点击图标～");
                ViewChange.showAndHiddenAnimation(UI4,ViewUtils.AnimationState.STATE_SHOW,1000);
                Thread.sleep(1000);
                ViewChange.showAndHiddenAnimation(UI4,ViewUtils.AnimationState.STATE_HIDDEN,1000);
                Thread.sleep(1000);
                Task();
            } catch (Exception e)
            {
                ShowToast("运行异常!",false);
            }
        }
    }
    
    private void CheckPermission() {
        String  Writer = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        String  Info = Manifest.permission.READ_PHONE_STATE;
        if(Cpromission(Writer))
        {
            //ShowToast("储存已获取",false);
            Q2 =true;
        }
        else
        {
            Q2 =false;
        }
        if(Cpromission(Info))
        {
            //ShowToast("手机信息已获取",false);
            Q3 = true;
        }
        else
        {
            Q3 =false;
        }
        if (Settings.canDrawOverlays(GetPremiss.this)) {
            //ShowToast("悬浮窗已获取",false);
            Q1 = true;
        }
        else
        {
            Q1 =false;
        }
        if(Q1&&Q2&&Q3)
        {
            timer.cancel();
            Intent a = new Intent(GetPremiss.this,MainActivity.class);
            startActivity(a);
            finish();
        }
        }
        
    public boolean Cpromission(String premiss) {
        int i = ContextCompat.checkSelfPermission(getApplicationContext(), premiss);
        if (i!= PackageManager.PERMISSION_GRANTED) {
            //检测不到权限
            return false;
        }
        else
        {
            //已获取权限
            return true;
        }
    }
    
    public void ShowToast(String twtext,boolean color)
    {
        LayoutInflater inflater = getLayoutInflater();
        //创建容器
        View layout = inflater.inflate(R.layout.tw,(ViewGroup)findViewById(R.id.twLinearLayout1)); 
        //获取视图
        TextView text = layout.findViewById(R.id.twTextView1); 
        //获取文本ID
        text.setText(twtext);
        //设置文本
        if(color)
        //存在背景为白色，设置字体颜色为黑色
        {
            text.setTextColor(Color.BLACK);
        }
        Toast toast= new Toast(getApplicationContext()); 
        toast.setGravity(Gravity.BOTTOM,0,200); 
        //设置显示位置
        toast.setDuration(Toast.LENGTH_LONG); 
        //默认时间为长
        toast.setView(layout); 
        toast.show();
    }
}
