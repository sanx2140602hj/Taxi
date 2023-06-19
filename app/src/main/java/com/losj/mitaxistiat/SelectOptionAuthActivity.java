package com.losj.mitaxistiat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.losj.mitaxistiat.incluides.MyToolbar;

public class SelectOptionAuthActivity extends AppCompatActivity {

    Button mButtonGoToLogin;
    Button mButtonGoToRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_option_auth);
//tolbar
        MyToolbar.show(this,"Selecciona una opcion",true);

//        Boton
        mButtonGoToLogin = findViewById(R.id.btnGoToLogin);
        mButtonGoToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLogin();
            }
        });
        mButtonGoToRegister = findViewById(R.id.btnGoRegister);
        mButtonGoToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToResgister();
            }
        });
    }

    public void goToLogin(){
        Intent intent = new Intent(SelectOptionAuthActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void goToResgister(){
        Intent intent = new Intent(SelectOptionAuthActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

}