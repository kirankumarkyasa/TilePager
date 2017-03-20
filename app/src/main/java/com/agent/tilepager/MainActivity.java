package com.agent.tilepager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    ViewPager viewpager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewpager = (ViewPager) findViewById(R.id.viewpager);

        //viewpager.setPageMargin((int)Utils.convertPixelsToDp(80f,this));
        viewpager.setClipToPadding(false);
        // set padding manually, the more you set the padding the more you see of prev & next page
        viewpager.setPadding(40, 0, 40, 0);
        // sets a margin b/w individual pages to ensure that there is a gap b/w them
        viewpager.setPageMargin(3);
        viewpager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override public void transformPage(View page, float position) {
                page.setAlpha(1.0f - Math.abs(position * 0.5f));
            }
        });

        viewpager.setAdapter(new CustomPagerAdapter(this));
    }

    private class CustomPagerAdapter extends PagerAdapter {

        int[] mResources = {
                R.drawable.pp1,
                R.drawable.pp2,
                R.drawable.pp3,
                R.drawable.pp4
        };
        Context mContext;
        LayoutInflater mLayoutInflater;

        public CustomPagerAdapter(Context context) {
            mContext = context;
            mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return mResources.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((LinearLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);

            ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
            imageView.setImageResource(mResources[position]);

            container.addView(itemView);

            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);
        }
    }

}
