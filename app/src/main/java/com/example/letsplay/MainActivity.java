package com.example.letsplay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView=findViewById(R.id.bottom_navigation); //fragment function
        bottomNavigationView.setOnNavigationItemSelectedListener(navlist);
        bottomNavigationView.setSelectedItemId(R.id.home);

    }
    private BottomNavigationView.OnNavigationItemSelectedListener navlist=new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) { //fragment function

            switch (menuItem.getItemId())
            {
                case R.id.home: //switch to game.class (gamepage)
                    game game=new game();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frag,game).commit();
                    return true;
                case R.id.cam:
                    score score=new score(); //switch to score.class (scoregame)
                    getSupportFragmentManager().beginTransaction().replace(R.id.frag,score).commit();
                    return true;
            }


            return true;
        }
    };



}
