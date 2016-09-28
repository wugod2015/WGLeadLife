package com.jackhan.wgleadlife.adapter;

import java.util.List;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.jackhan.wgleadlife.R;
import com.jackhan.wgleadlife.bean.PhotoWallItem;
import com.jackhan.wgleadlife.utils.PaletteUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class PhotoWallAdapter extends BaseRecyclerAdapter {

	public PhotoWallAdapter(Context context, List<?> list, View headView) {
		super(context, list, headView);
		// TODO Auto-generated constructor stub
	}

	public PhotoWallAdapter(Context context, List<?> list) {
		super(context, list);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onBindViewHolderItem(ViewHolder viewHolder, int position) {
		// TODO Auto-generated method stub
		PhotoWallHolder holder = (PhotoWallHolder) viewHolder;
		PhotoWallItem item = (PhotoWallItem) holder.itemView.getTag();

		holder.text.setText("item" + position);

		 Glide.with(context).load(item.getUrl()).into(holder.img);
		/*if (item.isSizeNull()) {
			Glide.with(context).load(item.getUrl()).asBitmap().fitCenter()
					.placeholder(R.drawable.ic_launcher)
					.into(new DriverViewTarget(holder.img, holder, item));
		} else {

			PhotoWallItem itemTag=(PhotoWallItem)holder.itemView.getTag();
			Glide.with(context).load(item.getUrl()).fitCenter()
					.override(itemTag.getWidth(), itemTag.getHeight())
					.into(holder.img);
			holder.text.setBackgroundColor(itemTag.getTextColor());
		}*/

		/*
		 * if(item.isSizeNull())
		 * 
		 * Glide.with(context).load(item.getUrl()).fitCenter().transform(new
		 * Transform_FitWidth(context,item)).into(holder.img); else
		 * Glide.with(context).load(item.getUrl()).override(item.getWidth(),
		 * item.getHeight()).fitCenter() .into(holder.img);
		 */
	}

	class DriverViewTarget extends BitmapImageViewTarget {
		PhotoWallItem item;
		PhotoWallHolder holder;

		public DriverViewTarget(ImageView view, PhotoWallHolder holder,
				PhotoWallItem item) {
			super(view);
			// TODO Auto-generated constructor stub
			this.holder = holder;
			this.item = item;
		}

		private void setCardViewLayoutParams(int width, int height) {
			ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
			layoutParams.width = width;
			layoutParams.height = height;
			view.setLayoutParams(layoutParams);
		}

		
		@Override
		public void onResourceReady(Bitmap resource,
				GlideAnimation<? super Bitmap> glideAnimation) {
			// TODO Auto-generated method stub
			if (item.isSizeNull()) {
				int viewWidth = view.getWidth();
				float scale = resource.getWidth() / (viewWidth * 1.0f);
				int viewHeight = (int) (resource.getHeight() * scale);
				//setCardViewLayoutParams(viewWidth, viewHeight);
				item.setSize(viewWidth, viewHeight);
			} //else {
				//setCardViewLayoutParams(item.getWidth(), item.getHeight());
			//}
			int textColor = PaletteUtils.getColorByPalette(resource,
					R.color.white);
			item.setTextColor(textColor);

			//holder.text.setBackgroundColor(textColor);
			holder.itemView.setTag(item);
			super.onResourceReady(resource, glideAnimation);
			holder.text.setBackgroundColor(textColor);
		}
	}

	@Override
	protected ViewHolder onCreateViewHolderItem(ViewGroup parent, int viewType) {
		// TODO Auto-generated method stub
		PhotoWallHolder holder = new PhotoWallHolder(LayoutInflater.from(
				context).inflate(R.layout.activity_photo_wall, parent, false));
		return holder;
	}

	class PhotoWallHolder extends ViewHolder {

		CardView cardView;
		ImageView img;
		TextView text;

		public PhotoWallHolder(View v) {
			super(v);
			// TODO Auto-generated constructor stub
			cardView = (CardView) v.findViewById(R.id.card_view);
			img = (ImageView) v.findViewById(R.id.img);
			text = (TextView) v.findViewById(R.id.text);
		}

	}
}
