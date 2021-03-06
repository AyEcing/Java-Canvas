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
        
        //???????????????
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
        //??????????????????
        SetClick();
        //??????????????????
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
                        ShowToast("?????????????????????????????????!",false);
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
                        ShowToast("?????????????????????????????????!",false);
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
                        ShowToast("?????????????????????????????????!",false);
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
                        ShowToast("?????????????????????????????????!",false);
                    }else
                    {
                        ShowToast("????????????????????????????????????",false);
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
        //????????????
        TextRun =new TextRun(L,T4,"????????????\n\n      ????????????????????????????????????????????????????????????????????????????????????!  ??????????????????????????????!\n      ?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????! ??????????????????????????????????????????????????????------by.??????",80);
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
                        ShowToast("????????????!",false);
                    }
                }
            }).start();
    }

    class Run extends Thread {
        //??????run??????
        @Override
        public void run() {
            try {
                UI1.setText("?????????~");
                ViewChange.showAndHiddenAnimation(UI1,ViewUtils.AnimationState.STATE_SHOW,1000);
                Thread.sleep(1000);
                ViewChange.showAndHiddenAnimation(UI1,ViewUtils.AnimationState.STATE_HIDDEN,1000);
                Thread.sleep(1000);
                UI2.setText("???????????????????????????");
                ViewChange.showAndHiddenAnimation(UI2,ViewUtils.AnimationState.STATE_SHOW,1000);
                Thread.sleep(1000);
                ViewChange.showAndHiddenAnimation(UI2,ViewUtils.AnimationState.STATE_HIDDEN,1000);
                Thread.sleep(1000);
                UI3.setText("???????????????????????????");
                ViewChange.showAndHiddenAnimation(UI3,ViewUtils.AnimationState.STATE_SHOW,1000);
                Thread.sleep(1000);
                ViewChange.showAndHiddenAnimation(UI3,ViewUtils.AnimationState.STATE_HIDDEN,1000);
                Thread.sleep(1000);
                UI4.setText("???????????????????????????");
                ViewChange.showAndHiddenAnimation(UI4,ViewUtils.AnimationState.STATE_SHOW,1000);
                Thread.sleep(1000);
                ViewChange.showAndHiddenAnimation(UI4,ViewUtils.AnimationState.STATE_HIDDEN,1000);
                Thread.sleep(1000);
                Task();
            } catch (Exception e)
            {
                ShowToast("????????????!",false);
            }
        }
    }
    
    private void CheckPermission() {
        String  Writer = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        String  Info = Manifest.permission.READ_PHONE_STATE;
        if(Cpromission(Writer))
        {
            //ShowToast("???????????????",false);
            Q2 =true;
        }
        else
        {
            Q2 =false;
        }
        if(Cpromission(Info))
        {
            //ShowToast("?????????????????????",false);
            Q3 = true;
        }
        else
        {
            Q3 =false;
        }
        if (Settings.canDrawOverlays(GetPremiss.this)) {
            //ShowToast("??????????????????",false);
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
            //??????????????????
            return false;
        }
        else
        {
            //???????????????
            return true;
        }
    }
    
    public void ShowToast(String twtext,boolean color)
    {
        LayoutInflater inflater = getLayoutInflater();
        //????????????
        View layout = inflater.inflate(R.layout.tw,(ViewGroup)findViewById(R.id.twLinearLayout1)); 
        //????????????
        TextView text = layout.findViewById(R.id.twTextView1); 
        //????????????ID
        text.setText(twtext);
        //????????????
        if(color)
        //???????????????????????????????????????????????????
        {
            text.setTextColor(Color.BLACK);
        }
        Toast toast= new Toast(getApplicationContext()); 
        toast.setGravity(Gravity.BOTTOM,0,200); 
        //??????????????????
        toast.setDuration(Toast.LENGTH_LONG); 
        //??????????????????
        toast.setView(layout); 
        toast.show();
    }
}
