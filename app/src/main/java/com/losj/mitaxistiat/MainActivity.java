package com.losj.mitaxistiat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button mButtonIAmDriver;
    Button mButtonIAmCliente;
    SharedPreferences mPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//SharedPreferences
        mPref= getApplicationContext().getSharedPreferences("typeUser", MODE_PRIVATE);
        SharedPreferences.Editor editor =mPref.edit();

//id de los botones
        mButtonIAmCliente = findViewById(R.id.btnIAmCliente);
        mButtonIAmDriver = findViewById(R.id.btnIAmDriver);
//Funcion para hacer interectivos los botones (cambio de pantallas)
        mButtonIAmCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("user", "Cliente");
                editor.apply();
                goToSelectAuth();
            }
        });
        mButtonIAmDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("user", "Chofer");
                editor.apply();
                goToSelectAuth();
            }
        });
    }

    private void goToSelectAuth() {
        Intent intent = new Intent(MainActivity.this, SelectOptionAuthActivity.class);
        startActivity(intent);
    }
}