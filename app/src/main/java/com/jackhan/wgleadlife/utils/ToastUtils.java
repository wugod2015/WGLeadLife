package com.jackhan.wgleadlife.utils;

import com.jackhan.wgleadlife.BaseApplication;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {
	private static Toast mToast;

	private ToastUtils() {
		// TODO Auto-generated constructor stub
	}

	public synchronized static void showShortToast(Context context,
			CharSequence text) {
		// TODO Auto-generated method stub
		if (mToast == null)
			mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
		else {
			mToast.setDuration(Toast.LENGTH_SHORT);
			mToast.setText(text);
		}
		mToast.show();
	}

	public synchronized static void showShortToast(CharSequence text) {
		// TODO Auto-generated method stub
		showShortToast(BaseApplication.getInstance(), text);
	}
}
