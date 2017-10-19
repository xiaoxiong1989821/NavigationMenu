package com.example.mylibrary;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by xiaox on 2017/10/18.
 */

public interface NavigationMenuInterface {
    void itemClick(int pos, Object object);

    void itemLoad(int pos, ImageView imageView, TextView textView, Object object);
}
