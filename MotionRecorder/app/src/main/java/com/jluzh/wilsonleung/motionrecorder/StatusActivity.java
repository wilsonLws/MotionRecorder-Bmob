package com.jluzh.wilsonleung.motionrecorder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

import static com.jluzh.wilsonleung.motionrecorder.FinalActivity.showdialog_menu;


/**
 * Created by Administrator on 2015/7/21.
 */
public class StatusActivity extends Activity {
    private TextView tv_show_step;
    private TextView tv_timer;
    private Button btn_stop;
    private TextView mTextview_time,mTextview_distance,mTextview_step,
            mTextview_speed;
    private long timer = 0;// 运动时间
    private static long startTimer = 0;// 开始时间
    private static long tempTime = 0;
    private int total_step = 0;
    private Thread thread;
    private String  mDistance,mSpeed,mTime,mStep;
    Handler handler = new Handler() {// Handler对象用于更新当前步数

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);

            tv_show_step.setText(total_step + "");// 显示当前步数
            tv_timer.setText(getFormatTime(timer));// 显示当前运行时间
            countStep();
            float distance=(float)(total_step*0.8);
            String Distance=Float.toString(distance);
            mTextview_distance.setText(Distance);

            String time=(String) tv_timer.getText();
            String [] temp = null;
            temp = time.split(":");
            int hour=Integer.parseInt(temp[0]);
            int minute=Integer.parseInt(temp[1]);
            int second=Integer.parseInt(temp[2]);
            int Time=hour*3600+minute*60+second;
            float speed=(float)(distance/Time);
            DecimalFormat fnum=new   DecimalFormat("##0.00");
            String   Speed=fnum.format(speed);
            mTextview_speed.setText(Speed);

        }


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.layout_status);
        ExitApplication.getInstance().addActivity(this);

        tv_show_step = (TextView) this.findViewById(R.id.textView_step);
        tv_timer = (TextView) this.findViewById(R.id.textView_time);
        btn_stop = (Button) this.findViewById(R.id.button_stop);
        mTextview_distance=(TextView)findViewById(R.id.textView_distance);
        mTextview_speed=(TextView)findViewById(R.id.textView_speed);

        final Intent service = new Intent(this, StepService.class);
        startService(service);

        startTimer = System.currentTimeMillis();
        tempTime = timer;

        if (thread == null) {

            thread = new Thread() {// 子线程用于监听当前步数的变化

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    super.run();
                    int temp = 0;
                    while (true) {
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        if (StepService.FLAG) {
                            Message msg = new Message();
                            if (temp != StepDetector.CURRENT_SETP) {
                                temp = StepDetector.CURRENT_SETP;
                            }
                            if (startTimer != System.currentTimeMillis()) {
                                timer = tempTime + System.currentTimeMillis()
                                        - startTimer;
                            }
                            handler.sendMessage(msg);// 通知主线程
                        }
                    }
                }
            };
            thread.start();
        }
        btn_stop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                stopService(service);


                mDistance = (String) mTextview_distance.getText();
                mSpeed = (String) mTextview_speed.getText();
                mTime = (String) tv_timer.getText();
                mStep = (String) tv_show_step.getText();

                Bundle data = new Bundle();
                data.putString("distance", mDistance);
                data.putString("time", mTime);
                data.putString("step", mStep);
                data.putString("speed", mSpeed);
                Intent intent = new Intent();
                intent.setClass(StatusActivity.this, FinalActivity.class);
                intent.putExtras(data);
                startActivity(intent);

                StepDetector.CURRENT_SETP = 0;
                tempTime = timer = 0;
                tv_timer.setText(getFormatTime(timer));
                tv_show_step.setText("0");
                mTextview_distance.setText("0");
                mTextview_speed.setText("0");
                handler.removeCallbacks(thread);


            }
        });


    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        // 获取界面控件


        // 初始化控件
        init();

    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }



    /**
     * 初始化界面
     */
    private void init() {
        countStep();
        tv_timer.setText(getFormatTime(timer + tempTime));
        tv_show_step.setText(total_step + "");



    }

    private String formatDouble(Double doubles) {
        DecimalFormat format = new DecimalFormat("####.##");
        String distanceStr = format.format(doubles);
        return distanceStr.equals(getString(R.string.zero)) ? getString(R.string.double_zero)
                : distanceStr;
    }






    private String getFormatTime(long time) {
        // long millisecond = time % 1000;
        time = time / 1000;
        long second = time % 60;
        long minute = (time % 3600) / 60;
        long hour = time / 3600;

        // 毫秒秒显示两位
        // String strMillisecond = "" + (millisecond / 10);
        // 秒显示两位
        String strSecond = ("00" + second)
                .substring(("00" + second).length() - 2);
        // 分显示两位
        String strMinute = ("00" + minute)
                .substring(("00" + minute).length() - 2);
        // 时显示两位
        String strHour = ("00" + hour).substring(("00" + hour).length() - 2);

        return strHour + ":" + strMinute + ":" + strSecond;
        // + strMillisecond;
    }



    private void countStep() {
        if (StepDetector.CURRENT_SETP % 2 == 0) {
            total_step = StepDetector.CURRENT_SETP / 2 * 3;
        } else {
            total_step = StepDetector.CURRENT_SETP / 2 * 3 + 1;
        }
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        showdialog_menu(StatusActivity.this);
        return super.onKeyDown(keyCode, event);}

}

