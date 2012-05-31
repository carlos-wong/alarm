package com.kyomen.alarm;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.util.Log;
import android.widget.TextView;

//TODO 当程序退出的时候定时器可能没有退出，所以需要程序退出的时候检查定时器。重复退出是否会有问题

public class Kyomen_alarmActivity extends Activity {
    /** Called when the activity is first created. */
	public static final int check_edit = 1;
	public static final int enough_length = 16;
	private TextView data_add;
	private int textlength = 0;
	private Timer timer;
	Vibrator vibrator;// = (Vibrator) getSystemService(VIBRATOR_SERVICE);

	private Handler myHandler = new Handler(){
		public void handleMessage(Message msg)
		{
			switch(msg.what){
			case check_edit:
				Log.v("test handler", "length of inpur is :"+data_add.getText().length()+"max length is :"+textlength);
				if(data_add.getText().length() > textlength)
					textlength = data_add.getText().length();
				else if(data_add.getText().length() > enough_length)
				{
					//TODO 用户输入已经达到设定值需要提示用户是继续输入还是将输入的内容按照某种方式储存
					//TODO 储存的方式不应该每次设置应该有一个默认的值。给用户一个反应的时间。当然第一次设置之前这个提示应该是一直存在的。
					Log.v("", "YEAH YOU FINISH");
					timer.cancel();
				}
				else
				{
					//TODO do something to alarm
					Log.v("", "ARE YOU SLEEP?");
				}
				break;
			}
		};
	};
	

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        data_add = (TextView)findViewById(R.id.data_input);
        timer = new Timer();
        try{
        	timer.scheduleAtFixedRate(new myTask(), 1, 5000);
        }
        catch (Exception e) {
        	e.printStackTrace();
		}
        vibrator =  (Vibrator) getSystemService(VIBRATOR_SERVICE);
    }
    private class myTask extends TimerTask{
    	@Override
    	public void run(){
    		Message message = new Message();
    		message.what = check_edit;
    		myHandler.sendMessage(message);
    		Log.v("","timer task run");
    	}
	}
}