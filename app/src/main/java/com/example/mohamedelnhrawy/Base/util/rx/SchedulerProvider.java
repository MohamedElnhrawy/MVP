package com.example.mohamedelnhrawy.Base.util.rx;

import io.reactivex.Scheduler;

/**
 * Created by mohamedelnhrawy on 1/27/19.
 */

public interface SchedulerProvider {
    Scheduler ui();

    Scheduler computation();

    Scheduler io();
}
