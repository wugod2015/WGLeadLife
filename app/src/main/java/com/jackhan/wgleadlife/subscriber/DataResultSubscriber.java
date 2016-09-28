package com.jackhan.wgleadlife.subscriber;

import java.util.List;

import com.jackhan.wgleadlife.bean.DataResult;
import com.jackhan.wgleadlife.conf.Constant;
import com.jackhan.wgleadlife.utils.LogUtils;
import com.jackhan.wgleadlife.utils.ToastUtils;

public abstract class DataResultSubscriber<T> extends
		BaseSubscriber<DataResult<T>> {
	final static String TAG = "DataResultSubscriber";

	@Override
	public void onNext(DataResult<T> dataResult) {
		// TODO Auto-generated method stub
		LogUtils.d(TAG, "onNext");
		if (dataResult == null)
			return;
		if (Constant.GETDATA_OK == dataResult.error)
			if (dataResult.result == null)
				onResults(dataResult.status, dataResult.results);
			else
				onResult(dataResult.status, dataResult.result);
		else {

			ToastUtils.showShortToast(dataResult.message);
			onErrorResult(dataResult.message);
		}

	}

	@Override
	protected void onErrorResult(String errorStr) {
		// TODO Auto-generated method stub
		LogUtils.d(TAG, "onErrorResult");
	}

	public abstract void onResult(String msg, T t);

	protected void onResults(String msg, List<T> ts) {

		if (ts != null && ts.size() > 0)
			onResult(msg, ts.get(0));
	}
}
