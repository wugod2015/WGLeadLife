package com.jackhan.wgleadlife.activity;

import android.os.Bundle;

import com.jackhan.wgleadlife.R;
import com.jackhan.wgleadlife.utils.LogUtils;
import com.jackhan.wgleadlife.utils.ToastUtils;
import com.jackhan.wgleadlife.widget.pinlockview.IndicatorDots;
import com.jackhan.wgleadlife.widget.pinlockview.PinLockListener;
import com.jackhan.wgleadlife.widget.pinlockview.PinLockView;

public class LockActivity extends BaseActivity {
	private static final String TAG = "LockActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		/*
		 * getWindow().setFlags(WindowManager.LayoutParams.MATCH_PARENT,
		 * WindowManager.LayoutParams.MATCH_PARENT);
		 */
		setContentView(R.layout.activity_lock);
	}

	PinLockView mLockView;
	IndicatorDots mIndicatorDots;
	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		mLockView = (PinLockView) findViewById(R.id.pinLockView);
		mIndicatorDots = (IndicatorDots) findViewById(R.id.indicatorDots);
		mLockView.attachIndicatorDots(mIndicatorDots);
		mLockView.setPinLockListener(new PinLockListener() {

			@Override
			public void onPinChange(int pinLength, String intermediatePin) {
				// TODO Auto-generated method stub
				LogUtils.d(TAG, "onPinChange-intermediatePin:"+intermediatePin);

			}

			@Override
			public void onEmpty() {
				// TODO Auto-generated method stub
				LogUtils.d(TAG, "onEmpty");

			}

			@Override
			public void onComplete(String pin) {
				// TODO Auto-generated method stub

				LogUtils.d(TAG, "onComplete-pin:"+pin);

				if ("0801".equals(pin)) {
					setResult(RESULT_OK);
					finish();
				} else {
					ToastUtils.showShortToast(mContext,
							getString(R.string.error_password));
				}
			}
		});
	}

}
