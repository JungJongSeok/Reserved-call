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

	//��ȭ ���� �ٲ����� state ����, incomingNumber ��ȭ��ȣ
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
		// ��ȭ���� ���� (��ȭ��ư�� ���� ����)
		case TelephonyManager.CALL_STATE_OFFHOOK:
			break;

		// ��ȭ���� �︮�� �ִ� ����
		case TelephonyManager.CALL_STATE_RINGING:
			Toast.makeText(mContext, "CALL_RINGING", 1).show();
			Log.d(TAG, "CALL_RINGING");

			Toast.makeText(mContext,
					"RINGING >> Incoming number : " + incomingNumber, 1).show();
			Log.d(TAG, "RINGING >> Incoming number : " + incomingNumber);
			ReservedMain.txt_incoming.setText(incomingNumber + "��ȭ�Խ��ϴ�.");

			// ��ȭ ������ ���� TelephonyManager
			TelephonyManager telephoney = (TelephonyManager) mContext
					.getSystemService(Context.TELEPHONY_SERVICE);
			try {
				Class c = Class.forName(telephoney.getClass().getName());
				Method m = c.getDeclaredMethod("getITelephony");
				m.setAccessible(true);
				// ��ȭ����
				telephonyService = (ITelephony) m.invoke(telephoney);
				telephonyService.endCall();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		super.onCallStateChanged(state, incomingNumber);
	}

}
