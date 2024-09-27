package com.example.MyComicsApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class LoginActivity extends AppCompatActivity {

    private EditText usernameField;
    private EditText passwordField;
    private Button loginButton;
    private Button registerButton;
    private Button forgotPasswordButton;
    private Toaster toaster = new Toaster();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameField = (EditText) findViewById(R.id.username_field);
        passwordField = (EditText) findViewById(R.id.password_field);

        loginButton = (Button) findViewById(R.id.login_button);
        registerButton = (Button) findViewById(R.id.register_button);
        forgotPasswordButton = (Button) findViewById(R.id.forgot_password_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //implement stuff
            }
        });

        forgotPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //implement stuff
            }
        });


    }

    public void Login(){
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();
        boolean noUsername = username.matches("");
        boolean noPassword = password.matches("");

        if(noUsername && noPassword){
            toaster.multipleFields(this);
        }
        else if(noUsername  && !noPassword){
            toaster.requiredField(this, "Username");
        }
        else if(!noUsername  && noPassword){
            toaster.requiredField(this,"Password");
        }
        else{
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(MainActivity.LOGIN_STATUS, true);
            startActivity(intent);
        }

    }

}