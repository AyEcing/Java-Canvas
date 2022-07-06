package com.microsoft.emmx;

import android.Manifest;
import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.icu.text.SimpleDateFormat;
import android.icu.util.TimeZone;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.os.Vibrator;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import com.microsoft.emmx.R;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/*
**by 阿夜
**create 2022 05 07
**写的很乱，部分地方有注释，早期的东西，放出来
*/
public class MainActivity extends Activity {

    private boolean CC=false,XFC=false,XX=false;
    private Timer Timer;
    private TextRun TextRun;
    private ScrollView GD;
    private TextView GG,XFJ,CX,JB;
    private Button DL;//声明按钮
    private ViewUtils V;
    private EditText Km;

    private int seekWidth = 256;
    private int seekHeight = 256;
    private String Time;
    private LinearLayout bc,theme;
    private WindowManager mwindow;
    private WindowManager.LayoutParams lparam;
    private WindowManager mwMenu;
    private WindowManager.LayoutParams mparam;
    private ImageButton mbutton;

    private String Filename="/sdcard/Tencent/";
    private int mTouchStartX, mTouchStartY;//手指按下时坐标
    private boolean isMove = false;
    private boolean isShow = false;
    private boolean winShow = false;
    private boolean background = false;
    public static int process=1500;
    public static boolean ZRHZ=false,HZFK=false,HZXX=false,HZSX=false,ZM=false,ZZ=false;

	public static int screenx,screeny;
    public static int width,height;
    public static BufferedReader idate;
    public static int ColorT[];
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        getWindow().setBackgroundDrawable(null);
        //设置为沉浸式状态栏
        
