package com.losj.mitaxistiat.providers;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.losj.mitaxistiat.models.Client;

public class ClientProvider {
    DatabaseReference mDatabase;

    public ClientProvider(){
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Cliente");
    }

    public Task<Void> create(Client client){
        return  mDatabase.child(client.getId()).setValue(client);

    }

}
