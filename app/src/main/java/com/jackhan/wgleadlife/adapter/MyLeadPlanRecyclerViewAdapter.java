package com.jackhan.wgleadlife.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jackhan.wgleadlife.R;
import com.jackhan.wgleadlife.bean.LeadPlan;
import com.jackhan.wgleadlife.utils.DateUtils;

import java.util.List;

public class MyLeadPlanRecyclerViewAdapter extends BaseRecyclerAdapter {


    public MyLeadPlanRecyclerViewAdapter(Context context, List<?> list) {
        super(context, list);
    }

    public MyLeadPlanRecyclerViewAdapter(Context context, List<?> list, View headView) {
        super(context, list, headView);
    }

    @Override
    protected void onBindViewHolderItem(RecyclerView.ViewHolder viewHolder, int position) {

        ViewHolder holder = (ViewHolder) viewHolder;

        LeadPlan item = (LeadPlan) list.get(position);
        holder.mTimeView.setText(DateUtils.translateDateTime(item.getCreate_date().getTime()));
        holder.mContentView.setText(item.getContent());
    }

    @Override
    protected RecyclerView.ViewHolder onCreateViewHolderItem(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_leadplan, parent, false));
        return holder;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTimeView;
        public final TextView mContentView;
        public LeadPlan mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTimeView = (TextView) view.findViewById(R.id.time);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTimeView.getText() + " '" + mContentView.getText() + "'";
        }
    }
}
