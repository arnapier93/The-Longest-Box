package com.example.MyComicsApp;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyViewHolderAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;
    List<Comic> comics;

    public MyViewHolderAdapter(Context context, List<Comic> comics) {
        this.context = context;
        this.comics = comics;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Comic comic = comics.get(position);
        holder.scanView.setImageBitmap(BitmapFactory.decodeByteArray(comic.bitmapAsBytes, 0, comic.bitmapAsBytes.length));
        holder.seriesTitleView.setText(comic.getSeriesTitle());
        holder.issueNumView.setText(comic.getIssueNum());
        holder.writerView.setText(comic.getWriter());
        holder.artistView.setText(comic.getArtist());
        if(comic.getPublisher() == Comic.Publisher.INDIE){
            holder.publisherView.setText(comic.getIndiePublisher());
        }
        else{
            holder.publisherView.setVisibility(View.INVISIBLE);
        }
        holder.position = position;
    }

    @Override
    public int getItemCount() {
        return comics.size();
    }

    public Comic getItem(int position){return comics.get(position);}

}
