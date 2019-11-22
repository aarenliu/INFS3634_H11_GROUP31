package com.example.letsplay;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class game extends Fragment { //Game Fragment
    private View view;
    private Button play; //play_button
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.game, container, false);
        play=view.findViewById(R.id.play);

        play.setOnClickListener(new View.OnClickListener() { //Play button's function
            @Override
            public void onClick(View v) { //when the play button is clicked
                Intent intent=new Intent(getContext(),Playgame.class); //it will direct to the playgame.class
                startActivity(intent); // start the game
            }
        });
        return view;
    }
}
