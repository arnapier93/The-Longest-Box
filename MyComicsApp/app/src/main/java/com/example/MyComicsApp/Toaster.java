package com.example.MyComicsApp;

import android.content.Context;
import android.widget.Toast;

public class Toaster {

    public Toaster(){
        //empty constructor
    }

    public void requiredField(Context context, String field){

        String requiredField = field + " is a required field. Please try again!";
        Toast.makeText(context,
                requiredField,
                Toast.LENGTH_SHORT).show();
    }

    public void multipleFields(Context context) {
        String requiredField = "Multiple required fields are not filled. Please try again!";
        Toast.makeText(context,
                requiredField,
                Toast.LENGTH_SHORT).show();
    }

    public void msg(Context context, String message){
        Toast.makeText(context,
                message,
                Toast.LENGTH_SHORT).show();
    }
}
