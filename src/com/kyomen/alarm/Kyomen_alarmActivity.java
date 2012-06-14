package com.kyomen.alarm;

import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;



import android.app.AlarmManager;

import java.security.PublicKey;
import java.util.Calendar;


import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

//TODO �������˳���ʱ��ʱ������û���˳���������Ҫ�����˳���ʱ���鶨ʱ�����ظ��˳��Ƿ��������

public class Kyomen_alarmActivity extends Activity {
    /** Called when the activity is first created. */
	public static final int check_edit = 1;
	public static final int enough_length = 16;
	private TextView data_add;
	private Button button_share;
	private int textlength = 0;
	private Timer timer;
	Vibrator vibrator;// = (Vibrator) getSystemService(VIBRATOR_SERVICE);
	final long[] vibrate_pattern = {800, 50, 400, 30}; // OFF/ON/OFF/ON...  
	MediaPlayer mplayer;
	private TimePickerDialog timepickdialog;
	

	
	Calendar c= Calendar.getInstance();
	int mHour=c.get(Calendar.HOUR_OF_DAY);
    int mMinute=c.get(Calendar.MINUTE);
    
	
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
					//vibrator.cancel();
					//TODO try to send the message to the other app
					
				}
				else
				{
					//TODO do something to alarm
					Log.v("", "ARE YOU SLEEP?");
					//vibrator.vibrate(vibrate_pattern, 2);//TODO you must make sure this work well
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
        button_share = (Button)findViewById(R.id.button_share);
        timer = new Timer();
//        try{
//        	timer.scheduleAtFixedRate(new myTask(), 1, 5000);
//        }
//        catch (Exception e) {
//        	e.printStackTrace();
//		}
//        //vibrator =  (Vibrator) getSystemService(VIBRATOR_SERVICE);
//        mplayer = MediaPlayer.create(this, R.raw.ring);
//          	mplayer.start();
        timepickdialog = new TimePickerDialog( this, mTimeSetListener, mHour, mMinute, true);
        
		OnClickListener listener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.v("MyTag", "the onclick button id is "+v.getId());

				if (v.getId() == R.id.button_share) {
					Log.v("MyTag",
							"run the code to handler click button share");
//					Intent intent = new Intent(Intent.ACTION_SEND);
//					intent.setType("text/plain");
//					intent.putExtra(Intent.EXTRA_SUBJECT, "����");
//					intent.putExtra(Intent.EXTRA_TEXT,
//							"I would like to share this with you...");
//					startActivity(Intent.createChooser(intent, getTitle()));
					/* ȡ�õ����ťʱ��ʱ����ΪTimePickerDialog��Ĭ��ֵ */
			        c.setTimeInMillis(System.currentTimeMillis());
			        mHour=c.get(Calendar.HOUR_OF_DAY);
			        mMinute=c.get(Calendar.MINUTE);
			        /* ����TimePickerDialog������ʱ�� */
			        timepickdialog.show();
			        //new TimePickerDialog( this, mTimeSetListener, mHour, mMinute, true).show();
				}
			}
		};
		button_share.setOnClickListener(listener);
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
    
    private TimePickerDialog.OnTimeSetListener mTimeSetListener =  
            new TimePickerDialog.OnTimeSetListener()   
       {  
  
                @Override  
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) { 
                	 c.setTimeInMillis(System.currentTimeMillis());
                     c.set(Calendar.HOUR_OF_DAY,hourOfDay);
                     c.set(Calendar.MINUTE,minute);
                     c.set(Calendar.SECOND,0);
                     c.set(Calendar.MILLISECOND,0);
                     Intent intent = new Intent(Kyomen_alarmActivity.this, CallAlarm.class);
                     PendingIntent sender=PendingIntent.getBroadcast(
                    		 Kyomen_alarmActivity.this,0, intent, 0);
                     AlarmManager am;
                     am = (AlarmManager)getSystemService(ALARM_SERVICE);
                     am.set(AlarmManager.RTC_WAKEUP,
                            c.getTimeInMillis(),
                            sender
                           );
                     int hour = hourOfDay;  
                     int minute1 = minute;    
                        Toast.makeText(getBaseContext(),   
                            "You have selected : " + hour + ":" + minute1,  
                            Toast.LENGTH_SHORT).show();  
                }  
            }; 
}