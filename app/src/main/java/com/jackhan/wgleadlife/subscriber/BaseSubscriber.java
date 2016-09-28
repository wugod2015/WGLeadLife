package com.jackhan.wgleadlife.subscriber;

import java.net.ConnectException;

import com.google.gson.JsonSyntaxException;
import com.jackhan.wgleadlife.BaseApplication;
import com.jackhan.wgleadlife.R;
import com.jackhan.wgleadlife.utils.LogUtils;
import com.jackhan.wgleadlife.utils.ToastUtils;

import rx.Subscriber;

public abstract class BaseSubscriber<T> extends Subscriber<T> {
	final static String TAG = "BaseSubscriber";

	@Override
	public void onCompleted() {
		// TODO Auto-generated method stub
		LogUtils.d(TAG, "onCompleted");

	}

	@Override
	public void onError(Throwable throwable) {
		// TODO Auto-generated method stub
		LogUtils.d(TAG, "onError:" + throwable);

		String errorStr = BaseApplication.getInstance().getString(
				R.string.server_failure);
		try {
			throw throwable;
		} catch (ConnectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ToastUtils.showShortToast(errorStr);
		onErrorResult(errorStr);
	}

	protected abstract void onErrorResult(String errorStr);
}
