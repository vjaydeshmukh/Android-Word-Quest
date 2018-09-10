package com.aar.app.wordsearch.features.gamethemeselector;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.aar.app.wordsearch.data.room.GameThemeDataSource;
import com.aar.app.wordsearch.model.GameTheme;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ThemeSelectorViewModel extends ViewModel {

    private GameThemeDataSource mGameThemeRepository;

    private MutableLiveData<List<GameTheme>> mOnGameThemeLoaded;

    public ThemeSelectorViewModel(GameThemeDataSource gameThemeRepository) {
        mGameThemeRepository = gameThemeRepository;
        mOnGameThemeLoaded = new MutableLiveData<>();
    }

    @SuppressLint("CheckResult")
    public void loadThemes() {
        mGameThemeRepository.getThemes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mOnGameThemeLoaded::setValue);
    }

    public LiveData<List<GameTheme>> getOnGameThemeLoaded() {
        return mOnGameThemeLoaded;
    }
}
