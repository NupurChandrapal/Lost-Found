package com.sazal.siddiqui.lost_people;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.sazal.siddiqui.lost_people.CustomAdapter.LFAdapter;
import com.sazal.siddiqui.lost_people.Model.LostFound;
import com.sazal.siddiqui.lost_people.Model.LostFoundData;
import com.sazal.siddiqui.lost_people.Puller.TheDoor;
import com.sazal.siddiqui.lost_people.Puller.TheRoad;
import com.sazal.siddiqui.lost_people.Utils.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, LFAdapter.ItemClickListener {

    LFAdapter lfAdapter;
    List<LostFoundData> lostFounds;
    List<LostFoundData> lost;
    List<LostFoundData> founds;
    @BindView(R.id.recyclerView)
    RecyclerView ms_RecyclerView;
    @BindView(R.id.imageView)
    AppCompatImageView ms_ImageView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout ms_SwipeRefreshLayout;
    @BindView(R.id.menu_item)
    FloatingActionButton ms_MenuItem;
    @BindView(R.id.menu_item2)
    FloatingActionButton ms_MenuItem2;
    private boolean isLost;

    private TheRoad theRoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initCollapsingToolbar();

        theRoad = TheDoor.driveOnRoad();

        final Intent intent = getIntent();
        isLost = intent.getBooleanExtra("isLost", true);
        if (isLost) ms_ImageView.setImageResource(R.drawable.lost);
        else ms_ImageView.setImageResource(R.drawable.found);

        lostFounds = new ArrayList<>();
        lost = new ArrayList<>();
        founds = new ArrayList<>();
        if (isLost) lfAdapter = new LFAdapter(this, lost);
        else lfAdapter = new LFAdapter(this, founds);

        lfAdapter.setClickListener(this);

        ms_SwipeRefreshLayout.setOnRefreshListener(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        ms_RecyclerView.setLayoutManager(layoutManager);
        ms_RecyclerView.setItemAnimator(new DefaultItemAnimator());
        ms_RecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        ms_RecyclerView.setAdapter(lfAdapter);


        ms_SwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                loadData();
            }
        });

        final Intent intent1 = new Intent(MainActivity.this, PostActivity.class);
        ms_MenuItem.setOnClickListener(v -> {
            intent1.putExtra("isLost", false);
            startActivity(intent1);
        });

        ms_MenuItem2.setOnClickListener(v -> {
            intent1.putExtra("isLost", true);
            startActivity(intent1);
        });

    }

    private void loadData() {
        ms_SwipeRefreshLayout.setRefreshing(true);
        theRoad.getAllLostFound("LostFound").enqueue(new Callback<LostFound>() {
            @Override
            public void onResponse(Call<LostFound> call, Response<LostFound> response) {
                lostFounds.clear();

                if (response.isSuccessful())
                    lostFounds.addAll(response.body().getLostFound());

                ms_SwipeRefreshLayout.setRefreshing(false);

                populate();
            }

            @Override
            public void onFailure(Call<LostFound> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Unable to fetch json: " + t.getMessage(), Toast.LENGTH_LONG).show();
                t.printStackTrace();
                ms_SwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void populate() {
        lost.clear();
        founds.clear();
        for (LostFoundData lostFoundData : lostFounds) {
            if (lostFoundData.getIsLost() == 0) lost.add(lostFoundData);
            else founds.add(lostFoundData);
        }

        lfAdapter.notifyDataSetChanged();
    }

    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appBar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    if (!isLost)
                        collapsingToolbar.setTitle(getString(R.string.found));
                    else collapsingToolbar.setTitle(getString(R.string.lost));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    @OnClick(R.id.recyclerView)
    public void onViewClicked() {
    }

    @Override
    public void onRefresh() {
        loadData();
    }

    @Override
    public void onClick(View view, int position) {
        LostFoundData data;
        if (isLost) {
            data = lost.get(position);
            Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
            intent.putExtra("data", data);
            startActivity(intent);
        } else {
            data = founds.get(position);
            Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
            intent.putExtra("data", data);
            startActivity(intent);
        }
    }


}
