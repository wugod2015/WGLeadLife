package com.jackhan.wgleadlife.fragment;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jackhan.wgleadlife.R;
import com.jackhan.wgleadlife.adapter.WeathersAdapter;
import com.jackhan.wgleadlife.bean.Weather;
import com.jackhan.wgleadlife.bean.WeatherResult;
import com.jackhan.wgleadlife.db.DBHelper;
import com.jackhan.wgleadlife.db.WeatherResultDao;
import com.jackhan.wgleadlife.server.ServerApi;
import com.jackhan.wgleadlife.subscriber.DataResultSubscriber;

public class WeathersFragment extends BaseFragment implements OnRefreshListener {
	private static final String TAG = "WeathersFragment";
	private static final String ARG_LOCATION = "location";

	public static WeathersFragment newInstance(String location) {
		WeathersFragment fragment = new WeathersFragment();
		Bundle args = new Bundle();
		args.putString(ARG_LOCATION, location);
		fragment.setArguments(args);
		return fragment;
	}

	String location;

	SwipeRefreshLayout swipeRefreshLayout;

	RecyclerView recyclerView;
	List<Weather> weathers;
	WeathersAdapter adapter;

	WeatherResultDao weatherResultDao;

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_list_recycler_view,
				container, false);
		return rootView;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		location = getArguments().getString(ARG_LOCATION);
		weatherResultDao = DBHelper.getDaoMaster(mContext).newSession()
				.getWeatherResultDao();
		getWeathers(location);
	}

	private void getWeathers(String location) {
		// TODO Auto-generated method stub

		swipeRefreshLayout.setRefreshing(true);

		ServerApi.getWeathers(location).subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new DataResultSubscriber<WeatherResult>() {

					@Override
					public void onResult(String msg, WeatherResult t) {

						// TODO Auto-generated method stub

						swipeRefreshLayout.setRefreshing(false);

						weathers.clear();
						weathers.addAll(t.getWeather_data());
						adapter.notifyDataSetChanged();
					}
				});
	}

	@Override
	protected void initView(View view) {
		// TODO Auto-generated method stub
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
		weathers = new ArrayList<>();
		adapter = new WeathersAdapter(getActivity(), weathers);
		recyclerView.setAdapter(adapter);

	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub

		getWeathers(location);
	}

}
