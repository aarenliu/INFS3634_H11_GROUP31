package com.example.letsplay;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class Register extends AppCompatActivity {

   private FirebaseAuth auth;
   static final int GOOGLE=123;
   private GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register); //register page
        auth=FirebaseAuth.getInstance();  //connect to firebase

        SignInButton sign_in_button = findViewById(R.id.sign_in_button);
        sign_in_button.setOnClickListener(new View.OnClickListener() { //sign in button - click function
            @Override
            public void onClick(View v) {
                SignInGoole(); //call SignInGoole
            }
        });
        GoogleSignInOptions googleSignInOptions=new GoogleSignInOptions // set up the google sign-in function
                .Builder().requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail().build();

        googleSignInClient=GoogleSignIn.getClient(this,googleSignInOptions);

    }
   public void SignInGoole() // google sign-in function
    {
        Intent intent=googleSignInClient.getSignInIntent();
        startActivityForResult(intent,GOOGLE);
    }

    @Override //set up and connect the google_login api with the firebase
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==GOOGLE)
        { // Result returned from launching the Intent from GoogleSignInApi.
            Task<GoogleSignInAccount> task=GoogleSignIn.getSignedInAccountFromIntent(data);
            try
            {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account=task.getResult(ApiException.class);
                if(account!=null) firebaseAuthWithGoogle(account);
            }
            catch(ApiException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) { //connect the firebase with gmail api
        AuthCredential credential= GoogleAuthProvider
                .getCredential(account.getIdToken(),null);
        auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Intent intent=new Intent(Register.this,MainActivity.class);
                    startActivity(intent);
                }
                //if a user success to register their account by using gmail,the page will direct to MainActivity.class(activity_main.xml)
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Register.this, "Error"+e, Toast.LENGTH_SHORT).show();
                //the error msg will pop up if the registration is failed
            }
        });
    }
}
