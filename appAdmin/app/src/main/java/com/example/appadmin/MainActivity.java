package com.example.appadmin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void insertartUser (View view){
        Intent i = new Intent(MainActivity.this, ingresarUsuario.class);
        startActivity(i);
    }
    public void insertarTarro (View view){
        Intent i = new Intent(MainActivity.this, ingresarTarro.class);
        startActivity(i);
    }
}
