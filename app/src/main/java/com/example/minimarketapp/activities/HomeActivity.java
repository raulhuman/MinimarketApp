package com.example.minimarketapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.minimarketapp.MainActivity;
import com.example.minimarketapp.R;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    ProgressBar progressBar;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        
        auth = FirebaseAuth.getInstance();

        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);
        if(auth.getCurrentUser() != null){
            progressBar.setVisibility(View.VISIBLE);
            startActivity(new Intent(HomeActivity.this, MainActivity.class));
            Toast.makeText(this, "Ya ha iniciado sesion. Espere por favor", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public void login(View view) {
        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
    }

    public void registro(View view) {
        startActivity(new Intent(HomeActivity.this, RegistroActivity.class));
    }
}