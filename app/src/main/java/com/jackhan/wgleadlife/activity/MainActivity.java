package com.jackhan.wgleadlife.activity;

import java.io.File;
import java.util.Date;

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
import com.jackhan.wgleadlife.bean.LeadPlan;
import com.jackhan.wgleadlife.db.DBHelper;
import com.jackhan.wgleadlife.db.LeadPlanDao;
import com.jackhan.wgleadlife.fragment.AddPlanDialog;
import com.jackhan.wgleadlife.fragment.MainDrawerMenuFragment;
import com.jackhan.wgleadlife.server.ServerApi;
import com.jackhan.wgleadlife.utils.DateUtils;
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
        switch (item.getItemId()) {
            case R.string.add_plan:

                showAddPlanDialog();
                break;
            case R.string.add_record:

                addRecord();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showAddPlanDialog() {

        AddPlanDialog addPlanDialog = new AddPlanDialog();
        addPlanDialog.setOnAddPlanClickListener(new AddPlanDialog.OnAddPlanClickListener() {
            @Override
            public void onAdd(String title, String content) {

                addPlan(title, content);
            }
        });
        addPlanDialog.show(getSupportFragmentManager(), "addPlanDialog");
    }

    public void addPlan(String title, String content) {
        LeadPlanDao leadPlanDao = DBHelper.getDaoMaster(mContext).newSession().getLeadPlanDao();
        LeadPlan leadPlan = new LeadPlan(System.currentTimeMillis() + "", title, content, new Date());
        leadPlanDao.insert(leadPlan);
    }

    public void addRecord() {

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
