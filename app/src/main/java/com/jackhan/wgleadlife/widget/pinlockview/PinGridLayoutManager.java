package com.jackhan.wgleadlife.widget.pinlockview;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Recycler;
import android.support.v7.widget.RecyclerView.State;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class PinGridLayoutManager extends GridLayoutManager {

	public PinGridLayoutManager(Context context, int spanCount,
			int orientation, boolean reverseLayout) {
		super(context, spanCount, orientation, reverseLayout);
		// TODO Auto-generated constructor stub
	}

	public PinGridLayoutManager(Context context, int spanCount) {
		super(context, spanCount);
		// TODO Auto-generated constructor stub
	}

	private int[] mMeasuredDimension = new int[2];

	@Override
	public void onMeasure(Recycler recycler, State state, int widthSpec,
			int heightSpec) {
		// TODO Auto-generated method stub
		final int widthMode = View.MeasureSpec.getMode(widthSpec);
		final int heightMode = View.MeasureSpec.getMode(heightSpec);
		final int widthSize = View.MeasureSpec.getSize(widthSpec);
		final int heightSize = View.MeasureSpec.getSize(heightSpec);
		int width = 0;
		int height = 0;

		Log.d("", "state:" + state.toString());

		measureScrapChild(recycler, 0, widthSpec,
				View.MeasureSpec.makeMeasureSpec(0,
						View.MeasureSpec.UNSPECIFIED), mMeasuredDimension);

		height = height + mMeasuredDimension[1] * 3;

		measureScrapChild(recycler, getItemCount() - 1, widthSpec,
				View.MeasureSpec.makeMeasureSpec(getItemCount() - 1,
						View.MeasureSpec.UNSPECIFIED), mMeasuredDimension);
		height = height + mMeasuredDimension[1];
		height=height+getPaddingBottom()+getPaddingTop();

		switch (heightMode) {
		case View.MeasureSpec.EXACTLY:
			height = heightSize;
		case View.MeasureSpec.AT_MOST:
		case View.MeasureSpec.UNSPECIFIED:
		}
		setMeasuredDimension(widthSpec, height);
	}

	private void measureScrapChild(RecyclerView.Recycler recycler,
			int position, int widthSpec, int heightSpec, int[] measuredDimension) {
		View view = recycler.getViewForPosition(position);

		// For adding Item Decor Insets to view
		// super.measureChildWithMargins(view, 0, 0);

		if (view != null) {
			RecyclerView.LayoutParams p = (RecyclerView.LayoutParams) view
					.getLayoutParams();
			int childHeightSpec = ViewGroup.getChildMeasureSpec(heightSpec,
					getPaddingTop() + getPaddingBottom(), p.height);
			view.measure(widthSpec, childHeightSpec);
			measuredDimension[0] = view.getMeasuredWidth() + p.leftMargin
					+ p.rightMargin;
			measuredDimension[1] = view.getMeasuredHeight() + p.bottomMargin
					+ p.topMargin;
			recycler.recycleView(view);
		}
	}

	@Override
	public void onLayoutChildren(RecyclerView.Recycler recycler,
			RecyclerView.State state) {
		// Logger.e("SyLinearLayoutManager state:" + state.toString());
		super.onLayoutChildren(recycler, state);
	}
}
