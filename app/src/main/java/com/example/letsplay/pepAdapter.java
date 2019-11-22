package com.example.letsplay;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

public class pepAdapter extends FirestoreRecyclerAdapter<modell,pepAdapter.pepHolder> {


    private OnItemClickListener listener;

    public pepAdapter(@NonNull FirestoreRecyclerOptions<modell> options) {
        super(options);
    }

    @Override //to store the reference of View’s.
    protected void onBindViewHolder(@NonNull pepHolder holder, int position, @NonNull modell model) {
        holder.date.setText(model.getDate().toString());
        holder.time.setText(model.getTime().toString());
        holder.score.setText(model.getScore().toString());
    }

    @NonNull
    @Override
    public pepHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //use to initialize our ViewHolders
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.receyle_scorepage
        ,parent,false);
        return new pepHolder(v);
    }

    class pepHolder extends RecyclerView.ViewHolder
    {
        TextView date,time,score;

        public pepHolder(@NonNull View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.date);
            time=itemView.findViewById(R.id.time);
            score=itemView.findViewById(R.id.score);
        }
    }

    public  interface  OnItemClickListener
    {
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }
    public void setonItemclicklistener(OnItemClickListener listener)
    {

        this.listener=listener;
    }

}
