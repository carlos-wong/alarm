package com.kyomen.alarm;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class CallAlarm extends Activity {
	  public void onReceive(Context context, Intent intent)
	  {
	    /* create Intent£¬µ÷ÓÃAlarmAlert.class */
	    Intent i = new Intent(context, Alarmalert.class);
	    Bundle bundleRet = new Bundle();
	    bundleRet.putString("STR_CALLER", "");
	    i.putExtras(bundleRet);
	    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	    context.startActivity(i);
	  }
}
