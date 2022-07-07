package com.wogoo.soccernews.ui.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.wogoo.soccernews.domain.News;

import java.util.ArrayList;
import java.util.List;

public class NewsViewModel extends ViewModel {

    private final MutableLiveData<List<News>> news = new MutableLiveData<>();

    public NewsViewModel() {


    }

    public LiveData<List<News>> getNews() {
        return news;
    }
}