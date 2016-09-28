package com.jackhan.wgleadlife;

import com.jackhan.wgleadlife.db.DBHelper;

import de.greenrobot.dao.query.QueryBuilder;

import android.app.Application;

public class BaseApplication extends Application {
	static BaseApplication instance;

	public static BaseApplication getInstance() {
		return instance;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		instance = this;
		// 默认使用的高度是设备的可用高度，也就是不包括状态栏和底部的操作栏的，如果你希望拿设备的物理高度进行百分比化
		// AutoLayoutConifg.getInstance().useDeviceSize();
		DBHelper.init(this);
		QueryBuilder.LOG_SQL = true;
		QueryBuilder.LOG_VALUES = true;
	}
}
