package com.jackhan.wgleadlife.receiver;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.app.DownloadManager.Query;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;

import com.jackhan.wgleadlife.utils.AppUtils;
import com.jackhan.wgleadlife.utils.LogUtils;

@SuppressLint({ "InlinedApi", "NewApi" })
public class DownLoadReceiver extends BroadcastReceiver {
	final static String TAG = "DownLoadReceiver";

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		DownloadManager downloadManager = (DownloadManager) context
				.getSystemService(Context.DOWNLOAD_SERVICE);

		if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(intent.getAction())) {
			LogUtils.d(TAG, "ACTION_DOWNLOAD_COMPLETE  ");
			long downLoad_Id = intent.getLongExtra(
					DownloadManager.EXTRA_DOWNLOAD_ID, -1);
			LogUtils.d(TAG, "DOWNLOAD_ID = " + downLoad_Id);
			Query query = new Query();
			query.setFilterById(downLoad_Id);
			Cursor cursor = downloadManager.query(query);
			if (cursor != null && cursor.moveToFirst()) {
				int fileNameIdx = cursor
						.getColumnIndex(DownloadManager.COLUMN_LOCAL_FILENAME);
				int fileUriIdx = cursor
						.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI);
				String fileNameStr = cursor.getString(fileNameIdx);
				String fileUriStr = cursor.getString(fileUriIdx);
				LogUtils.d(TAG, "fileNameStr = " + fileNameStr);
				LogUtils.d(TAG, "fileUriStr = " + fileUriStr);
				if (fileNameStr.endsWith(".apk")) {
					String mimeType = downloadManager
							.getMimeTypeForDownloadedFile(downLoad_Id);
					LogUtils.d(TAG, "MimeType = " + mimeType);
					Uri uri = /*Uri.parse(fileUriStr+fileNameStr)*/downloadManager
							.getUriForDownloadedFile(downLoad_Id);
					LogUtils.d(TAG, "uri = " + uri);
					AppUtils.installApk(context, uri);
				}
			}

			cursor.close();
		} else if (DownloadManager.ACTION_NOTIFICATION_CLICKED.equals(intent
				.getAction())) {
			LogUtils.d(TAG, "ACTION_NOTIFICATION_CLICKED  ");

		}

	}
}
