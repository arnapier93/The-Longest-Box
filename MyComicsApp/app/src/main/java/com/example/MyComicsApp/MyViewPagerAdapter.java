package com.example.MyComicsApp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.MyComicsApp.fragments.AllFragment;
import com.example.MyComicsApp.fragments.DCFragment;
import com.example.MyComicsApp.fragments.IndieFragment;
import com.example.MyComicsApp.fragments.MarvelFragment;

public class MyViewPagerAdapter extends FragmentStateAdapter{
    public MyViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position){
            case 0:
                return new MarvelFragment();
            case 1:
                return new DCFragment();
            case 2:
                return new IndieFragment();
            case 3:
                return new AllFragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

}
