package com.jluzh.wilsonleung.motionrecorder;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2015/7/20.
 */
public class SignInActivity extends Activity{
    private Button button_signin_main;
    private Button button_cancel;
    private EditText editText_username;
    private EditText editText_password;
    private CheckBox checkBox_remeberpassword;
    private static boolean remeberpassword;

    private UserDBAdapter userDBAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("登录");
        setContentView(R.layout.layout_signin);
        ExitApplication.getInstance().addActivity(this);

//        userDBAdapter = new UserDBAdapter(this);
//        userDBAdapter.open();

        editText_password= (EditText) findViewById(R.id.editText_password_in);
        editText_username= (EditText) findViewById(R.id.editText_username_in);
        checkBox_remeberpassword= (CheckBox) findViewById(R.id.checkBox_remeberpassword);

        SharedPreferences mySharedPreferences2 = getSharedPreferences("remeberpassword",
                Activity.MODE_PRIVATE);
        if( mySharedPreferences2.getBoolean("check_remeberpassword",false)){
            editText_username.setText(mySharedPreferences2.getString("name",""));
            editText_password.setText(mySharedPreferences2.getString("password",""));
            checkBox_remeberpassword.setChecked(true);
        }

        button_signin_main= (Button) findViewById(R.id.button_siginin_main);
        button_signin_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isNetworkConnectionChecked(SignInActivity.this)) {
                    checkUser();
//                if ((username == null || username.equalsIgnoreCase("")) || (password == null || password.equalsIgnoreCase(""))) {
//                    Toast.makeText(SignInActivity.this, "请输入用户名和密码！",
//                            Toast.LENGTH_SHORT).show();
//                } else {
//                    Cursor cursor = userDBAdapter.getDiary(username);
//
//                    if (!cursor.moveToFirst()) {
//                        Toast.makeText(SignInActivity.this, "用户名不存在！",
//                                Toast.LENGTH_SHORT).show();
//                    } else if (!password.equals(cursor.getString(2))) {
//                        Toast.makeText(SignInActivity.this, "密码错误，请再输入一次！.",
//                                Toast.LENGTH_SHORT).show();
//                    } else {
//
//                        Toast.makeText(SignInActivity.this, "登录成功！！",
//                                Toast.LENGTH_SHORT).show();

//                        Intent intent = new Intent();
//                        intent.setClass(SignInActivity.this, ReadyActivity.class);
//                        startActivity(intent);

//                    }
//                }
                }
                else
                {
                    Toast.makeText(SignInActivity.this,"网络连接失败，请检查网络！！"
                            ,Toast.LENGTH_LONG).show();
                }
            }
        });

        button_cancel= (Button) findViewById(R.id.button_cancel);
        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private void checkUser() {


        final ProgressDialog progressDialog=new ProgressDialog(SignInActivity.this);
        progressDialog.setMessage("登录中请稍后...");
        progressDialog.setCancelable(true);
        progressDialog.show();

        BmobQuery<User> query1 = new BmobQuery<User>();
        query1.addWhereEqualTo("username", editText_username.getText().toString());

        BmobQuery<User> query2=new BmobQuery<User>();
        query2.addWhereEqualTo("password", editText_password.getText().toString());

        List<BmobQuery<User>> queries=new ArrayList<BmobQuery<User>>();
        queries.add(query1);
        queries.add(query2);

        BmobQuery<User> query=new BmobQuery<User>();
        query.and(queries);
        query.findObjects(SignInActivity.this, new FindListener<User>() {
            @Override
            public void onSuccess(List<User> list) {
                Boolean isCheck = true;
                String username_remember = editText_username.getText().toString();
                String password_remember = editText_password.getText().toString();
                for (User u : list
                        ) {
                    if (u != null) {
                        progressDialog.dismiss();
                        Toast.makeText(SignInActivity.this, "登录成功！", Toast.LENGTH_LONG).show();

                        Intent i = new Intent(SignInActivity.this, ReadyActivity.class);
                        startActivity(i);

                        isCheck = false;

                        SharedPreferences mySharedPreferences = getSharedPreferences("username_record",
                                Activity.MODE_PRIVATE);
                        SharedPreferences.Editor editor = mySharedPreferences.edit();
                        editor.putString("name", username_remember);
                        editor.commit();

                        if (checkBox_remeberpassword.isChecked()) {
                            remeberpassword = true;
                            SharedPreferences mySharedPreferences1 = getSharedPreferences("remeberpassword",
                                    Activity.MODE_PRIVATE);
                            SharedPreferences.Editor editor1 = mySharedPreferences1.edit();
                            editor1.putString("name", username_remember);
                            editor1.putString("password", password_remember);
                            editor1.putBoolean("check_remeberpassword", remeberpassword);
                            editor1.commit();
                            checkBox_remeberpassword.setChecked(true);
                        } else {
                            remeberpassword = false;
                            SharedPreferences mySharedPreferences2 = getSharedPreferences("remeberpassword",
                                    Activity.MODE_PRIVATE);
                            SharedPreferences.Editor editor2 = mySharedPreferences2.edit();
                            editor2.putBoolean("check_remeberpassword", remeberpassword);
                            editor2.commit();
                        }
                    }
                }
                if (isCheck) {
                    progressDialog.dismiss();
                    Toast.makeText(SignInActivity.this, "密码或账号错误请重新输入！！"
                            , Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onError(int i, String s) {

                Toast.makeText(SignInActivity.this, "SignIn failed3", Toast.LENGTH_LONG).show();
            }
        });
    }

    private Boolean isNetworkConnectionChecked(Context context) {
        // TODO Auto-generated method stub
        ConnectivityManager connectivityManager=(ConnectivityManager) context.getSystemService( CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo= connectivityManager.getActiveNetworkInfo();
        if( networkInfo!= null)
        {
            return networkInfo.isAvailable();
        }
        return false;
    }

}
