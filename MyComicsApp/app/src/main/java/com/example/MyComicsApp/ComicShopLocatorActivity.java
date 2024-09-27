package com.example.MyComicsApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.MyComicsApp.fragments.MapFragment;

public class ComicShopLocatorActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic_shop_locator);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Fragment fragment = new MapFragment();
        getSupportFragmentManager()
                .beginTransaction().replace(R.id.frame_layout,fragment)
                .commit();

    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.getItem(2).setVisible(false); //2 is the comic shop locator icon set to invisible when on that screen
        return true;
    }
    @Override
    public boolean  onOptionsItemSelected(MenuItem item) {
        CharSequence title = item.getTitle();
        if (title.equals("Library")) {
            Intent libraryIntent = new Intent(ComicShopLocatorActivity.this, LibraryActivity.class);
            startActivity(libraryIntent);
        } else if (title.equals("Add New Comic")) {
            Intent comicShopLocatorIntent = new Intent(ComicShopLocatorActivity.this, AddComicActivity.class);
            startActivity(comicShopLocatorIntent);
        }
        return true;
    }

}