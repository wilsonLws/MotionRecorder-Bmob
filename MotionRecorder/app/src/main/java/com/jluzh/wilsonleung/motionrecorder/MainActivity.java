package com.jluzh.wilsonleung.motionrecorder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import cn.bmob.v3.Bmob;

import static com.jluzh.wilsonleung.motionrecorder.FinalActivity.showdialog_menu;

public class MainActivity extends Activity {
    private Button button_signin;
    private Button button_signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ExitApplication.getInstance().addActivity(this);

        Bmob.initialize(this, "0d0a4ecaec60e6e498994a604592a40b");

        button_signin= (Button) findViewById(R.id.button_signin);
        button_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(MainActivity.this,SignInActivity.class);
                startActivity(i);


            }
        });
        button_signup= (Button) findViewById(R.id.button_signup);
        button_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(i);
            }
        });

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        showdialog_menu(MainActivity.this);
        return super.onKeyDown(keyCode, event);}

}
