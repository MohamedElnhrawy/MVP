package com.example.mohamedelnhrawy.Base.ui.main;

import com.example.mohamedelnhrawy.Base.data.db.model.Location;
import com.example.mohamedelnhrawy.Base.ui.base.MvpView;

import java.util.List;

/**
 * Created by mohamedelnhrawy on 1/22/19.
 */

public interface MainMvpView extends MvpView {
    void openAddCityrActivity();
    void setUpRecyclerView();
    void updateRecyclerAdapter(List<Location> locations);
}
