package com.example.MyComicsApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.MyComicsApp.fragments.MapFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class LibraryActivity extends AppCompatActivity {

    public static List<Comic> comicsInLibrary;
    private FloatingActionButton addComicFAB;
    private Toaster toaster = new Toaster();
    private FragmentManager manager;
    public static ComicViewModel comicViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        comicViewModel =  ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(ComicViewModel.class);;

        comicViewModel.getAllComicsFromVM().observe(
                this, comics -> {
                    if (comics != null && !comics.isEmpty()) {
                        comicsInLibrary = (ArrayList<Comic>) comics;
                    }
                });
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager2 viewPager2 = findViewById(R.id.view_pager2);
        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter(this);
        viewPager2.setAdapter(myViewPagerAdapter);
        manager = getSupportFragmentManager();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });

        addComicFAB = findViewById(R.id.floating_action_button);

        addComicFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addComic();
            }
        });

    }

    private void addComic() {
        Context context = LibraryActivity.this;
        Intent addComicIntent = new Intent(context, AddComicActivity.class);
        startActivity(addComicIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.getItem(0).setVisible(false); //0 is the library icon set to invisible when on the library screen
        return true;
    }

    @Override
    public boolean  onOptionsItemSelected(MenuItem item) {
        CharSequence title = item.getTitle();
        if (title.equals("Add New Comic")) {
            Intent libraryIntent = new Intent(LibraryActivity.this, AddComicActivity.class);
            startActivity(libraryIntent);
        } else if (title.equals("Comic Shop Locator")) {
            Intent comicShopLocatorIntent = new Intent(LibraryActivity.this, ComicShopLocatorActivity.class);
            startActivity(comicShopLocatorIntent);
        }
        return true;
    }

}