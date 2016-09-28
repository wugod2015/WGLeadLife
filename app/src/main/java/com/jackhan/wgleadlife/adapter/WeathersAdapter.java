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
import com.jackhan.wgleadlife.bean.Weather;

public class WeathersAdapter extends BaseRecyclerAdapter {

	public WeathersAdapter(Context context, List<?> list, View headView) {
		super(context, list, headView);
		// TODO Auto-generated constructor stub
	}

	public WeathersAdapter(Context context, List<?> list) {
		super(context, list);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onBindViewHolderItem(ViewHolder viewHolder, int position) {
		// TODO Auto-generated method stub
		WeatherHolder holder = (WeatherHolder) viewHolder;

		Weather item = (Weather) list.get(position);

		holder.dateText.setText(item.getDate());
		holder.weatherText.setText(item.getWeather());
		holder.windText.setText(item.getWind());
		holder.temperatureText.setText(item.getTemperature());

		Glide.with(context).load(item.getDayPictureUrl()).crossFade()
				.into(holder.dayPicture);
	}

	@Override
	protected ViewHolder onCreateViewHolderItem(ViewGroup parent, int viewType) {
		// TODO Auto-generated method stub
		WeatherHolder holder = new WeatherHolder(LayoutInflater.from(context)
				.inflate(R.layout.item_weathers, parent, false));
		return holder;
	}

	public class WeatherHolder extends ViewHolder {

		TextView dateText, weatherText, windText, temperatureText;
		ImageView dayPicture;

		public WeatherHolder(View view) {
			super(view);
			// TODO Auto-generated constructor stub
			dateText = (TextView) view.findViewById(R.id.date);
			weatherText = (TextView) view.findViewById(R.id.weather);
			windText = (TextView) view.findViewById(R.id.wind);
			temperatureText = (TextView) view.findViewById(R.id.temperature);
			dayPicture = (ImageView) view.findViewById(R.id.dayPicture);

		}

	}
}
