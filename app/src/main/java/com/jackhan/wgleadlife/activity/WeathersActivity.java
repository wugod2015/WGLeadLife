package com.jackhan.wgleadlife.activity;

import java.util.Locale;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.jackhan.wgleadlife.R;
import com.jackhan.wgleadlife.fragment.WeathersFragment;

public class WeathersActivity extends BaseActivity {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a {@link FragmentPagerAdapter}
	 * derivative, which will keep every loaded fragment in memory. If this
	 * becomes too memory intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView_BackToolbar(R.layout.activity_weathers);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.weather, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a PlaceholderFragment (defined as a static inner class
			// below).
			return WeathersFragment.newInstance(locations[position]);
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return locations.length;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			return locations[position].toUpperCase(l);
		}
	}

	TabLayout mTabLayout;

	String[] locations;

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		mTabLayout = (TabLayout) findViewById(R.id.tabs);
		locations = getResources().getStringArray(R.array.weather_locations);
		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		for (int i = 0; i < locations.length; i++) {
			mTabLayout.addTab(mTabLayout.newTab().setText(locations[i]));
		}

		mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
		mTabLayout
				.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(
						mViewPager));
		mViewPager
				.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(
						mTabLayout));
	}

}
