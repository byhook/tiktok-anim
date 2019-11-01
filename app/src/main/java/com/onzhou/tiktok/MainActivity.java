package com.onzhou.tiktok;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView();
    }

    private void setupView() {
        mRecycleView = (RecyclerView) findViewById(R.id.recycle_cover);
        mRecycleView.setLayoutManager(new GridLayoutManager(this, 2));
        //mRecycleView.addItemDecoration(new GridItemDecoration());
        mRecycleView.setAdapter(new VideoPlayAdapter());
    }

}
