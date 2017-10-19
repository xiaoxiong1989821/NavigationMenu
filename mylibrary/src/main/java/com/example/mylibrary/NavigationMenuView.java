package com.example.mylibrary;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaox on 2017/10/18.
 */

public class NavigationMenuView extends LinearLayout {
    private Context context;
    private ViewPager mPager;
    private LinearLayout mLlDot;
    private List<View> mPagerList;
    private int pageCount;
    private int pageSize = 5;
    private int curIndex = 0;

    public NavigationMenuView(Context context) {
        super(context);
        initView(context);
    }

    public NavigationMenuView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;
        View root = View.inflate(context, R.layout.navigation_menu_layout, null);
        this.mPager = (ViewPager) root.findViewById(R.id.viewpager);
        this.mLlDot = (LinearLayout) root.findViewById(R.id.ll_dot);
        this.addView(root);
    }

    public void setData(final List<? extends Object> mDatas, final NavigationMenuInterface navigationMenuInterface) {
        if (mDatas != null && mDatas.size() != 0) {
            this.pageCount = (int) Math.ceil((double) mDatas.size() * 1.0D / (double) this.pageSize);
            this.mPagerList = new ArrayList();

            for (int i = 0; i < this.pageCount; ++i) {
                GridView gridView = (GridView) View.inflate(this.context, R.layout.gridview, (ViewGroup) null);
                gridView.setAdapter(new GridViewAdapter(this.context, mDatas, i, this.pageSize, navigationMenuInterface));
                this.mPagerList.add(gridView);
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        int pos = position + NavigationMenuView.this.curIndex * NavigationMenuView.this.pageSize;
                        navigationMenuInterface.itemClick(pos, mDatas.get(pos));
                    }
                });
            }

            this.mPager.setAdapter(new ViewPagerAdapter(this.mPagerList));
            this.setOvalLayout();
        }
    }

    private void setOvalLayout() {
        for (int i = 0; i < this.pageCount; ++i) {
            this.mLlDot.addView(View.inflate(this.context, R.layout.dot, null));
        }

        this.mLlDot.getChildAt(0).findViewById(R.id.v_dot).setBackgroundResource(R.drawable.dot_selected);
        this.mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            public void onPageSelected(int position) {
                NavigationMenuView.this.mLlDot.getChildAt(NavigationMenuView.this.curIndex).findViewById(R.id.v_dot).setBackgroundResource(R.drawable.dot_normal);
                NavigationMenuView.this.mLlDot.getChildAt(position).findViewById(R.id.v_dot).setBackgroundResource(R.drawable.dot_selected);
                NavigationMenuView.this.curIndex = position;
            }

            public void onPageScrollStateChanged(int state) {
            }
        });
    }
}
