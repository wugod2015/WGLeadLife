package com.jackhan.wgleadlife.utils;

/**
 * @author hanhuizhong
 * @date 2016-4-26
 */
public class LogUtils {
	private static boolean debug = true;
	public static int DEBUG = android.util.Log.DEBUG;

	public static void i(String tag, String msg) {
		// TODO Auto-generated method stub
		if (msg == null) {
			msg = "null";
		}
		if (debug)
			android.util.Log.i(tag, msg);
	}

	public static void e(String tag, String msg) {
		// TODO Auto-generated method stub
		if (msg == null) {
			msg = "null";
		}
		if (debug)
			android.util.Log.e(tag, msg);
	}

	public static void e(String tag, String msg, Throwable tr) {
		// TODO Auto-generated method stub
		if (msg == null) {
			msg = "null";
		}
		if (debug)
			android.util.Log.e(tag, msg, tr);
	}

	public static void d(String tag, String msg) {
		// TODO Auto-generated method stub
		if (msg == null) {
			msg = "null";
		}
		if (debug)
			android.util.Log.d(tag, msg);
	}

	public static void v(String tag, String msg) {
		// TODO Auto-generated method stub
		if (msg == null) {
			msg = "null";
		}
		if (debug)
			android.util.Log.v(tag, msg);
	}
	public static void w(String tag, String msg) {
		// TODO Auto-generated method stub
		if (msg == null) {
			msg = "null";
		}
		if (debug)
			android.util.Log.w(tag, msg);
	}
	public static boolean isLoggable(String arg0, int arg1) {
		// TODO Auto-generated method stub
		if (debug)
			return android.util.Log.isLoggable(arg0, arg1);
		else
			return false;
	}
}
