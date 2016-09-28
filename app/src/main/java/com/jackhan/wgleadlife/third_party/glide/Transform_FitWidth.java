package com.jackhan.wgleadlife.third_party.glide;

import android.content.Context;
import android.graphics.Bitmap;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.jackhan.wgleadlife.bean.PhotoWallItem;
import com.jackhan.wgleadlife.utils.BitmapUtil;

public class Transform_FitWidth extends BitmapTransformation {
	PhotoWallItem item;

	public Transform_FitWidth(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public Transform_FitWidth(Context context, PhotoWallItem item) {
		// TODO Auto-generated constructor stub
		this(context);
		this.item = item;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return getClass().getName();
	}

	@Override
	protected Bitmap transform(BitmapPool pool, Bitmap toTransform,
			int outWidth, int outHeight) {
		// TODO Auto-generated method stub
		item.setSize(outWidth, outHeight);
		Bitmap result= BitmapUtil.zoom(toTransform, outWidth, outHeight);
		return result;
	}

}
