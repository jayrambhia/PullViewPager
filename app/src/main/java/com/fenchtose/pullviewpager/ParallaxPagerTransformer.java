package com.fenchtose.pullviewpager;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import de.greenrobot.event.EventBus;

/**
 * Created by jay on 3/8/15.
 */
public class ParallaxPagerTransformer implements ViewPager.PageTransformer {

    private static final String TAG = "PagerTransformer";

    public ParallaxPagerTransformer() {
        super();
    }

    @Override
    public void transformPage(View page, float position) {
        int pageWidth = page.getWidth();
        int pageHeight = page.getHeight();

        if (position < -1) { // [-Infinity, -1)
            // This page is way off-screen to the left.
//            page.setTranslationX(-(page.getWidth()));
        } else if (position <= 1) { // [-1, 1]

            if (position <= 0) {
                page.setTranslationX(-(position * pageWidth));
            }

        } else { // (1, +Infinity]
            // This page is way off-screen to the right.
//            page.setTranslationX(page.getWidth());

        }
    }
}
