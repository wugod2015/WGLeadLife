package com.jackhan.wgleadlife.activity;

import java.io.File;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.LayoutParams;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.jackhan.wgleadlife.ActivityUtils;
import com.jackhan.wgleadlife.R;
import com.jackhan.wgleadlife.fragment.MainDrawerMenuFragment;
import com.jackhan.wgleadlife.server.ServerApi;
import com.jackhan.wgleadlife.utils.DisplayUtils;
import com.jackhan.wgleadlife.utils.DownLoadUtils;
import com.jackhan.wgleadlife.utils.FileUtils;
import com.jackhan.wgleadlife.utils.LogUtils;
import com.jackhan.wgleadlife.utils.ToastUtils;
import com.jackhan.wgleadlife.utils.rxbus.RxEvent;

@SuppressLint("NewApi")
public class MainActivity extends LockableActivity {
	private static final String TAG = "MainActivity";
	TextView namesText;
	Toolbar toolbar;

	public static MainActivity instance;

	DrawerLayout mDrawerLayout;
	private MainDrawerMenuFragment drawerMenuFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main_drawer);

	}

	@Override
	protected void onRxBusCall(RxEvent rxEvent) {
		// TODO Auto-generated method stub
		super.onRxBusCall(rxEvent);
		ToastUtils.showShortToast(mContext, TAG + rxEvent.msg);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		onDrawerSwitch(false);
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub

		instance = this;

		toolbar = (Toolbar) findViewById(R.id.toolbar);
		namesText = (TextView) findViewById(R.id.textView1);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.navigation_drawer);

		drawerMenuFragment = (MainDrawerMenuFragment) getSupportFragmentManager()
				.findFragmentById(R.id.drawer_menu_fragment);
		View view = findViewById(R.id.drawer_menu_fragment);
		LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();

		int wh = DisplayUtils.getScreenW(this);
		layoutParams.width = (wh * 3 / 4);
		LogUtils.i(TAG, "screenWidth:" + wh + "，drawer_menu_width:"
				+ layoutParams.width);
		view.setLayoutParams(layoutParams);

		initToolbar();
	}

	private void initToolbar() {
		// TODO Auto-generated method stub
		setSupportActionBar(toolbar);
		// toolbar.setNavigationIcon(R.drawable.ic_more_vert_grey600_36dp);//
		// 设置导航栏图标
		toolbar.setNavigationIcon(R.drawable.ic_launcher);// 设置app logo
		toolbar.setTitle(R.string.app_name);// 设置主标题
		// toolbar.setSubtitle("Subtitle");// 设置子标题

		// toolbar.inflateMenu(R.menu.base_toolbar_menu);// 设置右上角的填充菜单
		// initShareActionProvider(toolbar.getMenu());

		toolbar.setNavigationOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onDrawerSwitch(true);
			}
		});
	}

	ShareActionProvider shareActionProvider;

	private void initShareActionProvider(Menu menu) {
		// TODO Auto-generated method stub
		LogUtils.d(TAG, "initShareActionProvider");
		MenuItem menuItem = menu.findItem(R.id.action_share);
		shareActionProvider = new ShareActionProvider(mContext);
		MenuItemCompat.setActionProvider(menuItem, shareActionProvider);

		Intent shareIntent = new Intent();
		shareIntent.setAction(Intent.ACTION_SEND);
		shareIntent.putExtra(Intent.EXTRA_TEXT, "Hello World");
		shareIntent.setType("text/plain");
		shareActionProvider.setShareIntent(shareIntent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		LogUtils.d(TAG, "onCreateOptionsMenu");
		getMenuInflater().inflate(R.menu.main, menu);
		initShareActionProvider(menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		LogUtils.d(TAG, "onOptionsItemSelected");
		return super.onOptionsItemSelected(item);
	}

	/**
	 * 选择文件
	 * 
	 * @author hanhuizhong
	 * @date 2016-7-21
	 * @param v
	 */
	public void onSaveFiles(View v) {
		// TODO Auto-generated method stub
		FileUtils.openFileChooser(this, ActivityUtils.REQUEST_CODE_FILE_SELECT);
	}

	/**
	 * 下载
	 * 
	 * @author hanhuizhong
	 * @date 2016-7-21
	 * @param v
	 */
	public void onDownLoadFile(View v) {
		// TODO Auto-generated method stub
		String uriString = "http://www.etotem.cn/app/doctor_1.2.1.apk";
		DownLoadUtils.getInstance().DownLoadFile(uriString, true);

	}

	public void onList(View v) {
		// TODO Auto-generated method stub
		ServerApi.login().subscribeOn(Schedulers.io())
		.observeOn(AndroidSchedulers.mainThread())
		.subscribe(new Subscriber<String>() {

			@Override
			public void onCompleted() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onError(Throwable arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onNext(String arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	public void onLogin(View v) {
		// TODO Auto-generated method stub
		ActivityUtils.startLoginActivity(this);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		switch (requestCode) {
		case ActivityUtils.REQUEST_CODE_FILE_SELECT:
			if (resultCode == RESULT_OK) {
				// Get the Uri of the selected file
				Uri uri = data.getData();
				// String path = FileUtils.getPath(this, uri);
				File imgFile = FileUtils.uri2File(this, uri);
				if (imgFile != null) {
					/*
					 * List<File> files = new ArrayList<File>();
					 * files.add(imgFile); Map<String, String> params = new
					 * HashMap<String, String>(); params.put("schoolid",
					 * "1000000368"); params.put("userid", "1000000110");
					 * params.put("riskTitle", "riskTitle"); params.put("qyid",
					 * "6e12aa379d08422fab0e8266ad40afbf"); params.put("isOpen",
					 * "1"); params.put("noRisk", "0"); params.put("riskDetail",
					 * ""); params.put("coordinate", "");
					 * params.put("receiveUserId", ""); params.put("fillCheck",
					 * ""); GroupApi.saveFiles(this, params, files)
					 * .subscribeOn(Schedulers.io())
					 * .observeOn(AndroidSchedulers.mainThread()) .subscribe(new
					 * BaseSubscriber<BaseDataResult>() {
					 * 
					 * @Override public void onNext(BaseDataResult arg0) { //
					 * TODO Auto-generated method stub
					 * 
					 * ToastUtils.showShortToast( MainActivity.this, arg0.msg);
					 * } });
					 */
				}
			}
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	public void onDrawerSwitch(boolean isCanOpen) {
		// TODO Auto-generated method stub
		if (mDrawerLayout != null /* && drawerMenu != null */) {
			if (mDrawerLayout.isDrawerOpen(Gravity.LEFT))
				mDrawerLayout.closeDrawer(Gravity.LEFT);
			else if (isCanOpen)
				mDrawerLayout.openDrawer(Gravity.LEFT);

		}
	}
}
