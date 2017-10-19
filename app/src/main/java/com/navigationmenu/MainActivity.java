package com.navigationmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mylibrary.NavigationMenuView;

public class MainActivity extends AppCompatActivity {

    private NavigationMenuView nmv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nmv = (NavigationMenuView) findViewById(R.id.nmv);
    }
}
