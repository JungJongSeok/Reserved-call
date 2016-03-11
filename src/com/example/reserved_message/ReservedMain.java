package com.example.reserved_message;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class ReservedMain extends Activity {

	public static final String TAG = "CallStateListner";
	public int mRingModeBackup = 0;
	public boolean mIsReservedMainActivity = false;
	public static TextView txt_incoming;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reserved_main);
		txt_incoming = (TextView) findViewById(R.id.incomingNumber);
		mIsReservedMainActivity = true;

		Toast.makeText(this,
				"CALL_RINGING_BACKUP_MODE mIsReservedMainActivity : "+mIsReservedMainActivity, 1)
				.show();
		Log.d(TAG, "CALL_RINGING_BACKUP_MODE mIsReservedMainActivity : "+mIsReservedMainActivity);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		GetRingerMode();
		SetStartReservedMain(mIsReservedMainActivity);
		
		super.onResume();
	}
	
	public void GetRingerMode(){
		AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
		mRingModeBackup = audioManager.getRingerMode();
	}
	
	public void SetStartReservedMain(boolean set){

		Toast.makeText(this,
				"SetStartReservedMain : "+set, 1)
				.show();
		Log.d(TAG, "SetStartReservedMain : "+set);
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub		
		super.onPause();
	}
	
	@Override
	protected void onDestroy() {

		Toast.makeText(this,
				"CALL_RINGING_BACKUP_MODE 22 : "+mRingModeBackup, 1)
				.show();
		Log.d(TAG, "CALL_RINGING_BACKUP_MODE 22 : "+mRingModeBackup);
		AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
		audioManager.setRingerMode(mRingModeBackup);//AudioManager.RINGER_MODE_NORMAL);//
		
		mIsReservedMainActivity = false;
		SetStartReservedMain(mIsReservedMainActivity);
		
		super.onDestroy();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.reserved_main, menu);
		return true;
	}
}
