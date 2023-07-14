package com.example.panharith.wifichat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

public class AdvicerActivity extends AppCompatActivity  implements BottomNavigationView.OnNavigationItemSelectedListener{

    BottomNavigationView bottomNavigationView;
    Intent intent;
    private MenuItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advicer);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener((BottomNavigationView.OnNavigationItemSelectedListener) this);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        this.item = item;
        if (item.getItemId() == R.id.action_home) {
            Intent intent = new Intent(AdvicerActivity.this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(AdvicerActivity.this, "menu home", Toast.LENGTH_LONG).show();
        } else if (item.getItemId() == R.id.action_profile) {
            Intent intent = new Intent(AdvicerActivity.this, ProfileActivity.class);
            startActivity(intent);
            Toast.makeText(AdvicerActivity.this, "Profile", Toast.LENGTH_LONG).show();

        } else if (item.getItemId() == R.id.action_aboutus) {
            Intent intent = new Intent(AdvicerActivity.this, AboutusActivity.class);
            startActivity(intent);
            Toast.makeText(AdvicerActivity.this, "About us", Toast.LENGTH_LONG).show();

        } else if (item.getItemId() == R.id.action_setting) {
            Intent intent = new Intent(AdvicerActivity.this, AdvicerActivity.class);
            startActivity(intent);
            Toast.makeText(AdvicerActivity.this, "Advicer", Toast.LENGTH_LONG).show();
        }
        return false;
    }

}

