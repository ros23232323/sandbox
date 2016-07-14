package com.lucidlogic.horsetracker.presenter.impl;

import com.lucidlogic.horsetracker.model.Racecard;
import com.lucidlogic.horsetracker.model.RacecardDTO;
import com.lucidlogic.horsetracker.presenter.RacecardPresenter;
import com.lucidlogic.horsetracker.service.ParseService;
import com.lucidlogic.horsetracker.utils.BeanTransformers;
import com.lucidlogic.horsetracker.view.RacecardView;

import java.util.Date;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by ian on 10/07/16.
 */
public class RacecardPresenterImpl implements RacecardPresenter {
    RacecardView racecardView;

    @Override
    public void setView(RacecardView racecardView) {
        this.racecardView = racecardView;
    }

    @Override
    public void updateView() {
        ParseService.getRacecards(new Date()).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(new Func1<RacecardDTO, Racecard>() {
                    @Override
                    public Racecard call(RacecardDTO racecardDTO) {
                        Racecard racecard = BeanTransformers.racecardFromRacecardDTO(racecardDTO);
                        return racecard;
                    }
                })
                .subscribe(new Observer<Racecard>() {
                    @Override
                    public void onCompleted() {
                        Timber.i("Item retrival complete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e,e.getMessage());
                        e.printStackTrace();
                    }
                    @Override
                    public void onNext(Racecard racecard) {
                        Timber.i("Item retrieved");
                        racecardView.updateView(racecard);
                    }
                });
    }
}
