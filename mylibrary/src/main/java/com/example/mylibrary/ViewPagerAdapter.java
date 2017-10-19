package com.example.mylibrary;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by xiaox on 2017/10/18.
 */

public class ViewPagerAdapter extends PagerAdapter {
    private List<View> mViewList;

    public ViewPagerAdapter(List<View> mViewList) {
        this.mViewList = mViewList;
    }

    public int getCount() {
        return this.mViewList == null ? 0 : this.mViewList.size();
    }

    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) this.mViewList.get(position));
    }

    public Object instantiateItem(ViewGroup container, int position) {
        container.addView((View) this.mViewList.get(position));
        return this.mViewList.get(position);
    }
}
