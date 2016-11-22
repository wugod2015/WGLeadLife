package com.jackhan.wgleadlife.control;

import android.content.Context;
import android.text.TextUtils;

import com.jackhan.wgleadlife.bean.LeadPlan;
import com.jackhan.wgleadlife.db.DBHelper;
import com.jackhan.wgleadlife.db.LeadPlanDao;
import com.jackhan.wgleadlife.utils.LogUtils;
import com.jackhan.wgleadlife.utils.ToastUtils;
import com.jackhan.wgleadlife.utils.rxbus.RxBus;
import com.jackhan.wgleadlife.utils.rxbus.RxEvent;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/11/22.
 */

public class LeadPlanManager {
    private static final String TAG = "LeadPlanManager";
    LeadPlanDao leadPlanDao;
    static LeadPlanManager leadPlanManager;

    private LeadPlanManager() {
    }

    public static LeadPlanManager getInstance(Context context) {

        if (leadPlanManager == null) {
            leadPlanManager = new LeadPlanManager();
            leadPlanManager.leadPlanDao = DBHelper.getDaoMaster(context).newSession().getLeadPlanDao();
        }

        return leadPlanManager;
    }

    /**
     * LeadPlan Title最大长度
     */
    public static final int MAX_TITLE_LENGTH = 15;

    /**
     * 添加LeadPlan
     *
     * @param title
     * @param content
     */
    public void addPlan(String title, String content) {
        LogUtils.d(TAG, "addPlan");
        if (TextUtils.isEmpty(title) && TextUtils.isEmpty(content)) {
            ToastUtils.showShortToast("不能为空");
            return;
        }
        if (TextUtils.isEmpty(title)) {
            title = content.length() > MAX_TITLE_LENGTH ? content.substring(0, MAX_TITLE_LENGTH - 1) : content;
        }
        LeadPlan leadPlan = new LeadPlan(System.currentTimeMillis() + "", title, content, new Date());
        Long i = leadPlanDao.insert(leadPlan);
        if (i != -1) {
            ToastUtils.showShortToast("Success");
            RxBus.getDefault().post(new RxEvent(RxEvent.WHAT_LEADPLAN_ADD));
        } else {
            ToastUtils.showShortToast("Fail");
        }
    }

    public List<LeadPlan> getLeadPlans() {
        LogUtils.d(TAG, "getLeadPlans");
        return leadPlanDao.queryBuilder().list();
    }
}
