package com.fenchtose.pullviewpager;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

import de.greenrobot.event.EventBus;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_layout);
        CustomViewPager viewPager = (CustomViewPager)findViewById(R.id.viewpager);
        viewPager.setAdapter(new ScreenSlidePagerAdapter(getFragmentManager(), 8));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        private final int NUM_PAGES;
        private Calendar mCal;
        private Locale locale = Locale.getDefault();
        private float width = 0.8f;

        public ScreenSlidePagerAdapter(FragmentManager fm, int pages) {
            super(fm);
            NUM_PAGES = pages;
            mCal = Calendar.getInstance();
            mCal.add(Calendar.DATE, -8);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = SampleFragment.newInstance();
            Bundle args = new Bundle();
            Calendar calendar = (Calendar)mCal.clone();
            calendar.add(Calendar.DATE, position);
            args.putInt("day", calendar.get(Calendar.DATE));
            args.putString("month", calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, locale));
            args.putInt("bike", new Random().nextInt(30) + 30);
            args.putInt("walk", new Random().nextInt(30) + 30);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        @Override
        public float getPageWidth(int position) {
            return width;
        }

    }
}
