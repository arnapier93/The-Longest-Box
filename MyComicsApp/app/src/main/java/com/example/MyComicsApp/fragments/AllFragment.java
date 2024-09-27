package com.example.MyComicsApp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.MyComicsApp.Comic;
import com.example.MyComicsApp.ComicViewModel;
import com.example.MyComicsApp.LibraryActivity;
import com.example.MyComicsApp.MainActivity;
import com.example.MyComicsApp.MyViewHolderAdapter;
import com.example.MyComicsApp.R;

import java.util.ArrayList;
import java.util.List;

public class AllFragment extends Fragment {

    public int itemCount;
    public MyViewHolderAdapter myAdapter;

    public AllFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);

        List<Comic> allComics = LibraryActivity.comicsInLibrary;

        if(allComics == null){
            allComics = new ArrayList<Comic>();
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        MyViewHolderAdapter myAdapter = new MyViewHolderAdapter(getContext(), allComics);
        recyclerView.setAdapter(myAdapter);

    }
}

