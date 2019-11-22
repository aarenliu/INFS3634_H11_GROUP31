package com.example.letsplay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class Playgame extends AppCompatActivity {

    private ImageView imageView;
    private Button one,two,three,four,cancel,save;
    private TextView score;
    private ArrayList<Integer> arrayList=new ArrayList<Integer>();
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener mAuthListner;
    private FirebaseUser firebaseUser;

    private int[] flags=new int[]{ // all countries's flags
            R.drawable.argentia,
            R.drawable.australia,
            R.drawable.canada,
            R.drawable.chile,
            R.drawable.cuba,
            R.drawable.denmark,
            R.drawable.egypt,
            R.drawable.france,
            R.drawable.germany,
            R.drawable.ireland,
            R.drawable.malaysia,
            R.drawable.morocco,
            R.drawable.newzealand,
            R.drawable.russia,
            R.drawable.spain,
            R.drawable.thailand,
            R.drawable.turkey,
            R.drawable.vietnam,
            R.drawable.us,
            R.drawable.venezuela,
    };
    private String[] anss=new String[]{ //all answer
            "Argentia",
            "Australia",
            "Canada",
            "Chile",
            "Cuba",
            "Denmark",
            "Egypt",
            "France",
            "Germany",
            "Ireland",
            "Malaysia",
            "Morocco",
            "Newzealand",
            "Russia",
            "Spain",
            "Thailand",
           "Turkey",
            "Vietnam",
             "US",
            "Venezuela",
    };

    int btnselect;
    int scoree;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playgame);
        imageView=findViewById(R.id.img);
        one=findViewById(R.id.one);
        two=findViewById(R.id.two);
        three=findViewById(R.id.three);
        four=findViewById(R.id.four);
        score=findViewById(R.id.score);
        cancel=findViewById(R.id.cancel);
        save=findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() { //save button
            @Override
            public void onClick(View v) {  //when the save button is clicked
                Toast.makeText(Playgame.this, "Saving...", Toast.LENGTH_SHORT).show(); // the msg will pop up "Saving"
                auth= FirebaseAuth.getInstance(); //get connection with firebase
                firebaseUser=auth.getCurrentUser();
                String uid=firebaseUser.getUid();
                String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date()); //date format setting
                String currentTime = new SimpleDateFormat("hh:mm:ss", Locale.getDefault()).format(new Date()); // time format setting
                String Sscore=score.getText().toString();
                Toast.makeText(Playgame.this, Sscore, Toast.LENGTH_SHORT).show();
                Toast.makeText(Playgame.this, Sscore, Toast.LENGTH_SHORT).show();
                Map<String,Object> map=new HashMap<>();
               // save a user object to the database with the date,time,score
                map.put("date",currentDate);
                map.put("time",currentTime);
                map.put("score",Sscore);

                db.collection(uid).document(currentTime).set(map, SetOptions.merge()).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) { //if the data is saved, the successful msg will pop up
                        Toast.makeText(Playgame.this, "Saved to Database !", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {  //if the data is not saved, the error msg will pop up
                        Toast.makeText(Playgame.this, "Error"+e, Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {  //cancel button
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Playgame.this,MainActivity.class); //return to the MainActivity.class
                startActivity(intent);
            }
        });
        generate(); //call generate()

    }
    public void chooseans(View v) //choose answer function
    {
        if(v.getTag().toString().equals(Integer.toString(btnselect)))
        {
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show(); //incorrect answer : the Correct msg will pop up
            scoree++; // score will increase by 1
            score.setText(Integer.toString(scoree));
            generate();
        }
        else
        {
            Toast.makeText(this, "Wrong !", Toast.LENGTH_SHORT).show();
            //incorrect answer : the error msg will pop up
        }
    }
    public void generate() //start game  - 4 choose 1
    {
        Random random=new Random();
        int a=random.nextInt(20); //random selection setting
        imageView.setImageResource(flags[a]);
        btnselect=random.nextInt(4);
        arrayList.clear(); //clear data

        if(btnselect==0)
        {
            one.setText(anss[a]);
            for(int i=0;i<3;i++) {
                int incorrct = random.nextInt(20);
                while (incorrct == btnselect) {
                    incorrct = random.nextInt(20);
                }
                arrayList.add(incorrct);

            }
            two.setText(anss[arrayList.get(0)]);
            three.setText(anss[arrayList.get(1)]);
            four.setText(anss[arrayList.get(2)]);
        }else if(btnselect==1)
        {
            two.setText(anss[a]);
            for(int i=0;i<3;i++) {
                int incorrct = random.nextInt(20);

                while (incorrct == btnselect) {
                    incorrct = random.nextInt(20);
                }
                arrayList.add(incorrct);

            }
            one.setText(anss[arrayList.get(0)]);
            three.setText(anss[arrayList.get(1)]);
            four.setText(anss[arrayList.get(2)]);
        }
        else if(btnselect==2)
        {
            three.setText(anss[a]);
            for(int i=0;i<3;i++) {
                int incorrct = random.nextInt(20);

                while (incorrct == btnselect) {
                    incorrct = random.nextInt(20);
                }
                arrayList.add(incorrct);

            }
            two.setText(anss[arrayList.get(0)]);
            one.setText(anss[arrayList.get(1)]);
            four.setText(anss[arrayList.get(2)]);
        }
        else if(btnselect==3)
        {
            four.setText(anss[a]);
            for(int i=0;i<3;i++) {
                int incorrct = random.nextInt(20);

                while (incorrct == btnselect) {
                    incorrct = random.nextInt(20);
                }
                arrayList.add(incorrct);

            }
            two.setText(anss[arrayList.get(0)]);
            three.setText(anss[arrayList.get(1)]);
            one.setText(anss[arrayList.get(2)]);
        }

    }

}
