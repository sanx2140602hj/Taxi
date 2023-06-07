package com.losj.mitaxistiat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.losj.mitaxistiat.models.User;

public class RegisterActivity extends AppCompatActivity {
    SharedPreferences mPref;
    //Registro de B_D
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    //views
    Button mButtonRegister;
    TextInputEditText mtextInputEmail;
    TextInputEditText mtextInputName;
    TextInputEditText mtextInputPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //Registro de B_D

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //variable para saber si es conductor o cliente
        mPref= getApplicationContext().getSharedPreferences("typeUser", MODE_PRIVATE);
        //se cambio a *metodo user*
        String SelectedUser = mPref.getString("user", "");
        //Toast.makeText(this, "El registro que seleciono es para: " + SelectedUser, Toast.LENGTH_SHORT).show();
        mButtonRegister = findViewById(R.id.btnRegister);
        mtextInputEmail = findViewById(R.id.textInputEmail);
        mtextInputName = findViewById(R.id.textInputName);
        mtextInputPassword = findViewById(R.id.textInputPassword);

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Metodo nuevo
                registerUser();
            }
        });

    }
    //metodo registerUser
    void registerUser(){
        final String name = mtextInputName.getText().toString();
        final String email = mtextInputEmail.getText().toString();
        final String password = mtextInputPassword.getText().toString();

        if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty()){
            if (password.length() >= 6){
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isComplete()){
                            //nuevo metodo user
                            saveUser(name, email);
                        } else {
                            Toast.makeText(RegisterActivity.this, "No se pudo registrar usuario, intente más tarde", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } else {
                Toast.makeText(this, "Contraseña debe tener al menos 8 caracteres", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Ingrese por favor todos los campos", Toast.LENGTH_SHORT).show();
        }
    }


    //metodo user

    void saveUser(String name, String email){
        String selectedUser = mPref.getString("user", "");
        User user = new User();
        user.setEmail(email);
        user.setName(name);

        if (selectedUser.equals("driver")){
            mDatabase.child("Users").child("Drivers").push().setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Registro inválido, intente más tarde", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else if(selectedUser.equals("cliente")){
            mDatabase.child("Users").child("Clients").push().setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Registro inválido, intente más tarde", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


}//corchete final