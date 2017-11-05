package com.example.nikhil.krigerapplication.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.nikhil.krigerapplication.R;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {


    Button btnlogout ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnlogout = (Button)findViewById(R.id.buttonLogout);

    }
    public void Logout(View v)
    {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(HomeActivity.this, SignInActivity.class));
        finish();
    }
}
