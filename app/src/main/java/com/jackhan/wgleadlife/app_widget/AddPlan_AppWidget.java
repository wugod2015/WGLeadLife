package com.jackhan.wgleadlife.app_widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.jackhan.wgleadlife.R;
import com.jackhan.wgleadlife.activity.DialogActivity;
import com.jackhan.wgleadlife.service.PlanListWidgetService;

/**
 * Implementation of App Widget functionality.
 */
public class AddPlan_AppWidget extends AppWidgetProvider {

    RemoteViews remoteViews;

    void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                         int appWidgetId) {

        // Construct the RemoteViews object
        remoteViews = new RemoteViews(context.getPackageName(), R.layout.add_plan__app_widget);
        remoteViews.setImageViewResource(R.id.appLogo, R.mipmap.ic_launcher);
        remoteViews.setImageViewResource(R.id.add_plan, android.R.drawable.ic_input_add);
        Intent intent = new Intent(context, DialogActivity.class);
        intent.putExtra("AppWidgetId", appWidgetId);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        remoteViews.setOnClickPendingIntent(R.id.add_plan, pendingIntent);

        Intent serviceIntent = new Intent(context, PlanListWidgetService.class);
        remoteViews.setRemoteAdapter(R.id.planListView, serviceIntent);
        Intent gridIntent = new Intent();
        gridIntent.setAction("COLLECTION_VIEW_ACTION");
        gridIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        PendingIntent pendingIntentPlans = PendingIntent.getBroadcast(context, 0, gridIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        // 设置intent模板
        remoteViews.setPendingIntentTemplate(R.id.planListView, pendingIntentPlans);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
    }
}

