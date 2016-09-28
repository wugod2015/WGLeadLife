package com.jackhan.wgleadlife.fragment;

import com.jackhan.wgleadlife.ActivityUtils;
import com.jackhan.wgleadlife.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainDrawerMenuFragment extends BaseFragment implements
		OnItemClickListener {

	ListView listView;

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater
				.inflate(R.layout.fragment_drawer_menu, container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	protected void initView(View view) {
		// TODO Auto-generated method stub

		String[] titles = new String[] {
				getString(R.string.title_activity_movies),
				getString(R.string.title_activity_weathers) ,
				getString(R.string.title_activity_photo_wall)};
		listView = (ListView) view.findViewById(R.id.listView);
		ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext,
				R.layout.item_main_drawer_menu_fragment, R.id.title, titles);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		switch (position) {
		case 0:

			ActivityUtils.startMoviesActivity(mContext);
			// RxBus.getDefault().post(new RxEvent(0, "RxBus", null));
			break;

		case 1:

			ActivityUtils.startWeathersActivity(mContext);
			// RxBus.getDefault().post(new RxEvent(0, "RxBus", null));
			break;
		case 2:

			ActivityUtils.startPhotoWallActivity(mContext);
			// RxBus.getDefault().post(new RxEvent(0, "RxBus", null));
			break;
		default:
			break;
		}
	}
}
