package com.jackhan.wgleadlife.activity;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnCloseListener;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.jackhan.wgleadlife.ActivityUtils;
import com.jackhan.wgleadlife.R;
import com.jackhan.wgleadlife.adapter.MoviesAdapter;
import com.jackhan.wgleadlife.bean.Movie;
import com.jackhan.wgleadlife.bean.MovieResult;
import com.jackhan.wgleadlife.db.DBHelper;
import com.jackhan.wgleadlife.db.MovieDao;
import com.jackhan.wgleadlife.db.MovieDao.Properties;
import com.jackhan.wgleadlife.listener.HidingScrollListener;
import com.jackhan.wgleadlife.server.ServerApi;
import com.jackhan.wgleadlife.subscriber.DataResultSubscriber;
import com.jackhan.wgleadlife.utils.LogUtils;

@SuppressLint("NewApi")
public class MoviesActivity extends BaseActivity implements OnRefreshListener,
        OnQueryTextListener, OnCloseListener {
    private static final String TAG = "MoviesActivity";
    SwipeRefreshLayout swipeRefreshLayout;

    RecyclerView recyclerView;
    List<Movie> movies;
    MoviesAdapter adapter;

    MovieDao movieDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView_BackToolbar(R.layout.fragment_list_recycler_view);
        movieDao = DBHelper.getDaoMaster(this).newSession().getMovieDao();
        getMovies(null);
        // RxBus.getDefault().post(new RxEvent(0, "movies", null));
    }

    @Override
    protected void onSetContentContainer(View contentView) {
        // TODO Auto-generated method stub
        mTopBarContainer.addView(contentView, 0);
    }

    private void getMovies(String location) {
        // TODO Auto-generated method stub

        swipeRefreshLayout.setRefreshing(true);

		/*
         * movies.clear(); movies.addAll(movieDao.queryBuilder().list());
		 * adapter.notifyDataSetChanged();
		 */
        getMoviesByJson(location);
        // getMoviesByXml(location);

    }


    private void getMoviesByJson(String location) {
        // TODO Auto-generated method stub
        ServerApi
                .getMovies(location)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new DataResultSubscriber<MovieResult<List<Movie>>>() {

                            @Override
                            public void onResult(String msg,
                                                 MovieResult<List<Movie>> t) {
                                // TODO Auto-generated method stub

                                swipeRefreshLayout.setRefreshing(false);
                                movies.clear();
                                movies.addAll(t.movie);
                                adapter.notifyDataSetChanged();

                                movieDao.deleteAll();
                                movieDao.insertOrReplaceInTx(movies);

                            }

                            @Override
                            protected void onErrorResult(String errorStr) {
                                // TODO Auto-generated method stub
                                super.onErrorResult(errorStr);
                                swipeRefreshLayout.setRefreshing(false);
                            }
                        });
    }

    @Override
    protected void initView() {
        // TODO Auto-generated method stub
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mToolBar.inflateMenu(R.menu.list_menu);
        SearchView searchView = (SearchView) mToolBar
                .findViewById(R.id.action_search);
        searchView.setQueryHint("电影");
        searchView.setOnQueryTextListener(this);
        searchView.setOnCloseListener(this);
        // searchView.setSubmitButtonEnabled(true);

        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.GREEN,
                Color.BLUE);
        swipeRefreshLayout.setOnRefreshListener(this);
        // 这句话是为了，第一次进入页面的时候显示加载进度条
        swipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        movies = new ArrayList<>();
        View headView = new View(mContext);
        adapter = new MoviesAdapter(this, movies, headView);
        recyclerView.setAdapter(adapter);
        recyclerView.setOnScrollListener(new HidingScrollListener() {

            @Override
            public void onShow() {
                // TODO Auto-generated method stub
                mToolBar.animate().translationY(0)
                        .setInterpolator(new DecelerateInterpolator(2));

            }

            @Override
            public void onHide() {
                // TODO Auto-generated method stub

                mToolBar.animate().translationY(-mToolBar.getHeight())
                        .setInterpolator(new AccelerateInterpolator(2));
            }
        });
        adapter.setOnItemClickListener(new MoviesAdapter.OnRecyclerViewItemClickListener() {

            @Override
            public void onItemClick(View view, Object item) {
                // TODO Auto-generated method stub
                ActivityUtils.startMovieActivity(mContext, (Movie) item);
            }
        });

        initSpinnerToToolbar();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        // TODO Auto-generated method stub
        super.onWindowFocusChanged(hasFocus);
        ViewGroup.LayoutParams lp = adapter.getHeadView().getLayoutParams();
        if (lp != null) {
            lp.height = mToolBar.getHeight();
            adapter.getHeadView().setLayoutParams(lp);
        }
    }

    private void initSpinnerToToolbar() {
        // TODO Auto-generated method stub

        SpinnerAdapter spinnerAdapter = ArrayAdapter.createFromResource(
                mContext, R.array.movies_spinner,
                android.R.layout.simple_spinner_dropdown_item);
        Spinner navigationSpinner = new Spinner(mContext);
        navigationSpinner
                .setOnItemSelectedListener(new OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position, long id) {
                        // TODO Auto-generated method stub
                        switch (position) {
                            case 0:

                                onRefresh(movieDao.queryBuilder()
                                        .where(Properties.Is_new.eq("1")).list());
                                return;
                            case 1:

                                onRefresh(movieDao.queryBuilder()
                                        .where(Properties.Is_imax.eq("1")).list());
                                return;

                            default:
                                break;
                        }

                        LogUtils.d(TAG, parent.getItemAtPosition(position)
                                .toString());
                        getMovies(parent.getItemAtPosition(position).toString());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        // TODO Auto-generated method stub

                    }
                });
        navigationSpinner.setAdapter(spinnerAdapter);
        navigationSpinner.setSelection(2);
        navigationSpinner.setPadding(8, 5, 8, 5);
        mToolBar.addView(navigationSpinner);
    }

    @Override
    public void onRefresh() {
        // TODO Auto-generated method stub

        getMovies(null);
    }

    private void onRefresh(List<Movie> qMovies) {
        // TODO Auto-generated method stub
        movies.clear();
        movies.addAll(qMovies);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onQueryTextChange(String s) {
        // TODO Auto-generated method stub
        LogUtils.d(TAG, "onQueryTextChange:" + s);
        onRefresh(movieDao.queryBuilder()
                .where(Properties.Movie_name.like("%" + s + "%")).list());

        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        // TODO Auto-generated method stub
        LogUtils.d(TAG, "onQueryTextSubmit:" + s);
        return false;
    }

    @Override
    public boolean onClose() {
        // TODO Auto-generated method stub
        LogUtils.d(TAG, "onClose:");
        onRefresh(movieDao.queryBuilder().list());
        return false;
    }

}
