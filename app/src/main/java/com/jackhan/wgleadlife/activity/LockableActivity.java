package com.jackhan.wgleadlife.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.jackhan.wgleadlife.ActivityUtils;
import com.jackhan.wgleadlife.utils.AppUtils;
import com.jackhan.wgleadlife.utils.LogUtils;

public class LockableActivity extends BaseActivity {

	final static String TAG = "LockableActivity";

	boolean isNeedLock = true;
	boolean isOpenLock = false;

	public boolean isOpenLock() {
		return isOpenLock;
	}

	public void setOpenLock(boolean isOpenLock) {
		this.isOpenLock = isOpenLock;
	}

	public boolean isNeedLock() {
		return isNeedLock;
	}

	public void setNeedLock(boolean isNeedLock) {
		this.isNeedLock = isNeedLock;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		startScreenReceiver();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(mScreenReceiver);
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		if (AppUtils.isApplicationInBackground(mContext)) {
			setOpenLock(false);
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		if (isNeedLock && !isOpenLock)
			ActivityUtils.startLockActivity(this,
					ActivityUtils.REQUEST_CODE_LOCK);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case ActivityUtils.REQUEST_CODE_LOCK:
			if (resultCode == RESULT_OK) {
				setOpenLock(true);
			} else {
				finish();
			}

			break;

		default:
			break;
		}
	}

	private void startScreenReceiver() {
		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_SCREEN_ON);
		filter.addAction(Intent.ACTION_SCREEN_OFF);
		registerReceiver(mScreenReceiver, filter);
	}

	ScreenReceiver mScreenReceiver = new ScreenReceiver();

	public class ScreenReceiver extends BroadcastReceiver {
		private String action = null;

		@Override
		public void onReceive(Context context, Intent intent) {
			action = intent.getAction();
			if (Intent.ACTION_SCREEN_ON.equals(action)) {
				LogUtils.d(TAG, "ACTION_SCREEN_ON");
			} else if (Intent.ACTION_SCREEN_OFF.equals(action)) {
				LogUtils.d(TAG, "ACTION_SCREEN_OFF");
				setOpenLock(false);
				ActivityUtils.startLockActivity(LockableActivity.this,
						ActivityUtils.REQUEST_CODE_LOCK);
			}
		}
	}
}
