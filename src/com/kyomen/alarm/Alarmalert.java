package com.kyomen.alarm;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

public class Alarmalert extends Activity {
	  @Override
	  protected void onCreate(Bundle savedInstanceState) 
	  {
	    super.onCreate(savedInstanceState);
	    /* ���������徯ʾ  */
	    new AlertDialog.Builder(this)
	        .setTitle("��������!!")
	        .setMessage("�Ͽ��𴲰�!!!")
	        .setPositiveButton("�ص���",
	         new DialogInterface.OnClickListener()
	        {
	          public void onClick(DialogInterface dialog, int whichButton)
	          {
	            /* �ر�Activity */
	            finish();
	          }
	        })
	        .show();
	  } 
}
