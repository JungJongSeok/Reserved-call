package com.example.reserved_message;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;


public class CallStateReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context,
				"CallStateReceiver >>>>>>> onReceive", 1)
				.show();
		Log.d(CallStateListener.TAG, "CallStateReceiver >>>>>>> onReceive");
		
		CallStateListener callStateListner = new CallStateListener(context);
		TelephonyManager telephony = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
		// TelephonyManager에 PhoneStateListner를 등록한다
		telephony.listen(callStateListner, PhoneStateListener.LISTEN_CALL_STATE);
	}
}
