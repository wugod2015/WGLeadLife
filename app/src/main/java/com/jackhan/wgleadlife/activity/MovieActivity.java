package com.jackhan.wgleadlife.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jackhan.wgleadlife.R;
import com.jackhan.wgleadlife.bean.Movie;

public class MovieActivity extends BaseActivity {

	Movie movie;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView_BackToolbar(R.layout.activity_movie);
		movie = (Movie) getIntent().getSerializableExtra("Movie");
		if (movie == null)
			finish();
		mToolBar.setTitle(movie.getMovie_name());
		initData();
	}

	private TextView movie_name;
	private TextView movie_type;
	private TextView movie_release_date;
	private TextView movie_nation;
	private TextView movie_starring;
	private TextView movie_length;
	private TextView movie_score;
	private TextView movie_director;
	private TextView movie_tags;
	private TextView movie_message;
	private TextView is_imax;
	private TextView is_new;
	private TextView movies_wd;
	private ImageView movie_big_picture;

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		movie_name = (TextView) findViewById(R.id.movie_name);
		movie_type = (TextView) findViewById(R.id.movie_type);
		movie_release_date = (TextView) findViewById(R.id.movie_release_date);
		movie_nation = (TextView) findViewById(R.id.movie_nation);
		movie_starring = (TextView) findViewById(R.id.movie_starring);
		movie_length = (TextView) findViewById(R.id.movie_length);
		movie_score = (TextView) findViewById(R.id.movie_score);
		movie_director = (TextView) findViewById(R.id.movie_director);
		movie_tags = (TextView) findViewById(R.id.movie_tags);
		movie_message = (TextView) findViewById(R.id.movie_message);
		is_imax = (TextView) findViewById(R.id.is_imax);
		is_new = (TextView) findViewById(R.id.is_new);
		movies_wd = (TextView) findViewById(R.id.movies_wd);
		movie_big_picture = (ImageView) findViewById(R.id.movie_big_picture);
	}

	private void initData() {
		// TODO Auto-generated method stub
		movie_name.setText(movie.getMovie_name());
		movie_message.setText(movie.getMovie_message());
		movie_director.setText(movie.getMovie_director());
		movie_length.setText(movie.getMovie_length());
		movie_nation.setText(movie.getMovie_nation());
		movie_score.setText(movie.getMovie_score());
		movie_starring.setText(movie.getMovie_starring());
		movie_tags.setText(movie.getMovie_tags());
		movie_type.setText(movie.getMovie_type());
		movies_wd.setText(movie.getMovies_wd());
		is_imax.setText(movie.getIs_imax());
		is_new.setText(movie.getIs_new());
		movie_release_date.setText(movie.getMovie_release_date() + "上映");
		Glide.with(mContext).load(movie.getMovie_big_picture()).crossFade()
				.into(movie_big_picture);
	}

}