        ColorT = new int[100];
        GG = findViewById(R.id.activitymainTextViewGG);
        GD = findViewById(R.id.activitymainScrollViewGD);
        XFJ = findViewById(R.id.activitymainTextViewXHJ);
        DL = findViewById(R.id.activitymainButtonDL);//获取登录按钮控件
        bc = findViewById(R.id.activitymainLinearLayoutBC);
        Km = findViewById(R.id.activitymainEditTextKM);
        theme = findViewById(R.id.activityTheme);
        CX = findViewById(R.id.activitymainTextViewxCX);
        JB = findViewById(R.id.activitymainTextViewJB);
        //获取控件属性
        Timetask();
        //计时器任务
        telegram();
        //添加小灰机
        startKM();
        //登录
        CheckPermission();
        //检测应用权限
        checkKm();
        //获取屏幕宽高
        GetScreen();
        //写出二进制
        OutFiles(this, getFilesDir() + "/assets", "Drawing","freedrawqs");
        OutFiles(this, getFilesDir() + "/assets", "Bullet tracking","freebulbyqingshan");
        OutFiles(this, getFilesDir() + "/assets", "Aiming at oneself","freeaimbyqingshan");
    }

    private void checkKm() {
        //解绑和查询卡密
        //查询卡密
        CX.setOnClickListener(new OnClickListener(){
                //获取时间
                @Override
                public void onClick(View p1) {
                    new Thread(new Runnable() {
                            @Override
                            public void run() {
                                URL url = null;//取得资源对象
                                try {
                                    url = new URL("http://www.baidu.com");
                                    URLConnection baidu = url.openConnection();//生成连接对象
                                    baidu.connect(); //发出连接
                                    long time13 =  baidu.getDate(); //取得网站日期时间
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                                    sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
                                    Time = sdf.format(new Date(time13));
                                    Log.d("时间",Time);
                                } catch (Exception e) {
                                    ShowToast("数据异常",false);
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                        //查询
                        
          }});
    }

    private void login() {
        File f=new File(Filename+"km");  
        if(f.exists())  
        {  
            Km.setText(readFileString(f));
        }
        
    }

    private void GetScreen() {
        WindowManager windowManager = getWindow().getWindowManager();
        Point point = new Point();
        windowManager.getDefaultDisplay().getRealSize(point);
        //屏幕实际宽度（像素个数）
        width = point.x;
        //屏幕实际高度（像素个数）
        height = point.y;
        
    }

    public String readFileString(File file){
        String str = "";
        if(file.canRead()){
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                int length = fileInputStream.available();
                byte[] bytes = new byte[length];
                fileInputStream.read(bytes);
                str = new String(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return str;
    }
    private void CheckPermission() {
        String  Writer = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        String  Info = Manifest.permission.READ_PHONE_STATE;
        if (Cpromission(Writer)) {
            //ShowToast("储存已获取",false);
            CC = true;
        }
        if (Cpromission(Info)) {
            //ShowToast("手机信息已获取",false);
            XX = true;
        }
        if (Settings.canDrawOverlays(MainActivity.this)) {
            //ShowToast("悬浮窗已获取",false);
            XFC = true;
        }
        if (CC && XX && XFC) {
            ShowToast("权限校验通过", false);
            login();
        } else {
            Timer.cancel();
            GetPremiss.Q1 = XFC;
            GetPremiss.Q2 = CC;
            GetPremiss.Q3 = XX;
            Intent pre = new Intent(MainActivity.this, GetPremiss.class);
            startActivity(pre);
            finish();
            ShowToast("软件需要给予一些权限才能正常运行!", false);
        }
    }

    private void startKM(){
        new Thread(new Runnable() {
                @Override
                public void run() {
                    URL url = null;//取得资源对象
                    try {
                        url = new URL("http://www.baidu.com");
                        URLConnection baidu = url.openConnection();//生成连接对象
                        baidu.connect(); //发出连接
                        long time13 =  baidu.getDate(); //取得网站日期时间
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
                        Time = sdf.format(new Date(time13));
                        Log.d("时间",Time);
                    } catch (Exception e) {
                        ShowToast("数据异常",false);
                        e.printStackTrace();
                    }
                }
            }).start();
        DL.setOnClickListener(new OnClickListener(){
                //卡密验证
                @Override
                public void onClick(View p1) {
                    try{
						String k=Km.getText().toString();
						
                        String text="ay666";
						//这里是你的软件卡密
                        if(k.equals(text))
                        {
                            ShowToast("验证成功!",false);
                            DL.setText("登录");
                            StartXFC();
                            write(Filename,k);
                            if (!background) {
                                theme.setVisibility(View.INVISIBLE);
                                theme.setBackgroundResource(R.drawable.bcicom);
                                V.showAndHiddenAnimation(theme, ViewUtils.AnimationState.STATE_SHOW, 1000);
                                background = true;
                            }
                        }
                        else
                        {
                            ShowToast("卡密错误",false);
                        }
                    
                }catch (Exception mm){ShowToast("好家伙，输入的啥东西!",false);}
				}
            });
        }
    
    private void StartXFC() {
        ignoreBatteryOptimization();
        //验证卡密，显示悬浮窗
        DL.setOnClickListener(new  OnClickListener(){
                //匿名方法，不容易被捕捉
                @Override
                public void onClick(View p1) {
                    if (!isShow) {
                        start();
                        moveTaskToBack(true);
                    } else {
                        ShowToast("貌似已经开启悬浮窗了呢✘", false);
                    }
                }
            });
    }
    public void ignoreBatteryOptimization() {

        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);

        boolean hasIgnored = false;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            hasIgnored = powerManager.isIgnoringBatteryOptimizations(MainActivity.this.getPackageName());
            //  判断当前APP是否有加入电池优化的白名单，如果没有，弹出加入电池优化的白名单的设置对话框。
            if(!hasIgnored) {
                Intent intent = new Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
                intent.setData(Uri.parse("package:"+MainActivity.this.getPackageName()));
                startActivity(intent);
                ShowToast("请给予无限制权限!保证绘制流畅!",true);
            }
            
        }
    }
    private void telegram() {//点击小飞机后执行监听
        XFJ.setOnClickListener(new OnClickListener(){
                //重写点击事件
                @Override
                public void onClick(View p1) {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    intent.setData(Uri.parse("https://t.me/+T8LrLPnqYMYyOGM1"));
                    startActivity(intent);
                    ShowToast("欢迎加入Telegram!", true);
                }
            });
    }

    private void Timetask() {//计时器任务
        GD.setVisibility(View.GONE);
        Timer = new Timer(true);
        //定于计时器
        Timer.schedule(TimerTask, 1000, 1000);
        //开始执行任务
    }

    Handler Handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                //可以动态获取信息
                TextRun = new TextRun(GD, GG, "测试，仅供交流!没了，没了，测试一波!java绘制\n\nby.阿夜", 100);
                Timer.cancel();
            }
        }
    };
    TimerTask TimerTask = new TimerTask()
    //建立计时器任务
    {
        //匿名内部类
        @Override
        public void run() {
            Message Message = new Message();
            Message.what = 1;
            Handler.sendMessage(Message);
            //传递数据
        }
    };

	//设置按两次退出
    private long mExitTime;
    //对返回键进行监听
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            ShowToast("再按一次返回键退出ค(TㅅT)ค ", false);
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }

    public void ShowToast(String twtext, boolean color) {
        LayoutInflater inflater = getLayoutInflater();
        //创建容器
        View layout = inflater.inflate(R.layout.tw, (ViewGroup)findViewById(R.id.twLinearLayout1)); 
        //获取视图
        TextView text = layout.findViewById(R.id.twTextView1); 
        //获取文本ID
        text.setText(twtext);
        //设置文本
        if (color) {
            text.setTextColor(Color.BLACK);
        }
        Toast toast= new Toast(getApplicationContext()); 
        toast.setGravity(Gravity.BOTTOM, 0, 200); 
        //设置显示位置
        toast.setDuration(Toast.LENGTH_LONG); 
        //默认时间为长
        toast.setView(layout); 
        toast.show();
    }

    public boolean Cpromission(String premiss) {
        int i = ContextCompat.checkSelfPermission(getApplicationContext(), premiss);
        if (i != PackageManager.PERMISSION_GRANTED) {
            //检测不到权限
            return false;
        } else {
            //已获取权限
            return true;
        }
    }

    //悬浮窗区-/--------/---/----------/----
    private void start() {
        
        for(int i=31;i<100;i++)
        {
            ColorT[i] = getRandColor();
        }
        ColorT[0] = Color.rgb(39,27,93);
        ColorT[1] = Color.rgb(205,179,128);
        ColorT[2] = Color.rgb(3,101,100);
        ColorT[3] = Color.rgb(3,54,73);
        ColorT[4] = Color.rgb(96,143,159);
        ColorT[5] = Color.rgb(1,77,103);
        ColorT[6] = Color.rgb(200,200,169);
        ColorT[7] = Color.rgb(131,175,155);
        ColorT[8] = Color.rgb(252,157,154);
        ColorT[9] = Color.rgb(229,187,129);
        ColorT[10] = Color.rgb(118,77,57);
        ColorT[11] = Color.rgb(17,63,61);
        ColorT[12] = Color.rgb(60,79,57);
        ColorT[13] = Color.rgb(95,92,51);
        ColorT[14] = Color.rgb(227,160,93);
        ColorT[15] = Color.rgb(178,190,126);
        ColorT[16] = Color.rgb(114,111,128);
        ColorT[17] = Color.rgb(89,61,67);
        ColorT[18] = Color.rgb(20,68,106);
        ColorT[19] = Color.rgb(130,57,53);
        ColorT[20] = Color.rgb(201,186,131);
        ColorT[21] = Color.rgb(222,151,83);
        ColorT[22] = Color.rgb(23,44,60);
        ColorT[23] = Color.rgb(39,71,93);
        ColorT[24] = Color.rgb(232,221,203);
        ColorT[25] = Color.rgb(153,80,43);
        ColorT[26] = Color.rgb(174,221,123);
        ColorT[27] = Color.rgb(6,128,67);
        ColorT[28] = Color.rgb(38,157,123);
        ColorT[29] = Color.rgb(117,121,71);
        ColorT[30] = Color.rgb(69,137,148);
        ////////*
        mwindow = (WindowManager)getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        lparam = new WindowManager.LayoutParams();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            lparam.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            lparam.type = WindowManager.LayoutParams.TYPE_PHONE;
        }

        mbutton = new ImageButton(getApplicationContext());
        mbutton.setBackgroundResource(R.drawable.ic_launcher_round);
        mbutton.setOnTouchListener(new OnTouchListener()
            {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            isMove = false;
                            mTouchStartX = (int) event.getRawX();
                            mTouchStartY = (int) event.getRawY();
                            break;
                        case MotionEvent.ACTION_MOVE:
                            int nowX = (int) event.getRawX();
                            int nowY = (int) event.getRawY();
                            int movedX = nowX - mTouchStartX;
                            int movedY = nowY - mTouchStartY;
                            if (movedX > 5 || movedY > 5) {
                                isMove = true;
                            }
                            mTouchStartX = nowX;
                            mTouchStartY = nowY;
                            lparam.x += movedX;
                            lparam.y += movedY;
                            mwindow.updateViewLayout(mbutton, lparam);
                            break;
                        case MotionEvent.ACTION_UP:
                            break;
                        case MotionEvent.ACTION_CANCEL:
                            break;
                    }
                    return isMove;
                }
            });
        mbutton.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    Vibrator vib = (Vibrator)getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
                    if (!isMove) {
                        vib.vibrate(100);
                        mwindow.removeView(mbutton);
                        isShow = false;
                    }
                    return true;
                }
            });
        mbutton.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View p1) {
                    try {
                        if (!winShow) {
                            GetWin();
                            winShow = true;
                            mwindow.removeView(mbutton);
                            
                        } else {
                            ShowToast("悬浮窗已经开启!", false);
                        }
                    } catch (Exception e) {
                        ShowToast("异常!", false);
                    }
                }
            });
        lparam.format = PixelFormat.RGBA_8888;
        lparam.gravity = Gravity.LEFT;
        lparam.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        lparam.width = 80;
        //宽度
        lparam.height = 80;
        //高度
        if (mparam == null) {
            lparam.x = 300;
            lparam.y = 0;
        } else {
            lparam.x = mparam.x;
            lparam.y = mparam.y;
        }
        mwindow.addView(mbutton, lparam);
        isShow = true;
    }
    private void GetWin() {
        mwMenu = (WindowManager)getApplicationContext().getSystemService(Context.WINDOW_SERVICE);

        mparam = new WindowManager.LayoutParams();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mparam.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            mparam.type = WindowManager.LayoutParams.TYPE_PHONE;
        }
        LayoutInflater inflater = getLayoutInflater();
        //创建容器
        final View layout = inflater.inflate(R.layout.ayxfc, (ViewGroup)findViewById(R.id.AYxfcLinearLayout));
        LinearLayout re = layout.findViewById(R.id.AYxfcLinearLayout1);//收起
        TextView t1 = layout.findViewById(R.id.AYxfcTextView1);//绘制
        TextView t2 = layout.findViewById(R.id.AYxfcTextView2);//功能
        final CheckBox c1 = layout.findViewById(R.id.AYxfcCheckBox1);//注入绘制
        final CheckBox c2 = layout.findViewById(R.id.AYxfcCheckBox2);//方框
        final CheckBox c3 = layout.findViewById(R.id.AYxfcCheckBox3);//信息
        final CheckBox c4 = layout.findViewById(R.id.AYxfcCheckBox4);//射线
        final LinearLayout hz = layout.findViewById(R.id.AYxfcLinearLayoutHZ);
        final LinearLayout gn = layout.findViewById(R.id.AYxfcLinearLayoutGN);
        final SeekBar  bar = layout.findViewById(R.id.AYxfcSeekBar);
        final RadioButton zm = layout.findViewById(R.id.ayxfcRadioButton1);
        final RadioButton zz = layout.findViewById(R.id.ayxfcRadioButton2);
        
        Drawable drawable = getNewDrawable(MainActivity.this, R.drawable.barbcicon, seekWidth, seekHeight);
        bar.setThumb(drawable);
        bar.setProgress(process);
        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
                @Override
                public void onProgressChanged(SeekBar p1, int p2, boolean p3) {
                }

                @Override
                public void onStartTrackingTouch(SeekBar p1) {
                }

                @Override
                public void onStopTrackingTouch(SeekBar p1) {
                    process = bar.getProgress();
                }
            });
        layout.setOnTouchListener(new OnTouchListener()
            {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            isMove = false;
                            mTouchStartX = (int) event.getRawX();
                            mTouchStartY = (int) event.getRawY();
                            break;
                        case MotionEvent.ACTION_MOVE:
                            int nowX = (int) event.getRawX();
                            int nowY = (int) event.getRawY();
                            int movedX = nowX - mTouchStartX;
                            int movedY = nowY - mTouchStartY;
                            if (movedX > 5 || movedY > 5) {
                                isMove = true;
                            }
                            mTouchStartX = nowX;
                            mTouchStartY = nowY;
                            mparam.x += movedX;
                            mparam.y += movedY;
                            mwMenu.updateViewLayout(layout, mparam);
                            break;
                        case MotionEvent.ACTION_UP:
                            break;
                        case MotionEvent.ACTION_CANCEL:
                            break;
                    }
                    return isMove;
                }
            });
        if (ZRHZ) {
            c1.setChecked(true);
        }
        if (HZFK) {
            c2.setChecked(true);
        }
        if (HZXX) {
            c3.setChecked(true);
        }
        if (HZSX) {
            c4.setChecked(true);
        }
        if(ZZ)
        {
            zz.setChecked(true);
        }
        if(ZM)
        {
            zm.setChecked(true);
        }
        zm.setTextColor(Color.YELLOW);
        zz.setTextColor(Color.RED);
        zm.setOnClickListener(new OnClickListener(){
                //监听
                @Override
                public void onClick(View p1) {
                    if(ZM==false)
                    {
                    ZM=true;
                    ShowToast("自瞄开启成功!", false);
                    StartC("/assets/freeaimbyqingshan");
                    }
                }
            });
        zz.setOnClickListener(new OnClickListener(){
                //监听
                @Override
                public void onClick(View p1) {
                    if(ZZ==false)
                    {
                    ZZ=true;
                    ShowToast("子追开启成功!", false);
                    StartC("/assets/freebulbyqingshan");
                    }
                }
            });
        c1.setOnClickListener(new OnClickListener(){
                //注入绘制监听
                @Override
                public void onClick(View p1) {
                    Vibrator vib = (Vibrator)getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
                    vib.vibrate(10);

                    if (c1.isChecked()) {
                        Intent DrawActivity = new Intent(getApplicationContext(), DrawActivity.class);
                        startService(DrawActivity);
                        ShowToast("绘制开启成功!", false);
                        ZRHZ = true;
                        StartC("/assets/freedrawqs");
                        //绘制
                    } else {
                        Intent DrawActivity = new Intent(getApplicationContext(), DrawActivity.class);
                        stopService(DrawActivity);
                        ShowToast("关闭绘制!", false);
                        ZRHZ = false;
                    }
                }
            });
        c2.setOnClickListener(new OnClickListener(){
                //注入方框
                @Override
                public void onClick(View p1) {
                    Vibrator vib = (Vibrator)getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
                    vib.vibrate(10);
                    if (c2.isChecked()) {
                        ShowToast("方框开启成功!", false);
                        HZFK = true;

                    } else {
                        ShowToast("关闭方框!", false);
                        HZFK = false;
                    }
                }
            });
        c3.setOnClickListener(new OnClickListener(){
                //注入信息
                @Override
                public void onClick(View p1) {
                    Vibrator vib = (Vibrator)getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
                    vib.vibrate(10);
                    if (c3.isChecked()) {
                        ShowToast("信息开启成功!", false);
                        HZXX = true;

                    } else {
                        ShowToast("关闭信息!", false);
                        HZXX = false;
                    }
                }
            });
        c4.setOnClickListener(new OnClickListener(){
                //注入射线
                @Override
                public void onClick(View p1) {
                    Vibrator vib = (Vibrator)getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
                    vib.vibrate(10);
                    if (c4.isChecked()) {
                        ShowToast("射线开启成功!", false);
                        HZSX = true;
                    } else {
                        ShowToast("关闭射线!", false);
                        HZSX = false;
                    }
                }
            });
        t1.setOnClickListener(new OnClickListener(){
                //点击绘制
                @Override
                public void onClick(View p1) {

                    Vibrator vib = (Vibrator)getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
                    vib.vibrate(10);
                    gn.setVisibility(View.GONE);
                    hz.setVisibility(View.GONE);
                    V.showAndHiddenAnimation(hz, ViewUtils.AnimationState.STATE_SHOW_RIGHT, 500);

                }
            });

        t2.setOnClickListener(new OnClickListener(){
                //点击功能
                @Override
                public void onClick(View p1) {

                    Vibrator vib = (Vibrator)getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
                    vib.vibrate(10);
                    hz.setVisibility(View.GONE);
                    hz.setVisibility(View.GONE);
                    V.showAndHiddenAnimation(gn, ViewUtils.AnimationState.STATE_SHOW_LEFT, 500);
                }
            });

        re.setOnClickListener(new OnClickListener(){
                //关闭悬浮窗
                @Override
                public void onClick(View p1) {
                    winShow = false;
                    lparam.x=mparam.x;
                    lparam.y=mparam.y;
                    mwMenu.removeView(layout);
                    mwindow.addView(mbutton,lparam);
                }
            });

        mparam.format = PixelFormat.RGBA_8888;
        mparam.gravity = Gravity.LEFT;
        mparam.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        mparam.width = WindowManager.LayoutParams.WRAP_CONTENT;
        //宽度
        mparam.height = WindowManager.LayoutParams.WRAP_CONTENT;
        //高度
        mparam.x = lparam.x;//x
        mparam.y = lparam.y;//y
        mwMenu.addView(layout, mparam);//添加布局
        //////////////////////////


    }
    public void StartC(String file) {
        String files=getFilesDir() + file;
        RunShell("su -c chmod 7777 " + files);
        RunShell("su -c " + files);
        RunShell("chmod 7777 " + files);
        RunShell(files);
    }
    public static void RunShell(String shell) {
        String s = shell;

        try {
            Runtime.getRuntime().exec(s, null, null);//执行
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static boolean OutFiles(Context context, String outPath, String fileName,String out) {
        File file = new File(outPath);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                Log.e("--Method--", "copyAssetsSingleFile: cannot create directory.");
                return false;
            }
        }
        try {
            InputStream inputStream = context.getAssets().open(fileName);
            File outFile = new File(file, out);
            FileOutputStream fileOutputStream = new FileOutputStream(outFile);
            // Transfer bytes from inputStream to fileOutputStream
            byte[] buffer = new byte[1024];
            int byteRead;
            while (-1 != (byteRead = inputStream.read(buffer))) {
                fileOutputStream.write(buffer, 0, byteRead);
            }
            inputStream.close();
            fileOutputStream.flush();
            fileOutputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void write(String file, String content) {
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(content);
            fw.close();
        } catch (IOException e) {}
    }
    public BitmapDrawable getNewDrawable(Activity context, int restId, int dstWidth, int dstHeight) {

        Bitmap Bmp = BitmapFactory. decodeResource(context.getResources(), restId);

        Bitmap bmp = Bmp.createScaledBitmap(Bmp, dstWidth, dstHeight, true);

        BitmapDrawable d = new BitmapDrawable(bmp);

        Bitmap bitmap = d.getBitmap();

        if (bitmap.getDensity() == Bitmap.DENSITY_NONE) {

            d.setTargetDensity(context.getResources().getDisplayMetrics());

        }

        return d;

    }
    public int getRandColor() {
        Random random = new Random();
        int red=random.nextInt(100);

        int green=random.nextInt(256);

        int blue=random.nextInt(256);

        return Color.rgb(red,green,blue);
    }
}
