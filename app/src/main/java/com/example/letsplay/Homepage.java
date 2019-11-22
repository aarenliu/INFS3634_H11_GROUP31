package com.example.letsplay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Homepage extends AppCompatActivity {

    private Animation animationone,animationtwo;
    private Button start;
    private TextView ask;
    private ImageView imageView;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener mAuthListner;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage );
        imageView=findViewById(R.id.img);// Image View
        ask=findViewById(R.id.ask); // Text View
        start=findViewById(R.id.start); //Button to move to next activity
        animationone= AnimationUtils.loadAnimation(this,R.anim.img);// Aniamtion store in anim folder
        animationtwo= AnimationUtils.loadAnimation(this,R.anim.text); // Animation store in anim folder
        imageView.setAnimation(animationone); // setting aniamtion
        ask.setAnimation(animationtwo); // setting aniamtion
        start.setAnimation(animationtwo);// setting aniamtion
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //start button is clicked
                checkuser(); //call checkuser
            }});
    }
    private void checkuser()
    { auth= FirebaseAuth.getInstance();  //connect the firebase
        firebaseUser=auth.getCurrentUser();
        if(firebaseUser==null) // if the system cannot find the user (null) in firebase
        { Intent inent=new Intent( Homepage.this,Register.class); // (start btn) it will direct user into Register class
            inent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);// other activities on top of it will be closed
            inent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);//clear any existing task that would be associated with the activity
            startActivity(inent); //start intent
            finish(); //end intent
        } else { // if the system can find the user (null) in firebase
            Intent inent=new Intent( Homepage.this,MainActivity.class); // (start btn) it will direct user into Register class
            inent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            inent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(inent);
            finish();
        }
    }
}
