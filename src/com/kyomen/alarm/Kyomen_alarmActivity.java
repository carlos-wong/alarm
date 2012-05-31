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

//TODO �������˳���ʱ��ʱ������û���˳���������Ҫ�����˳���ʱ���鶨ʱ�����ظ��˳��Ƿ��������

public class Kyomen_alarmActivity extends Activity {
    /** Called when the activity is first created. */
	public static final int check_edit = 1;
	public static final int enough_length = 16;
	private TextView data_add;
	private int textlength = 0;
	private Timer timer;
	Vibrator vibrator;// = (Vibrator) getSystemService(VIBRATOR_SERVICE);
	final long[] vibrate_pattern = {800, 50, 400, 30}; // OFF/ON/OFF/ON...  


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
					//TODO �û������Ѿ��ﵽ�趨ֵ��Ҫ��ʾ�û��Ǽ������뻹�ǽ���������ݰ���ĳ�ַ�ʽ����
					//TODO ����ķ�ʽ��Ӧ��ÿ������Ӧ����һ��Ĭ�ϵ�ֵ�����û�һ����Ӧ��ʱ�䡣��Ȼ��һ������֮ǰ�����ʾӦ����һֱ���ڵġ�
					Log.v("", "YEAH YOU FINISH");
					timer.cancel();
					vibrator.cancel();
				}
				else
				{
					//TODO do something to alarm
					Log.v("", "ARE YOU SLEEP?");
					vibrator.vibrate(vibrate_pattern, 2);//TODO you must make sure this work well
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