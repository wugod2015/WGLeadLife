package com.jackhan.wgleadlife.db;

import android.content.Context;

import com.jackhan.wgleadlife.db.DaoMaster.OpenHelper;

public class DBHelper {
	private static DBHelper instance;
	private static Context mContext;

	private static DaoMaster daoMaster;
	private static DaoSession daoSession;
	private MovieDao movieDao;

	/**
	 * 取得DaoMaster
	 * 
	 * @param context
	 * @return
	 */
	public static DaoMaster getDaoMaster(Context context) {
		if (daoMaster == null) {
			OpenHelper helper = new DaoMaster.DevOpenHelper(context,
					"im-db.db", null);
			daoMaster = new DaoMaster(helper.getWritableDatabase());
		}
		return daoMaster;
	}

	/**
	 * 取得DaoSession
	 * 
	 * @param context
	 * @return
	 */
	public static DaoSession getDaoSession(Context context) {
		if (daoSession == null) {
			if (daoMaster == null) {
				daoMaster = getDaoMaster(context);
			}
			daoSession = daoMaster.newSession();
		}
		return daoSession;
	}

	private DBHelper() {
	}

	public static void init(Context context) {
		mContext = context;
		instance = new DBHelper();
		// 数据库对象
		DaoSession daoSession = getDaoSession(mContext);
		instance.setMovieDao(daoSession.getMovieDao());

	}

	private void setMovieDao(MovieDao movieDao) {
		this.movieDao = movieDao;
	}

	public MovieDao getmovieDao() {
		return movieDao;
	}

	public static DBHelper getInstance() {
		return instance;
	}

}
