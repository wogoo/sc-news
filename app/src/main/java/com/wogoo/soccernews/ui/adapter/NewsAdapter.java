package com.wogoo.soccernews.ui.adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.wogoo.soccernews.databinding.NewsItemBinding;
import com.wogoo.soccernews.domain.News;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private final List<News> news;
    private final View.OnClickListener favoritesListener;

    public NewsAdapter(List<News> news, View.OnClickListener favoritesListener) {
        this.news = news;
        this.favoritesListener = favoritesListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        NewsItemBinding binding = NewsItemBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        News newsItem = news.get(position);
        holder.binding.tvTitle.setText(newsItem.title);
        holder.binding.tvDesc.setText(newsItem.description);
        Picasso.get().load(newsItem.image).into(holder.binding.ivThumbnail);

        holder.binding.btOpenLink.setOnClickListener( view -> {
            Intent  i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(newsItem.link));
            holder.itemView.getContext().startActivity(i);
        });

        holder.binding.ivShare.setOnClickListener(view -> {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT, newsItem.description);
            i.putExtra(Intent.EXTRA_TEXT, newsItem.link);
            holder.itemView.getContext().startActivity(Intent.createChooser(i, "Share link using"));
        });

        holder.binding.ivFavorite.setOnClickListener(this.favoritesListener);
    }

    @Override
    public int getItemCount() {
        return this.news.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final NewsItemBinding binding;

        public ViewHolder(NewsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
