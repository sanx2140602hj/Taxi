package com.losj.mitaxistiat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
import com.losj.mitaxistiat.incluides.MyToolbar;
import com.losj.mitaxistiat.models.Client;
import com.losj.mitaxistiat.models.User;
import com.losj.mitaxistiat.providers.AuthProvider;
import com.losj.mitaxistiat.providers.ClientProvider;

import dmax.dialog.SpotsDialog;

public class RegisterActivity extends AppCompatActivity {
    SharedPreferences mPref;
    AuthProvider mAuthProvider;
    ClientProvider mClientProvider;

    //views
    Button mButtonRegister;
    TextInputEditText mtextInputEmail;
    TextInputEditText mtextInputName;
    TextInputEditText mtextInputPassword;

    AlertDialog mDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //Registro de B_D
        mAuthProvider = new AuthProvider();
        mClientProvider = new ClientProvider();
        //tollbar
        MyToolbar.show(this,"Registar usuario",true);

        //variable para saber si es conductor o cliente
        mPref= getApplicationContext().getSharedPreferences("typeUser", MODE_PRIVATE);
        //se cambio a *metodo user*
        String SelectedUser = mPref.getString("user", "");
        //Toast.makeText(this, "El registro que seleciono es para: " + SelectedUser, Toast.LENGTH_SHORT).show();
        mButtonRegister = findViewById(R.id.btnRegister);
        mtextInputEmail = findViewById(R.id.textInputEmail);
        mtextInputName = findViewById(R.id.textInputName);
        mtextInputPassword = findViewById(R.id.textInputPassword);
        //alert
        mDialog = new SpotsDialog.Builder().setContext(RegisterActivity.this).setMessage("Espere un momento").build();

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Metodo nuevo
                clickRegister();
            }
        });

    }
    //metodo clickRegister antes registerUser
    void clickRegister(){
        final String name = mtextInputName.getText().toString();
        final String email = mtextInputEmail.getText().toString();
        final String password = mtextInputPassword.getText().toString();

        if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty()){
            if (password.length() >= 6){
                mDialog.show();
                register(name, email, password);
            } else {
                Toast.makeText(this, "Contraseña debe tener al menos 8 caracteres", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Ingrese por favor todos los campos", Toast.LENGTH_SHORT).show();
        }
    }
    void register(final String email, String password, String name){
        mAuthProvider.register(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                mDialog.hide();
                if (task.isComplete()){
                    //nuevo metodo user
                    String id = FirebaseAuth.getInstance().getUid();
                    Client client = new Client(id, name, email);
                    create(client);
                } else {
                    Toast.makeText(RegisterActivity.this, "No se pudo registrar usuario, intente más tarde", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    void create(Client client){
        mClientProvider.create(client).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(RegisterActivity.this, "El registro fue exitoso", Toast.LENGTH_SHORT).show();
                }else{
                    //
                    Toast.makeText(RegisterActivity.this, "No se pudo registrar el usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //metodo user

    /*void saveUser(String id, String name, String email){
        String selectedUser = mPref.getString("user", "");
        User user = new User();
        user.setEmail(email);
        user.setName(name);

        if (selectedUser.equals("Chofer")){
            mDatabase.child("Users").child("Drivers").child(id).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {

                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                        DatabaseReference nameRef = mDatabase.child(name);
                    } else {
                        Toast.makeText(RegisterActivity.this, "Registro inválido, intente más tarde", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            //falta guardar contraseña
        } else if(selectedUser.equals("Cliente")) {
            mDatabase.child("Users").child("Clients").child(id).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Registro inválido, intente más tarde", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }*/

}//corchete final