package com.example.mohamedelnhrawy.Base.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import com.example.mohamedelnhrawy.Base.R;
import com.example.mohamedelnhrawy.Base.ui.base.BaseActivity;
import com.example.mohamedelnhrawy.Base.ui.main.MainActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class SplashActivity extends BaseActivity implements SplashMvpView{
    @Inject
    SplashMvpPresenter<SplashMvpView> mPresenter;
    private final int SPLASH_DISPLAY_LENGTH = 2000;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, SplashActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(SplashActivity.this);


    }

    @Override
    protected void setUp() {

    }

    @Override
    public void openMainActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(MainActivity.getStartIntent(getBaseContext()));
                finish();
            }
        },SPLASH_DISPLAY_LENGTH);

    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }
}
