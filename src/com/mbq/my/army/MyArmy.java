package com.mbq.my.army;

import com.mbq.my.army.fragments.AddSoldier;
import com.mbq.my.army.fragments.CommonSense;
import com.mbq.my.army.fragments.DutyRoster;
import com.mbq.my.army.fragments.PhoneRoster;
import com.mbq.my.army.fragments.ToDoList;
import com.mbq.my.army.fragments.Upcoming;
import com.mbq.my.army.fragments.Vehicles;
import com.mbq.my.army.fragments.Welcome;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MyArmy extends FragmentActivity {

	public static class CategoriesFragment extends Fragment {

		public static final String ARG_CATEGORY = "category";

		public CategoriesFragment() {

		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			View rootView = inflater.inflate(R.layout.fragment_categories,
					container, false);

			int i = getArguments().getInt(ARG_CATEGORY);

			String List = getResources()
					.getStringArray(R.array.MenuDrawerStuff)[i];

			getActivity().setTitle(List);

			return rootView;

		}
	}

	public class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			selectItem(position);

			view.isHorizontalFadingEdgeEnabled();
		}
	}

	private static final int PERIOD = 2000;

	Context context;

	// Used later
	Intent intent;

	public ListView mList;

	private long lastPressedTime;

	public static DrawerLayout mDrawerLayout;

	public CharSequence mDrawerTitle;

	public static ActionBarDrawerToggle mDrawerToggle;

	SharedPreferences mPreferences;

	public CharSequence mTitle;

	public ListView mDrawerList;

	private String[] mCategories;

	Fragment one = new Welcome();
	Fragment two = new AddSoldier();
	Fragment three = new DutyRoster();
	Fragment four = new Upcoming();
	Fragment five = new Vehicles();
	Fragment six = new ToDoList();
	Fragment seven = new PhoneRoster();
	Fragment eight = new CommonSense();

	@Override
	public void onConfigurationChanged(Configuration newConfig) {

		super.onConfigurationChanged(newConfig);

		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@SuppressLint("CutPasteId")
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		getActionBar().setHomeButtonEnabled(true);

		getActionBar().setIcon(R.drawable.smallgreen);

		setContentView(R.layout.my_army);
		
		getActionBar().setTitle("MyArmy");

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		mTitle = mDrawerTitle = getTitle();

		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		mCategories = getResources().getStringArray(R.array.MenuDrawerStuff);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		LayoutInflater inflater = getLayoutInflater();

		final ViewGroup header = (ViewGroup) inflater.inflate(R.layout.header,
				mDrawerList, false);
		mDrawerList.addHeaderView(header, null, false);

		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);

		mDrawerList.setAdapter(new ArrayAdapter<String>(this,
				R.layout.drawer_list_item, mCategories));

		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);

		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		mDrawerToggle = new ActionBarDrawerToggle(

		this, mDrawerLayout, R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close)

		{
			@Override
			public void onDrawerClosed(View view) {

				Drawable icon = null;
				getActionBar().setIcon(icon);

				invalidateOptionsMenu();

			}

			@Override
			public void onDrawerOpened(View drawerView) {

				getActionBar().setIcon(null);

				invalidateOptionsMenu();

			}
		};

		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {

			selectItem(0);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
			switch (event.getAction()) {
			case KeyEvent.ACTION_DOWN:
				if (event.getDownTime() - lastPressedTime < PERIOD) {
					finish();
				} else {
					Toast.makeText(getApplicationContext(),
							"Press again to exit.", Toast.LENGTH_SHORT).show();
					lastPressedTime = event.getEventTime();
				}
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if (mDrawerToggle.onOptionsItemSelected(item)) {

			return true;
		}
		switch (item.getItemId()) {

		default:

		}
		;

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onPostCreate(Bundle savedInstanceState) {

		super.onPostCreate(savedInstanceState);

		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {

		return super.onPrepareOptionsMenu(menu);
	}

	public void selectItem(int position) {

		FragmentTransaction ft = getFragmentManager().beginTransaction();
		switch (position) {

		case 0:
			// Header
			break;

		case 1:
			ft.replace(R.id.content_frame, one);
			break;

		case 2:
			ft.replace(R.id.content_frame, two);
			break;

		case 3:
			ft.replace(R.id.content_frame, three);
			break;

		case 4:
			ft.replace(R.id.content_frame, four);
			break;

		case 5:
			ft.replace(R.id.content_frame, five);
			break;
			
		case 6:
			ft.replace(R.id.content_frame, six);
			break;

		case 7:
			ft.replace(R.id.content_frame, seven);
			break;

		case 8:
			ft.replace(R.id.content_frame, eight);
			break;
		}

		ft.commit();

		mDrawerList.setItemChecked(position, true);

		mDrawerLayout.closeDrawer(mDrawerList);
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}
}

