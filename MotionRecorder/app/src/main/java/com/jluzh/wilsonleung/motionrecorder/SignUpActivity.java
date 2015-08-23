package com.jluzh.wilsonleung.motionrecorder;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.listener.SaveListener;


/**
 * Created by Administrator on 2015/7/20.
 */
public class SignUpActivity extends Activity{
    private EditText editText_username;
    private EditText editText_password;
    private EditText editText_passwordagain;
    private Button button_signup;
    private Button btn_cancel;
    private UserDBAdapter userDBAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("注册");
        setContentView(R.layout.layout_signup);
        ExitApplication.getInstance().addActivity(this);
        userDBAdapter = new UserDBAdapter(this);
        userDBAdapter.open();
        editText_username= (EditText) findViewById(R.id.editText_username);
        editText_password= (EditText) findViewById(R.id.editText_password);
        editText_passwordagain= (EditText) findViewById(R.id.editText_passwordagain);
        button_signup = (Button)findViewById(R.id.button_signup_main);
        button_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editText_username.getText().toString();
                String password = editText_password.getText().toString();
                String conPassword = editText_passwordagain.getText().toString();
                if ((username == null || username.equalsIgnoreCase("")) || (password == null || password.equalsIgnoreCase("")) || (conPassword == null || conPassword.equalsIgnoreCase(""))) {
                    Toast.makeText(SignUpActivity.this, "请输入用户名和密码.",
                            Toast.LENGTH_SHORT).show();
                } else {
//                    Cursor cursor = userDBAdapter.getDiary(username);
//                    if (cursor.moveToFirst()) {
//                        Toast.makeText(SignUpActivity.this, "用户名已经存在！",
//                                Toast.LENGTH_SHORT).show();
//                    } else
                    if (!password.equals(conPassword)) {
                        Toast.makeText(SignUpActivity.this, "两次输入密码不一样，请重新输入！",
                                Toast.LENGTH_SHORT).show();
                    } else {
//                        userDBAdapter.createUser(username, password);

                        User u=new User();
                        u.setUsername(username);
                        u.setPassword(password);
                        u.save(SignUpActivity.this, new SaveListener() {
                            @Override
                            public void onSuccess() {
                                Toast.makeText(SignUpActivity.this, "注册成功！！",
                                        Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(int i, String s) {

                            }
                        });


                        SharedPreferences mySharedPreferences = getSharedPreferences("username_record",
                                SignUpActivity.MODE_PRIVATE);
                        SharedPreferences.Editor editor = mySharedPreferences.edit();
                        editor.putString("name", username);
                        editor.commit();

                        Intent intent = new Intent();
                        intent.setClass(SignUpActivity.this, ReadyActivity.class);
                        startActivity(intent);

                    }
                }
            }

        });

        btn_cancel= (Button) findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



}
