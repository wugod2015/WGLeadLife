package com.jackhan.wgleadlife.activity;

import rx.Subscription;
import rx.functions.Action1;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;

import com.jackhan.wgleadlife.R;
import com.jackhan.wgleadlife.utils.LogUtils;
import com.jackhan.wgleadlife.utils.ToastUtils;
import com.jackhan.wgleadlife.utils.rxbus.RxBus;
import com.jackhan.wgleadlife.utils.rxbus.RxEvent;
import com.jackhan.wgleadlife.view.BaseView;

@SuppressLint("NewApi")
public abstract class BaseActivity extends AppCompatActivity implements
		BaseView {
	private static final String TAG = "BaseActivity";
	Context mContext;
	Toolbar mToolBar;
	FrameLayout mTopBarContainer;
	FrameLayout mBannerContainer;
	FrameLayout mContentContainer;

	Subscription rxSubscription;

	/**
	 * init View
	 * 
	 * @author hanhuizhong
	 * @date 2016-7-28
	 */
	protected abstract void initView();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		LogUtils.d(TAG, "onCreate");

	}

	/*
	 * 初始 mContext = this
	 * 
	 * 调用 initView();onSubscribeRxBus(); (non-Javadoc)
	 * 
	 * @see android.app.Activity#setContentView(int)
	 */
	@Override
	public void setContentView(int layoutResID) {
		LogUtils.d(TAG, "setContentView");

		super.setContentView(layoutResID);
		mContext = this;
		onSubscribeRxBus();
		initView();

	}

	/**
	 * base模板 带含back ，title的toolbar
	 * 
	 * @author hanhuizhong
	 * @date 2016-7-28
	 * @param layoutResID
	 */
	protected void setContentView_BackToolbar(int layoutResID) {
		// TODO Auto-generated method stub
		LogUtils.d(TAG, "setContentView_BackToolbar");

		initBaseContentView(layoutResID);
		mContext = this;
		onSubscribeRxBus();
		initBackToolBar();
		initView();

	}

	/**
	 * base模板 带toolbar
	 * 
	 * @author hanhuizhong
	 * @date 2016-7-28
	 * @param layoutResID
	 * @param title
	 */
	protected void setContentView_Toolbar(int layoutResID) {
		// TODO Auto-generated method stub
		LogUtils.d(TAG, "setContentView_Toolbar");

		initBaseContentView(layoutResID);
		mContext = this;
		onSubscribeRxBus();
		initView();
	}

	/**
	 * 初始 base模板布局
	 * 
	 * @author hanhuizhong
	 * @date 2016-7-28
	 * @param layoutResID
	 */
	private void initBaseContentView(int layoutResID) {
		// TODO Auto-generated method stub
		super.setContentView(R.layout.activity_base);
		LogUtils.d(TAG, "initBaseContentView");
		mTopBarContainer = (FrameLayout) findViewById(R.id.top_bar_container);
		mBannerContainer = (FrameLayout) findViewById(R.id.banner_container);
		mContentContainer = (FrameLayout) findViewById(R.id.content_container);

		View contentView = getLayoutInflater()
				.inflate(layoutResID, null, false);
		onSetContentContainer(contentView);

		mToolBar = (Toolbar) findViewById(R.id.toolbar);

		mToolBar.setTitle(getTitle().toString());
	}

	/**
	 * contentView container ，默认mContentContainer ； mTopBarContainer 叠层效果
	 * 
	 * @author hanhuizhong
	 * @date 2016-8-8
	 * @param contentView
	 */
	protected void onSetContentContainer(View contentView) {
		// TODO Auto-generated method stub

		mContentContainer.addView(contentView, 0);
	}

	/**
	 * 初始 toolbar的back
	 * 
	 * @author hanhuizhong
	 * @date 2016-7-28
	 */
	protected void initBackToolBar() {
		// TODO Auto-generated method stub
		LogUtils.d(TAG, "initBackToolBar");

		mToolBar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
		mToolBar.setNavigationOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

	}

	/**
	 * 初始 rxSubscription
	 * 
	 * @author hanhuizhong
	 * @date 2016-7-28
	 */
	private void onSubscribeRxBus() {
		// TODO Auto-generated method stub
		rxSubscription = RxBus.getDefault().toObservable(RxEvent.class)
				.subscribe(new Action1<RxEvent>() {

					@Override
					public void call(RxEvent rxEvent) {
						// TODO Auto-generated method stub

						onRxBusCall(rxEvent);
					}
				});
	}

	/**
	 * 监听 rxSubscription 的 call
	 * 
	 * @author hanhuizhong
	 * @date 2016-7-28
	 * @param rxEvent
	 */
	protected void onRxBusCall(RxEvent rxEvent) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onServerFailure() {
		// TODO Auto-generated method stub
		Log.i(TAG, "onServerFailure");
		ToastUtils.showShortToast(this, getString(R.string.server_failure));
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i(TAG, "onDestroy");
		if (rxSubscription != null && !rxSubscription.isUnsubscribed()) {
			rxSubscription.unsubscribe();
		}
	}
}
