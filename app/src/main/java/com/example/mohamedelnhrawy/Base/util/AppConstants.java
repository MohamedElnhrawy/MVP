package com.example.mohamedelnhrawy.Base.util;

/**
 * Created by mohamedelnhrawy on 1/21/19.
 */

public final class AppConstants {
    public static final int API_STATUS_CODE_BAD_REQUEST = 403;
    public static final int API_STATUS_CODE_NOT_FOUND = 404;
    public static final int API_STATUS_CODE_INTERNAL_SERVER_ERROR = 500;
    public static final int API_STATUS_CODE_LOCAL_ERROR = 0;

    public static final String API_PLACE_DATA_ENDPOINT = "data/2.5/forecast/daily";
    public static final String DB_NAME = "weather";
    public static final String PREF_NAME = "weather_pref";
    public static final String TIMESTAMP_FORMAT = "yyyyMMdd_HHmmss";
    private AppConstants() {
        // This utility class is not publicly instantiable
    }
}

