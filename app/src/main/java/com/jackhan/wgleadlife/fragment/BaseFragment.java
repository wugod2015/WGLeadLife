package com.jackhan.wgleadlife.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;

import com.jackhan.wgleadlife.R;
import com.jackhan.wgleadlife.utils.ToastUtils;
import com.jackhan.wgleadlife.utils.rxbus.RxBus;
import com.jackhan.wgleadlife.utils.rxbus.RxEvent;
import com.jackhan.wgleadlife.view.BaseView;

import rx.Subscription;
import rx.functions.Action1;

public abstract class BaseFragment extends Fragment implements BaseView {

    private static final String TAG = "BaseFragment";

    Context mContext;
    Subscription rxSubscription;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        mContext = getActivity();
        onSubscribeRxBus();
        initView(getView());
    }

    protected abstract void initView(View view);

    /**
     * 初始 rxSubscription
     *
     * @author hanhuizhong
     * @date 2016-7-28
     */
    private void onSubscribeRxBus() {
        // TODO Auto-generated method stub
        rxSubscription = RxBus.getDefault().toObservable(RxEvent.class)
                .subscribe(new Action1<RxEvent>() {

                    @Override
                    public void call(RxEvent rxEvent) {
                        // TODO Auto-generated method stub

                        onRxBusCall(rxEvent);
                    }
                });
    }

    /**
     * 监听 rxSubscription 的 call
     *
     * @param rxEvent
     * @author hanhuizhong
     * @date 2016-7-28
     */
    protected void onRxBusCall(RxEvent rxEvent) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
        if (rxSubscription != null && !rxSubscription.isUnsubscribed()) {
            rxSubscription.unsubscribe();
        }
    }

    @Override
    public void onServerFailure() {
        // TODO Auto-generated method stub
        Log.i(TAG, "onServerFailure");
        if (isAdded())
            ToastUtils.showShortToast(getActivity(),
                    getString(R.string.server_failure));

    }

    AlertDialog.Builder confirmOperationBuilder;

    /**
     * 确认操作dialog
     *
     * @param context
     */
    public void confirmOperationDialog(Context context, CharSequence message,
                                       final DialogInterface.OnClickListener listener) {

        if (confirmOperationBuilder == null) {
            confirmOperationBuilder = new AlertDialog.Builder(context);
            confirmOperationBuilder.setTitle("提示")
                    .setNegativeButton("取消", null);
        }
        confirmOperationBuilder.setMessage(message).setPositiveButton("确定", listener).show();
    }
}
