package com.kyomen.alarm;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class Kyomen_alarmActivity extends Activity {
    /** Called when the activity is first created. */
	
	private Handler myHandler = new Handler(){
		public void handleMessage(Message msg)
		{
			switch(msg.what){
			case 1:
				Log.v("data_graph", "get timer task message");
				break;
			}
		};
	};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Timer timer = new Timer();
        try{
        timer.scheduleAtFixedRate(new myTask(), 1, 5000);
        }
        catch (Exception e) {
        	e.printStackTrace();
		}
    }
    private class myTask extends TimerTask{
    	@Override
    	public void run(){
    		Message message = new Message();
    		message.what = 1;
    		myHandler.sendMessage(message);
    	}
	}
}