package com.wogoo.soccernews.ui.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.wogoo.soccernews.domain.News;

import java.util.ArrayList;
import java.util.List;

public class NewsViewModel extends ViewModel {

    private final MutableLiveData<List<News>> mNews;

    public NewsViewModel() {
        mNews = new MutableLiveData<>();

        List<News> news = new ArrayList<>();
        news.add(new News("t1", "d1"));
        news.add(new News("t2", "d2"));
        news.add(new News("t3", "d3"));

        mNews.setValue(news);
    }

    public LiveData<List<News>> getNews() {
        return mNews;
    }
}