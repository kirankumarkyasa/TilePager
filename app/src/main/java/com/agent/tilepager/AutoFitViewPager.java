package com.agent.tilepager;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import static com.agent.tilepager.Utils.convertPixelsToDp;

/**
 * Created by KiranKyasa on 20-03-2017.
 */

public class AutoFitViewPager extends ViewPager {

    int height;
    private Context context;

    public AutoFitViewPager(Context context) {
        super(context);
        this.context = context;
    }

    public AutoFitViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {


        for(int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);

            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            int h = child.getMeasuredHeight();
            //if(h > height) height = h + (int) convertPixelsToDp(60,context);
            if(h > height) height = h + 10;
        }

        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
