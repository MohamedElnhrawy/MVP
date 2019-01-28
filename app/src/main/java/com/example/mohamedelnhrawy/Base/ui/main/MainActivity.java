package com.example.mohamedelnhrawy.Base.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.mohamedelnhrawy.Base.R;
import com.example.mohamedelnhrawy.Base.data.db.model.Location;
import com.example.mohamedelnhrawy.Base.ui.addcity.AddCityActivity;
import com.example.mohamedelnhrawy.Base.ui.base.BaseActivity;
import com.example.mohamedelnhrawy.Base.ui.details.DetailsActivity;
import com.example.mohamedelnhrawy.Base.ui.main.adapter.LocationItemAdapter;
import com.mikepenz.fastadapter.adapters.FastItemAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MainMvpView, LocationItemAdapter.onCardClicked {
    public static final String INTENT_LOCATION_KEY="location";
    LocationItemAdapter locationItemAdapter;
    List<Location> locations=new ArrayList<>();
    @Inject
    MainPresenter<MainMvpView> mPresenter;

    @OnClick(R.id.fab_add_city)
    void add(){
        openAddCityrActivity();
    }

    @BindView(R.id.rv_locations_list)
    RecyclerView rv_locations_list;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setUp();

    }

    @Override
    protected void setUp() {
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(MainActivity.this);

    }

    @Override
    public void openAddCityrActivity() {
        startActivity(AddCityActivity.getStartIntent(MainActivity.this));
    }

    @Override
    public void setUpRecyclerView() {
        rv_locations_list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        locationItemAdapter = new LocationItemAdapter(locations,this,MainActivity.this);
        rv_locations_list.setAdapter(locationItemAdapter);
    }

    @Override
    public void updateRecyclerAdapter(List<Location> locations) {
        Log.e("list",""+locations.size());
            locationItemAdapter.updateList(locations);
    }


    @Override
    public void onItemClicked(int position, Location location) {
        startActivity(DetailsActivity.getStartIntent(this).putExtra(INTENT_LOCATION_KEY, (Parcelable) location));
    }
}
