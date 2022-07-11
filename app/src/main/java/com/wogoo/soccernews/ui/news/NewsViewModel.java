package com.wogoo.soccernews.ui.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.wogoo.soccernews.data.remote.SoccerNewsApi;
import com.wogoo.soccernews.domain.News;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsViewModel extends ViewModel {

    public enum State {
        DOING, DONE, ERROR;
    }

    private final MutableLiveData<List<News>> news = new MutableLiveData<>();
    private final MutableLiveData<State> state = new MutableLiveData<>();
    private final SoccerNewsApi api;


    public NewsViewModel() {
        Retrofit retofit = new Retrofit.Builder()
                .baseUrl("https://digitalinnovationone.github.io/soccer-news-api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        api = retofit.create(SoccerNewsApi.class);

         this.findNews();

    }

    private void findNews() {
        state.setValue(State.DOING);
        api.getNews().enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                if(response.isSuccessful()) {
                    state.setValue(State.DONE);
                    news.setValue(response.body());
                } else {
                    state.setValue(State.ERROR);
                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                state.setValue(State.ERROR);
            }
        });
    }

    public LiveData<List<News>> getNews() {
        return news;
    }
    public LiveData<State> getState() { return this.state; }
}