package com.jackhan.wgleadlife.utils;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;

import com.jackhan.wgleadlife.BaseApplication;

@SuppressLint({ "InlinedApi", "NewApi" })
public class DownLoadUtils {
	final static String TAG = "DownLoadUtils";
	final static String ACTION = "DownLoadUtils";
	static DownLoadUtils mDownLoadUtils;
	DownloadManager downloadManager;

	private DownLoadUtils() {
		// TODO Auto-generated constructor stub
		downloadManager = (DownloadManager) BaseApplication.getInstance()
				.getSystemService(Context.DOWNLOAD_SERVICE);
	}

	public static DownLoadUtils getInstance() {
		// TODO Auto-generated method stub

		if (mDownLoadUtils == null)
			mDownLoadUtils = new DownLoadUtils();
		return mDownLoadUtils;
	}

	public long DownLoadFile(String uriString, boolean isWifi) {
		// TODO Auto-generated method stub

		Uri uri = Uri.parse(uriString);
		Request request = new Request(uri);
		if (isWifi)
			request.setAllowedNetworkTypes(Request.NETWORK_WIFI);
		request.setTitle(BaseApplication.getInstance().getApplicationContext()
				.getString(com.jackhan.wgleadlife.R.string.app_name));
		request.setDescription("正在下载" + uri.getLastPathSegment());
		request.setDestinationInExternalFilesDir(BaseApplication.getInstance(),
				Environment.DIRECTORY_DOWNLOADS, uri.getLastPathSegment());
		request.setNotificationVisibility(Request.VISIBILITY_VISIBLE);
		// request.setVisibleInDownloadsUi(true);

		String fileLocalUri = Environment.getDataDirectory()+Environment.DIRECTORY_DOWNLOADS
				+ uri.getLastPathSegment();

		LogUtils.i(TAG, "getExternalStorageState："+Environment.getExternalStorageState());
		LogUtils.i(TAG, "getDataDirectory："+Environment.getDataDirectory());
		LogUtils.i(TAG, "getDownloadCacheDirectory："+Environment.getDownloadCacheDirectory());
		LogUtils.i(TAG, "getExternalStorageDirectory："+Environment.getExternalStorageDirectory());
		LogUtils.i(TAG, "getRootDirectory："+Environment.getRootDirectory());
		LogUtils.d(TAG, "fileLocalUri：" + fileLocalUri);
		if (FileUtils.isFileExist(fileLocalUri)) {
			LogUtils.d(TAG, "isFileExist：" + true);
		} else {

			LogUtils.d(TAG, "isFileExist：" + false);

		}

		//return -1;
		 return downloadManager.enqueue(request);
	}

	/**
	 * 获取文件保存的路径
	 * 
	 * @param downloadId
	 *            an ID for the download, unique across the system. This ID is
	 *            used to make future calls related to this download.
	 * @return file path
	 * @see FileDownloadManager#getDownloadUri(long)
	 */
	public String getDownloadPath(long downloadId) {
		DownloadManager.Query query = new DownloadManager.Query()
				.setFilterById(downloadId);
		Cursor c = downloadManager.query(query);
		if (c != null) {
			try {
				if (c.moveToFirst()) {
					return c.getString(c
							.getColumnIndexOrThrow(DownloadManager.COLUMN_LOCAL_URI));
				}
			} finally {
				c.close();
			}
		}
		return null;
	}

	/**
	 * 获取保存文件的地址
	 * 
	 * @param downloadId
	 *            an ID for the download, unique across the system. This ID is
	 *            used to make future calls related to this download.
	 * @see FileDownloadManager#getDownloadPath(long)
	 */
	public Uri getDownloadUri(long downloadId) {
		return downloadManager.getUriForDownloadedFile(downloadId);
	}

	public DownloadManager getDm() {
		return downloadManager;
	}

	/**
	 * 获取下载状态
	 * 
	 * @param downloadId
	 *            an ID for the download, unique across the system. This ID is
	 *            used to make future calls related to this download.
	 * @return int
	 * @see DownloadManager#STATUS_PENDING
	 * @see DownloadManager#STATUS_PAUSED
	 * @see DownloadManager#STATUS_RUNNING
	 * @see DownloadManager#STATUS_SUCCESSFUL
	 * @see DownloadManager#STATUS_FAILED
	 */
	public int getDownloadStatus(long downloadId) {
		DownloadManager.Query query = new DownloadManager.Query()
				.setFilterById(downloadId);
		Cursor c = downloadManager.query(query);
		if (c != null) {
			try {
				if (c.moveToFirst()) {
					return c.getInt(c
							.getColumnIndexOrThrow(DownloadManager.COLUMN_STATUS));

				}
			} finally {
				c.close();
			}
		}
		return -1;
	}
}
