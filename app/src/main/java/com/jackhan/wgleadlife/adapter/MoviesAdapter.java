package com.jackhan.wgleadlife.adapter;

import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jackhan.wgleadlife.R;
import com.jackhan.wgleadlife.bean.Movie;

public class MoviesAdapter extends BaseRecyclerAdapter{

	
 
	public MoviesAdapter(Context context, List<?> list) {
		super(context, list);
		// TODO Auto-generated constructor stub
	}

	public MoviesAdapter(Context context, List<?> list, View headView) {
		super(context, list, headView);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onBindViewHolderItem(ViewHolder viewHolder, int position) {
		// TODO Auto-generated method stub
		MovieHolder holder = (MovieHolder) viewHolder;

		Movie item = (Movie)list.get(position);
		holder.movie_name.setText(item.getMovie_name());
		holder.movie_message.setText(item.getMovie_message());
		holder.movie_release_date.setText(item.getMovie_release_date() + "上映");
		Glide.with(context).load(item.getMovie_picture())
				.placeholder(R.drawable.ic_launcher).centerCrop().crossFade()
				.into(holder.movie_picture);
		if ("1".equals(item.getIs_new()))
			holder.is_new.setVisibility(View.VISIBLE);
		else
			holder.is_new.setVisibility(View.GONE);
	}

	@Override
	protected ViewHolder onCreateViewHolderItem(ViewGroup parent, int viewType) {
		// TODO Auto-generated method stub
		MovieHolder holder = new MovieHolder(LayoutInflater.from(context)
				.inflate(R.layout.item_movies, parent, false));
		return holder;
	}
	public class MovieHolder extends ViewHolder {

		TextView movie_name;
		TextView movie_release_date;
		TextView movie_message;
		ImageView movie_picture;
		ImageView is_new;

		public MovieHolder(View v) {
			super(v);
			// TODO Auto-generated constructor stub
			movie_name = (TextView) v.findViewById(R.id.movie_name);
			movie_release_date = (TextView) v
					.findViewById(R.id.movie_release_date);
			movie_message = (TextView) v.findViewById(R.id.movie_message);
			movie_picture = (ImageView) v.findViewById(R.id.movie_picture);
			is_new = (ImageView) v.findViewById(R.id.is_new);
		}

	}
}
