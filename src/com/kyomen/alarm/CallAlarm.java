package com.kyomen.alarm;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class CallAlarm extends BroadcastReceiver {
	  public void onReceive(Context context, Intent intent)
	  {
	    /* create Intent£¬µ÷ÓÃAlarmAlert.class */
		Log.v("test handler", "yeah receive the alarm");
	    Intent i = new Intent(context, Alarmalert.class);
	    Bundle bundleRet = new Bundle();
	    bundleRet.putString("STR_CALLER", "");
	    i.putExtras(bundleRet);
	    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	    context.startActivity(i);
	  }
}
