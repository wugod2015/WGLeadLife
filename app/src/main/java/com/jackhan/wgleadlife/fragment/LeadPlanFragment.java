package com.jackhan.wgleadlife.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jackhan.wgleadlife.R;
import com.jackhan.wgleadlife.adapter.BaseRecyclerAdapter;
import com.jackhan.wgleadlife.adapter.MyLeadPlanRecyclerViewAdapter;
import com.jackhan.wgleadlife.bean.LeadPlan;
import com.jackhan.wgleadlife.db.DBHelper;
import com.jackhan.wgleadlife.db.LeadPlanDao;
import com.jackhan.wgleadlife.utils.LogUtils;
import com.jackhan.wgleadlife.utils.rxbus.RxEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class LeadPlanFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, BaseRecyclerAdapter.OnRecyclerItemClickListener, BaseRecyclerAdapter.OnRecyclerItemLongClickListener {
    static String TAG = "LeadPlanFragment";

    private static final String ARG_PLAN_TYPE = "plan-type";
    private int mPlanType = 0;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public LeadPlanFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static LeadPlanFragment newInstance(int planType) {
        LeadPlanFragment fragment = new LeadPlanFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PLAN_TYPE, planType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtils.d(TAG, "onActivityCreated");

        leadPlanDao = DBHelper.getDaoMaster(mContext).newSession().getLeadPlanDao();
        getPlans();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.d(TAG, "onCreate");
        if (getArguments() != null) {
            mPlanType = getArguments().getInt(ARG_PLAN_TYPE);
        }
    }

    SwipeRefreshLayout swipeRefreshLayout;

    RecyclerView recyclerView;
    List<LeadPlan> leadPlanList;

    MyLeadPlanRecyclerViewAdapter adapter;

    LeadPlanDao leadPlanDao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LogUtils.d(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.fragment_list_recycler_view, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout) view
                .findViewById(R.id.swipe_refresh_layout);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.GREEN,
                Color.BLUE);
        swipeRefreshLayout.setOnRefreshListener(this);
        // 这句话是为了，第一次进入页面的时候显示加载进度条
        swipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        leadPlanList = new ArrayList<>();
        adapter = new MyLeadPlanRecyclerViewAdapter(getActivity(), leadPlanList);
        adapter.setOnRecyclerItemClickListener(this);
        adapter.setOnRecyclerItemLongClickListener(this);
        recyclerView.setAdapter(adapter);
        return view;
    }


    public void getPlans() {
        LogUtils.d(TAG, "getPlans()");
        swipeRefreshLayout.setRefreshing(true);

        leadPlanList.clear();
        leadPlanList.addAll(leadPlanDao.queryBuilder().orderDesc(LeadPlanDao.Properties.Create_date).list());

        swipeRefreshLayout.setRefreshing(false);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LogUtils.d(TAG, "onAttach");
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LogUtils.d(TAG, "onDetach");
        mListener = null;
    }

    @Override
    protected void initView(View view) {
        LogUtils.d(TAG, "initView()");

    }

    @Override
    public void onRefresh() {
        getPlans();
    }

    @Override
    public void onRecyclerItemClick(View view, Object item) {

    }

    @Override
    public void onRecyclerItemLongClick(View view, final Object item) {

        confirmOperationDialog(mContext, "删除plan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                leadPlanDao.delete((LeadPlan) item);
                onRefresh();
            }
        });
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(LeadPlan item);
    }

    @Override
    protected void onRxBusCall(RxEvent rxEvent) {
        super.onRxBusCall(rxEvent);
        switch (rxEvent.what) {
            case RxEvent.WHAT_LEADPLAN_ADD:
                onRefresh();
                break;
            default:
                break;
        }
    }
}
