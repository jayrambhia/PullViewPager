package com.fenchtose.pullviewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by jay on 3/8/15.
 */
public class CustomViewPager extends ViewPager {

    private static final String TAG = "CustomViewPager";

    private float pX;
    private float pXOld;

    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        addOnPageChangeListener(new PageChangeListener());
        setPageTransformer(false, new StickyPagerTransformer());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (pX < 0.28 || pX > 0.64) {
            pXOld = pX;
            Log.i(TAG, "pXOld: " + pXOld);
            Log.i(TAG, "pX: " + pX);
            return super.onTouchEvent(event);
        }

        Log.i(TAG, "pXOld: " + pXOld);
        Log.i(TAG, "pX: " + pX);
        Log.i(TAG, "event: " + event.getAction());

        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:

                Log.i(TAG, "diff: " + Math.abs(pX - pXOld));

                if (pXOld != 0f && Math.abs(pX - pXOld) > 0.01 ) {
                    pXOld = pX;
                    Log.i(TAG, "pXOld: " + pXOld);
                    Log.i(TAG, "pX: " + pX);
                    return super.onTouchEvent(event);
                }
                pXOld = pX;
                break;
            default:
                pXOld = pX;
                return super.onTouchEvent(event);
        }
        return false;
    }

    public class PageChangeListener implements OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            pX = positionOffset;
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    public class StickyPagerTransformer implements PageTransformer {

        private float textSize = 36;

        @Override
        public void transformPage(View page, float position) {

            TextView headerView = (TextView)page.findViewById(R.id.header_view);

            if (position < -1) { // [-Infinity, -1)
                // This page is way off-screen to the left.
            } else if (position <= 1) { // [-1, 1]
                if (position <= 0) {
                    page.setTranslationX(-(position * page.getWidth()));
                    headerView.setTranslationX(position * page.getWidth() * 0.6f);
                    headerView.setTextSize(36 + (position * 16));
                } else {
                    headerView.setTranslationX(-position * page.getWidth() * 0.4f);
                    headerView.setTextSize(36 + (-position * 16));
                }

            } else { // (1, +Infinity]
                // This page is way off-screen to the right.
            }
        }
    }
}
