package com.jackhan.wgleadlife;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.jackhan.wgleadlife.activity.LockActivity;
import com.jackhan.wgleadlife.activity.LoginActivity;
import com.jackhan.wgleadlife.activity.MovieActivity;
import com.jackhan.wgleadlife.activity.MoviesActivity;
import com.jackhan.wgleadlife.activity.PhotoWallActivity;
import com.jackhan.wgleadlife.activity.WeathersActivity;
import com.jackhan.wgleadlife.bean.Movie;
import com.jackhan.wgleadlife.utils.LogUtils;

public class ActivityUtils {

	final static public String TAG = "ActivityUtils";

	final static public int REQUEST_CODE_LOCK = 1;
	final static public int REQUEST_CODE_FILE_SELECT = 2;

	public static void startLoginActivity(Context context) {
		LogUtils.d(TAG, "startLoginActivity");

		Intent intent = new Intent(context, LoginActivity.class);
		context.startActivity(intent);
	}

	public static void startLockActivity(Activity activity, int requestCode) {
		LogUtils.d(TAG, "startLockActivity");

		Intent intent = new Intent(activity, LockActivity.class);
		activity.startActivityForResult(intent, requestCode);
	}

	public static void startMovieActivity(Context context, Movie movie) {
		LogUtils.d(TAG, "startMovieActivity");

		Intent intent = new Intent(context, MovieActivity.class);
		intent.putExtra("Movie", movie);
		context.startActivity(intent);
	}

	public static void startMoviesActivity(Context context) {
		LogUtils.d(TAG, "startMoviesActivity");

		Intent intent = new Intent(context, MoviesActivity.class);
		context.startActivity(intent);
	}

	public static void startWeathersActivity(Context context) {
		LogUtils.d(TAG, "startWeathersActivity");

		Intent intent = new Intent(context, WeathersActivity.class);
		context.startActivity(intent);
	}
	public static void startPhotoWallActivity(Context context) {
		LogUtils.d(TAG, "startPhotoWallActivity");

		Intent intent = new Intent(context, PhotoWallActivity.class);
		context.startActivity(intent);
	}
}
