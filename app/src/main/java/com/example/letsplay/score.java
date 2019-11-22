package com.example.letsplay;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


public class score extends Fragment {
    private pepAdapter adapter;
    private View view;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener mAuthListner;
    private FirebaseUser firebaseUser;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.score, container, false); //score.xml
        auth= FirebaseAuth.getInstance(); //connect to firebase
        firebaseUser=auth.getCurrentUser();

        String uid=firebaseUser.getUid(); //match the data type with the firebase - String: uid

        Query query = FirebaseFirestore.getInstance().collection(uid);
        //binds a Query to a RecyclerView
        FirestoreRecyclerOptions<modell> options = new FirestoreRecyclerOptions.Builder<modell>() //configure the adapter
                .setQuery(query, modell.class)
                .build();
        adapter=new pepAdapter(options);
        //added, removed or change - these updates are applied automatically
        RecyclerView recyclerView = view.findViewById(R.id.ree); //RecyclerView setting
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}
