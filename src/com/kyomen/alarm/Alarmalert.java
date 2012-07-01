package com.kyomen.alarm;


import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

public class Alarmalert extends Activity {
	
	public static final int check_edit = 1;
	private Timer timer;
	private TextView data_add;
	int last_input_len = 0;
	int input_len = 0;
	public static final int total_length = 128;
	int dialog_showed = 0;
	
	private Handler myHandler = new Handler(){
		public void handleMessage(Message msg)
		{
			switch(msg.what){
			case check_edit:
				input_len = data_add.getText().length();
				Log.v("","timer to check edit text leng is: "+input_len+" last input length is "+last_input_len);
				if(input_len <= last_input_len)
				{
					Log.v("","you must fall alseep");
					if(dialog_showed == 0)
					{
						showDialog(Alarmalert.this);
						dialog_showed = 1;
					}	
				}
				if(input_len >= total_length)
				{
					Log.v("","the worker finish work");
				}
				last_input_len = input_len;
			}
		};
	};
	
	private class myTask extends TimerTask{
    	@Override
    	public void run(){
    		Message message = new Message();
    		message.what = check_edit;
    		myHandler.sendMessage(message);
    		//Log.v("","timer task run");
    	}
	}
    
	
	  @Override
	  protected void onCreate(Bundle savedInstanceState) 
	  {
	    super.onCreate(savedInstanceState);
        setContentView(R.layout.alarmalert);
	    Log.v("alarmalert","the activity to recive alarm data");
	    
	    data_add  = (TextView)findViewById(R.id.dailyinput);
	    
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
	  
	  
	  private void showDialog(Context context) {  
	        AlertDialog.Builder builder = new AlertDialog.Builder(context);  
	        builder.setTitle("Title");  
	        builder.setMessage("Message");  
	        builder.setPositiveButton("OK",  
	                new DialogInterface.OnClickListener() {  
	                    public void onClick(DialogInterface dialog, int whichButton) {  
	                        //finish(); 
	                        dialog_showed = 0;
	                        Log.v("","some one click the ok button");
	                    }  
	                });   
	        builder.show();  
	    } 
}
