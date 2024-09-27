package com.example.MyComicsApp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    public int position;
    ImageView scanView;
    TextView seriesTitleView, issueNumView, writerView, artistView, publisherView;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        scanView = itemView.findViewById(R.id.scan_view);
        seriesTitleView = itemView.findViewById(R.id.series_title_view);
        issueNumView = itemView.findViewById(R.id.issue_num_view);
        writerView = itemView.findViewById(R.id.writer_view);
        artistView = itemView.findViewById(R.id.artist_view);
        publisherView = itemView.findViewById(R.id.publisher_view);
    }

}
