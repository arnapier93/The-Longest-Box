package com.example.MyComicsApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private boolean userLoggedIn;
    public static ComicViewModel comicViewModel;
    public static final String LOGIN_STATUS = "com.example.MyComicsApp.LOGIN_STATUS";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        userLoggedIn = intent.getBooleanExtra(LOGIN_STATUS, false);

        if(userLoggedIn) {
            enterLibraryView();
        }
        else{
            enterLoginView();
        }
    }

    private void enterLoginView() {
        Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(loginIntent);
    }


    private void enterLibraryView() {
        Intent libraryIntent = new Intent(MainActivity.this, LibraryActivity.class);
        startActivity(libraryIntent);
    }

    public void loginSuccess(){
        userLoggedIn = true;
    }

}
