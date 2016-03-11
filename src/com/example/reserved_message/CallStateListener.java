package com.example.reserved_message;

import java.lang.reflect.Method;
import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.media.AudioManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.android.internal.telephony.ITelephony;

public class CallStateListener extends PhoneStateListener {

	public static final String TAG = "CallStateListner";
	private ITelephony telephonyService;
	private Context mContext;

	public CallStateListener(Context context) {
		mContext = context;
	}

	//전화 상태 바꼈을때 state 상태, incomingNumber 전화번호
	@Override
	public void onCallStateChanged(int state, String incomingNumber) {

//		if (!mIsStartMain) {
//			return;
//		}

		// mReservedMain.getRingMode(AudioManager.RINGER_MODE_VIBRATE);
		// TODO Auto-generated method stub
		switch (state) {
		case TelephonyManager.CALL_STATE_IDLE:
			break;
		// 통화중인 상태 (통화버튼이 눌린 상태)
		case TelephonyManager.CALL_STATE_OFFHOOK:
			break;

		// 전화벨이 울리고 있는 상태
		case TelephonyManager.CALL_STATE_RINGING:
			Toast.makeText(mContext, "CALL_RINGING", 1).show();
			Log.d(TAG, "CALL_RINGING");

			Toast.makeText(mContext,
					"RINGING >> Incoming number : " + incomingNumber, 1).show();
			Log.d(TAG, "RINGING >> Incoming number : " + incomingNumber);
			ReservedMain.txt_incoming.setText(incomingNumber + "전화왔습니다.");

			// 전화 거절을 위한 TelephonyManager
			TelephonyManager telephoney = (TelephonyManager) mContext
					.getSystemService(Context.TELEPHONY_SERVICE);
			try {
				Class c = Class.forName(telephoney.getClass().getName());
				Method m = c.getDeclaredMethod("getITelephony");
				m.setAccessible(true);
				// 전화거절
				telephonyService = (ITelephony) m.invoke(telephoney);
				telephonyService.endCall();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		super.onCallStateChanged(state, incomingNumber);
	}

}
