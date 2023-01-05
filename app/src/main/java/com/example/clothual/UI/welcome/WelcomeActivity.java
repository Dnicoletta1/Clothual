package com.example.clothual.UI.welcome;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clothual.R;

import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_layout);

    }
    //disable backButton
    @Override
    public void onBackPressed(){
        Toast.makeText(getApplicationContext(),"Premi il tasto home per uscire da Clothual", Toast.LENGTH_SHORT).show();
    }
}