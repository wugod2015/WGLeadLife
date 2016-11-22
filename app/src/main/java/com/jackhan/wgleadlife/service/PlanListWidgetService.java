package com.jackhan.wgleadlife.service;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.jackhan.wgleadlife.R;
import com.jackhan.wgleadlife.app_widget.AddPlan_AppWidget;
import com.jackhan.wgleadlife.bean.LeadPlan;
import com.jackhan.wgleadlife.control.LeadPlanManager;
import com.jackhan.wgleadlife.utils.LogUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/11/22.
 */

public class PlanListWidgetService extends RemoteViewsService {
    private static final String TAG = "PlanListWidgetService";

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        LogUtils.d(TAG, "onGetViewFactory");
        return new PlansRemoteViewsFactory(this, intent);
    }

    private class PlansRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
        private static final String TAG = "PlansRemoteViewsFactory";

        private Context context;
        private int mAppWidgetId;

        private List<LeadPlan> data;

        /**
         * 构造GridRemoteViewsFactory
         *
         * @author skywang
         */
        public PlansRemoteViewsFactory(Context context, Intent intent) {
            LogUtils.d(TAG, "PlansRemoteViewsFactory");
            this.context = context;
            mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
            LogUtils.d(TAG, "GridRemoteViewsFactory mAppWidgetId:" + mAppWidgetId);
        }

        @Override
        public RemoteViews getViewAt(int position) {

            LogUtils.d(TAG, "GridRemoteViewsFactory getViewAt:" + position);
            // 获取 grid_view_item.xml 对应的RemoteViews
            RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.item_leadplans_aw);

            // 设置 第position位的“视图”的数据
            rv.setTextViewText(R.id.titleTV, data.get(position).getTitle());

            // 设置 第position位的“视图”对应的响应事件
            Intent fillInIntent = new Intent();
            fillInIntent.putExtra(AddPlan_AppWidget.COLLECTION_VIEW_EXTRA, position);
            rv.setOnClickFillInIntent(R.id.titleTV, fillInIntent);

            return rv;
        }

        /**
         * 初始化数据
         */
        private void initData() {
            data = LeadPlanManager.getInstance(context).getLeadPlans();
        }

        @Override
        public void onCreate() {
            LogUtils.d(TAG, "onCreate");
            // 初始化“集合视图”中的数据
            initData();
        }

        @Override
        public int getCount() {
            // 返回“集合视图”中的数据的总数
            return data.size();
        }

        @Override
        public long getItemId(int position) {
            // 返回当前项在“集合视图”中的位置
            return position;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            // 只有一类 GridView
            return 1;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public void onDataSetChanged() {
        }

        @Override
        public void onDestroy() {
            data.clear();
        }
    }
}
