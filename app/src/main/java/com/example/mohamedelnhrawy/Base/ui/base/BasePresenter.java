/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.example.mohamedelnhrawy.Base.ui.base;



import android.util.Log;

import com.androidnetworking.common.ANConstants;
import com.androidnetworking.error.ANError;
import com.example.mohamedelnhrawy.Base.R;
import com.example.mohamedelnhrawy.Base.data.DataManager;
import com.example.mohamedelnhrawy.Base.data.network.model.ApiError;
import com.example.mohamedelnhrawy.Base.util.AppConstants;
import com.example.mohamedelnhrawy.Base.util.rx.SchedulerProvider;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.patloew.rxlocation.RxLocation;


import javax.inject.Inject;
import javax.net.ssl.HttpsURLConnection;

import io.reactivex.disposables.CompositeDisposable;

import static android.content.ContentValues.TAG;

/**
 * Base class that implements the Presenter interface and provides a base implementation for
 * onAttach() and onDetach(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getMvpView().
 */
public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private final DataManager dataManager;
    private final RxLocation rxLocation;
    private final SchedulerProvider mSchedulerProvider;
    private final CompositeDisposable compositeDisposable;

    private V mMvpView;

    @Inject
    public BasePresenter(DataManager dataManager, RxLocation rxLocation, SchedulerProvider mSchedulerProvider, CompositeDisposable compositeDisposable) {
        this.dataManager = dataManager;
        this.rxLocation = rxLocation;
        this.mSchedulerProvider = mSchedulerProvider;
        this.compositeDisposable = compositeDisposable;

    }

    @Override
    public void onAttach(V mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void onDetach() {
        compositeDisposable.dispose();
        mMvpView = null;
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    public V getMvpView() {
        return mMvpView;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }
    public RxLocation getRxLocation(){return rxLocation;}

    @Override
    public void handleApiError(ApiError error) {
        try {
            if (error == null || error.getMessage() == null) {
                getMvpView().onError(R.string.api_default_error);
                return;
            }
            switch (error.getErrorCode()) {
                case AppConstants.API_STATUS_CODE_BAD_REQUEST:
//                    setUserAsLoggedOut();
//                    getMvpView().openActivityOnTokenExpire();
                case AppConstants.API_STATUS_CODE_INTERNAL_SERVER_ERROR:
                case AppConstants.API_STATUS_CODE_NOT_FOUND:
                default:
                    getMvpView().onError(error.getMessage());
            }
        } catch (JsonSyntaxException | NullPointerException e) {
            e.printStackTrace();
            getMvpView().onError(R.string.api_default_error);
        }
    }

    @Override
    public void handleApiError(ANError error) {
        if (error == null || error.getErrorBody() == null) {
            getMvpView().onError(R.string.api_default_error);
            return;
        }

        if (error.getErrorCode() == AppConstants.API_STATUS_CODE_LOCAL_ERROR
                && error.getErrorDetail().equals(ANConstants.CONNECTION_ERROR)) {
            getMvpView().onError("connection_error");
            return;
        }

        if (error.getErrorCode() == AppConstants.API_STATUS_CODE_LOCAL_ERROR
                && error.getErrorDetail().equals(ANConstants.REQUEST_CANCELLED_ERROR)) {
            getMvpView().onError("api_retry_error");
            return;
        }

        final GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
        final Gson gson = builder.create();

        try {
            ApiError apiError = gson.fromJson(error.getErrorBody(), ApiError.class);

            if (apiError == null || apiError.getMessage() == null) {
                getMvpView().onError(R.string.api_default_error);
                return;
            }

            switch (error.getErrorCode()) {
                case HttpsURLConnection.HTTP_UNAUTHORIZED:
                case HttpsURLConnection.HTTP_FORBIDDEN:
//                    setUserAsLoggedOut();
//                    getMvpView().openActivityOnTokenExpire();
                case HttpsURLConnection.HTTP_INTERNAL_ERROR:
                case HttpsURLConnection.HTTP_NOT_FOUND:
                default:
                    getMvpView().onError(apiError.getMessage());
            }
        } catch (JsonSyntaxException | NullPointerException e) {
            Log.e(TAG, "handleApiError", e);
            getMvpView().onError(R.string.api_default_error);
        }

    }


    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.onAttach(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }

    public SchedulerProvider getSchedulerProvider() {
        return mSchedulerProvider;
    }
}
