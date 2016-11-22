package com.jackhan.wgleadlife.app_widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.jackhan.wgleadlife.R;
import com.jackhan.wgleadlife.activity.DialogActivity;
import com.jackhan.wgleadlife.activity.MainActivity;
import com.jackhan.wgleadlife.service.PlanListWidgetService;
import com.jackhan.wgleadlife.utils.LogUtils;
import com.jackhan.wgleadlife.utils.ToastUtils;

/**
 * Implementation of App Widget functionality.
 */
public class AddPlan_AppWidget extends AppWidgetProvider {
    private static final String TAG = "AddPlan_AppWidget";
    public static final String ACTION_REFRESH_PLANS = "com.jackhan.wgleadlife.ACTION_REFRESH_PLANS";
    public static final String ACTION_PLAN_CLICK = "com.jackhan.wgleadlife.ACTION_PLAN_CLICK";
    public static final String COLLECTION_VIEW_EXTRA = "com.skywang.test.COLLECTION_VIEW_EXTRA";
    RemoteViews remoteViews;

    void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                         int appWidgetId) {

        // Construct the RemoteViews object
        remoteViews = new RemoteViews(context.getPackageName(), R.layout.add_plan__app_widget);
        remoteViews.setImageViewResource(R.id.appLogo, R.mipmap.ic_launcher);
        remoteViews.setImageViewResource(R.id.add_plan, android.R.drawable.ic_input_add);
        Intent intentAdd = new Intent(context, DialogActivity.class);
        intentAdd.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        PendingIntent pendingIntentAdd = PendingIntent.getActivity(context, 0, intentAdd, 0);
        remoteViews.setOnClickPendingIntent(R.id.add_plan, pendingIntentAdd);

        Intent intentLogo = new Intent(context, MainActivity.class);
        PendingIntent pendingIntentLogo = PendingIntent.getActivity(context, 0, intentLogo, 0);
        remoteViews.setOnClickPendingIntent(R.id.appLogo, pendingIntentLogo);

        Intent serviceIntent = new Intent(context, PlanListWidgetService.class);
        remoteViews.setRemoteAdapter(R.id.planListView, serviceIntent);
        Intent intentPlans = new Intent();
        intentPlans.setAction(ACTION_PLAN_CLICK);
        intentPlans.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        PendingIntent pendingIntentPlans = PendingIntent.getBroadcast(context, 0, intentPlans, PendingIntent.FLAG_UPDATE_CURRENT);
        // 设置intent模板
        remoteViews.setPendingIntentTemplate(R.id.planListView, pendingIntentPlans);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        LogUtils.d(TAG, "onUpdate");
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
        LogUtils.d(TAG, "onEnabled");
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
        LogUtils.d(TAG, "onDisabled");
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        LogUtils.d(TAG, "onReceive");
        String action = intent.getAction();
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

        if (action.equals(ACTION_PLAN_CLICK)) {
            // 接受“gridview”的点击事件的广播
            int appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
            int viewIndex = intent.getIntExtra(COLLECTION_VIEW_EXTRA, 0);
            ToastUtils.showShortToast(context, "Touched view " + viewIndex);
        } else if (action.equals(ACTION_REFRESH_PLANS)) {
            // 接受“bt_refresh”的点击事件的广播
            ToastUtils.showShortToast(context, "Click Button");
        } else if (action.equals(AppWidgetManager.ACTION_APPWIDGET_OPTIONS_CHANGED)) {

        }
        super.onReceive(context, intent);
    }
}

