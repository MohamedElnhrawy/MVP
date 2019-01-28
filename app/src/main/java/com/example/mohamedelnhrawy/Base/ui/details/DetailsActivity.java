package com.example.mohamedelnhrawy.Base.ui.details;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.mohamedelnhrawy.Base.R;
import com.example.mohamedelnhrawy.Base.data.db.model.Location;
import com.example.mohamedelnhrawy.Base.data.network.model.Place;
import com.example.mohamedelnhrawy.Base.ui.base.BaseActivity;
import com.example.mohamedelnhrawy.Base.ui.main.MainActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class DetailsActivity extends BaseActivity implements DetailsMvpView {

    @Inject
    DetailsPresenter<DetailsMvpView> mPresenter;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, DetailsActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        setUp();
    }

    @Override
    protected void setUp() {
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(this);

    }

    @Override
    public void getIntentData() {
      Location location = getIntent().getExtras().getParcelable(MainActivity.INTENT_LOCATION_KEY);
        Log.e("Details",""+location.getLocation_latitude());
        mPresenter.getLocationData(location);
    }

    @Override
    public void updateUi(Place place) {
        Log.e("Detailsplace",""+place.getCity());

    }
}
