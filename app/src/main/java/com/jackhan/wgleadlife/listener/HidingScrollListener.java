package com.jackhan.wgleadlife.listener;

import com.jackhan.wgleadlife.utils.LogUtils;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;

public abstract class HidingScrollListener extends OnScrollListener {
	final static String TAG = "HidingScrollListener";
	private static final int HIDE_THRESHOLD = 20;
	private int scrolledDistance = 0;
	private boolean controlsVisible = true;

	@Override
	public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
		super.onScrolled(recyclerView, dx, dy);
		LogUtils.d(TAG, "dx:" + dx + ",dy" + dy);
		if (scrolledDistance > HIDE_THRESHOLD && controlsVisible) {
			LogUtils.d(TAG, "scrolledDistance:" + scrolledDistance + "onHide()");
			onHide();
			controlsVisible = false;
			scrolledDistance = 0;
		} else if (scrolledDistance < -HIDE_THRESHOLD && !controlsVisible) {
			onShow();
			LogUtils.d(TAG, "scrolledDistance:" + scrolledDistance + "onShow()");
			controlsVisible = true;
			scrolledDistance = 0;
		}
		if ((controlsVisible && dy > 0) || (!controlsVisible && dy < 0)) {
			scrolledDistance += dy;
		}
	}

	public abstract void onHide();

	public abstract void onShow();
}
