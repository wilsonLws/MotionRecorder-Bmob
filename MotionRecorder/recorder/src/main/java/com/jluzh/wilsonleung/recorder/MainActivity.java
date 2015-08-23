package com.jluzh.wilsonleung.recorder;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class MainActivity extends AppCompatActivity {
    private EditText editText_username;
    private EditText editText_password;
    private Button btn_signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                checkUser();
                Intent i=new Intent(MainActivity.this,SecondActivity.class);
                startActivity(i);
            }

        });
    }

    private void checkUser() {


        BmobQuery<User> query1 = new BmobQuery<User>();
        query1.addWhereEqualTo("username", editText_username.getText().toString());
//        query1.findObjects(this, new FindListener<User>() {
//            @Override
//            public void onSuccess(List<User> list) {
//                Toast.makeText(MainActivity.this, "The user is exist", Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onError(int i, String s) {
//                Toast.makeText(MainActivity.this,"failed!!!",Toast.LENGTH_LONG).show();
//            }
//        });
        BmobQuery<User> query2=new BmobQuery<User>();
        query2.addWhereEqualTo("password", editText_password.getText().toString());
//        query2.findObjects(this, new FindListener<User>() {
//            @Override
//            public void onSuccess(List<User> list) {
//                Toast.makeText(MainActivity.this, "The password is exist", Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onError(int i, String s) {
//                Toast.makeText(MainActivity.this, "failed!!!", Toast.LENGTH_LONG).show();
//            }
//        });


        List<BmobQuery<User>> queries=new ArrayList<BmobQuery<User>>();
        queries.add(query1);
        queries.add(query2);

        BmobQuery<User> query3=new BmobQuery<User>();
        query3.and(queries);
        query3.findObjects(MainActivity.this, new FindListener<User>() {
            @Override
            public void onSuccess(List<User> list) {
                Boolean isCheck=true;
                for (User u : list
                        ) {
                    if (u != null) {
                        Toast.makeText(MainActivity.this, "SignIn Success"+u.getUsername().toString(), Toast.LENGTH_LONG).show();

                        SharedPreferences.Editor editor=getSharedPreferences("username",
                                MODE_PRIVATE).edit();
                        editor.putString("name",
                                editText_username.getText().toString());
                        editor.commit();

                        Intent i=new Intent(MainActivity.this,SecondActivity.class);
                        startActivity(i);



                        isCheck = false;
                    }
                }
                if (isCheck ) {
                    Toast.makeText(MainActivity.this, "SignIn failed", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onError(int i, String s) {

                Toast.makeText(MainActivity.this, "SignIn failed3", Toast.LENGTH_LONG).show();
            }
        });



    }

    private void initview() {
        editText_username= (EditText) findViewById(R.id.editText_username);
        editText_password= (EditText) findViewById(R.id.editText_password);
        btn_signin= (Button) findViewById(R.id.btn_signin);
    }
    private void submit() {
        User user=new User();
        user.setUsername(editText_username.getText().toString());
        user.setPassword(editText_password.getText().toString());
        user.save(MainActivity.this, new SaveListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(MainActivity.this,"提交成功！",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(MainActivity.this,"提交失败！请重新提交！",Toast.LENGTH_LONG).show();
            }
        });
    }



}
