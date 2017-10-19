package com.example.mylibrary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


/**
 * Created by xiaox on 2017/10/18.
 */

public class GridViewAdapter extends BaseAdapter {
    private NavigationMenuInterface navigationMenuInterface;
    private List<? extends Object> mDatas;
    private LayoutInflater inflater;
    private int curIndex;
    private int pageSize;

    public GridViewAdapter(Context context, List<? extends Object> mDatas, int curIndex, int pageSize, NavigationMenuInterface navigationMenuInterface) {
        this.mDatas = mDatas;
        this.pageSize = pageSize;
        this.curIndex = curIndex;
        this.navigationMenuInterface = navigationMenuInterface;
        this.inflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return this.mDatas.size() > (this.curIndex + 1) * this.pageSize ? this.pageSize : this.mDatas.size() - this.curIndex * this.pageSize;
    }

    public Object getItem(int position) {
        return this.mDatas.get(position + this.curIndex * this.pageSize);
    }

    public long getItemId(int position) {
        return (long) (position + this.curIndex * this.pageSize);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = this.inflater.inflate(R.layout.item_gridview, null);
            vh = new ViewHolder();
            vh.iv = (ImageView) convertView.findViewById(R.id.imageView);
            vh.tv = (TextView) convertView.findViewById(R.id.textView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        int pos = position + this.curIndex * this.pageSize;
        this.navigationMenuInterface.itemLoad(pos, vh.iv, vh.tv, this.mDatas.get(pos));
        return convertView;
    }

    class ViewHolder {
        public TextView tv;
        public ImageView iv;

        ViewHolder() {
        }
    }
}

