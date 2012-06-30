package com.kyomen.alarm;


import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class Alarmalert extends Activity {
	
	public static final int check_edit = 1;
	private Timer timer;

	
	private Handler myHandler = new Handler(){
		public void handleMessage(Message msg)
		{
			switch(msg.what){
			case check_edit:
				Log.v("","timer to check edit");
			}
		};
	};
	
	private class myTask extends TimerTask{
    	@Override
    	public void run(){
    		Message message = new Message();
    		message.what = check_edit;
    		myHandler.sendMessage(message);
    		Log.v("","timer task run");
    	}
	}
    
	
	  @Override
	  protected void onCreate(Bundle savedInstanceState) 
	  {
	    super.onCreate(savedInstanceState);
	    Log.v("alarmalert","the activity to recive alarm data");
		timer = new Timer();

		try {
			timer.scheduleAtFixedRate(new myTask(), 1, 5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    /* 跳出的闹铃警示  */
//	    new AlertDialog.Builder(this)
//	        .setTitle("闹钟响了!!")
//	        .setMessage("赶快起床吧!!!")
//	        .setPositiveButton("关掉他",
//	         new DialogInterface.OnClickListener()
//	        {
//	          public void onClick(DialogInterface dialog, int whichButton)
//	          {
//	            /* 关闭Activity */
//	            finish();
//	          }
//	        })
//	        .show();
	  } 
}
