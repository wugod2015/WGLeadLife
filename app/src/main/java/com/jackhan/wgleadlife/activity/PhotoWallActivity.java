package com.jackhan.wgleadlife.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.jackhan.wgleadlife.R;
import com.jackhan.wgleadlife.adapter.PhotoWallAdapter;
import com.jackhan.wgleadlife.bean.PhotoWallItem;
import com.jackhan.wgleadlife.conf.Constant;

public class PhotoWallActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView_BackToolbar(R.layout.fragment_list_recycler_view);

		getItems();
	}

	RecyclerView recyclerView;
	PhotoWallAdapter adapter;

	List<PhotoWallItem> photoWallItems = new ArrayList<PhotoWallItem>();

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
		recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,
				StaggeredGridLayoutManager.VERTICAL));
		adapter = new PhotoWallAdapter(this, photoWallItems);
		recyclerView.setAdapter(adapter);
	}

	private void getItems() {
		// TODO Auto-generated method stub
		for (String url : Constant.imageUrls) {
			photoWallItems.add(new PhotoWallItem(url, ""));
		}
		adapter.notifyDataSetChanged();
	}
}
