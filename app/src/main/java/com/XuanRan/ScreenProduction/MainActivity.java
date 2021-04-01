package com.XuanRan.ScreenProduction;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zackratos.ultimatebarx.library.UltimateBarX;
import com.zackratos.ultimatebarx.library.bean.BarConfig;

import java.util.Random;


public class MainActivity extends AppCompatActivity  {

    TextView source;
    TextView num;
    TextView time;
    LinearLayout linearLayout;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        int n = sharedPreferences.getInt("tips",1);

        if(n <= 3){
            showTips(n);
            editor.putInt("tips",++n);
            editor.commit();
        }

        source = findViewById(R.id.source);
        num = findViewById(R.id.num);
        time = findViewById(R.id.time);
        linearLayout = findViewById(R.id.linear);
        source.setTextColor(Color.WHITE);
        num.setTextColor(Color.WHITE);
        time.setTextColor(Color.WHITE);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("About");
                alert.setMessage("Development By XuanRan 3135419729@qq.com");
                alert.setPositiveButton("OK",null);
                alert.show();
            }
        });


        source.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(source);
            }
        });
       num.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               showDialog(num);
           }
       });
       time.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               showDialog(time);
           }
       });


        BarConfig barConfig = new BarConfig()
                .fitWindow(true)
                .color(getResources().getColor(R.color.wxcolor))
                .light(true);
        UltimateBarX.with(this).config(barConfig).applyStatusBar();


    }

    private void showTips(int n) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(String.format("第%d次提示",n));
        alert.setMessage("如果遇到位置不正确的话多输入几个空格就行了,如果是高度太高，懒得修复。");
        alert.setPositiveButton("OK",null);
        alert.show();
    }


    /**
     * @param textView
     */
    private void showDialog(final TextView textView) {

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("输入");
        final EditText editText = new EditText(this);
        alert.setView(editText);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                textView.setText(editText.getText().toString());
            }
        });
        alert.show();

    }

}
