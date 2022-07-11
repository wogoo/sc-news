package com.wogoo.soccernews.ui.favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.wogoo.soccernews.MainActivity;
import com.wogoo.soccernews.databinding.FragmentFavoritesBinding;
import com.wogoo.soccernews.domain.News;
import com.wogoo.soccernews.ui.adapter.NewsAdapter;

import java.util.List;

public class FavoritesFragment extends Fragment {

    private FragmentFavoritesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FavoritesViewModel favoritesViewModel =
                new ViewModelProvider(this).get(FavoritesViewModel.class);

        binding = FragmentFavoritesBinding.inflate(inflater, container, false);


        loadFavoriteNews();

        return binding.getRoot();
    }

    private void loadFavoriteNews() {
        MainActivity activity = (MainActivity) getActivity();
        List<News> favoriteNews = null;
        if (activity != null) {
            favoriteNews = activity.getDB().newsDao().loadFavoriteNews();
        }
        binding.rvFavorites.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvFavorites.setAdapter(new NewsAdapter(favoriteNews, updatedNews -> {
            assert activity != null;
            activity.getDB().newsDao().save(updatedNews);
            loadFavoriteNews();
        }));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}