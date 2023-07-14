package com.example.panharith.wifichat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class PrimaryActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener, View.OnClickListener

        {
    BottomNavigationView bottomNavigationView;
    CardView cardviewLight,cardviewCamera;
    LinearLayout ll;
    Intent intent;
    private MenuItem item;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.primary_activity);
         bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        cardviewLight = (CardView) findViewById(R.id.card_ligth);
        cardviewCamera = (CardView) findViewById(R.id.card_camera);

        cardviewLight.setOnClickListener(this);
        cardviewCamera.setOnClickListener(this);

    }

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                this.item = item;
                if (item.getItemId() == R.id.action_home) {
                    Intent intent = new Intent(PrimaryActivity.this, PrimaryActivity.class);
                    startActivity(intent);
                    Toast.makeText(PrimaryActivity.this, "home", Toast.LENGTH_LONG).show();
                } else if (item.getItemId() == R.id.action_profile) {
                    Intent intent = new Intent(PrimaryActivity.this, ProfileActivity.class);
                    startActivity(intent);
                    Toast.makeText(PrimaryActivity.this, "Profile", Toast.LENGTH_LONG).show();

                } else if (item.getItemId() == R.id.action_aboutus) {
                    Intent intent = new Intent(PrimaryActivity.this, AboutusActivity.class);
                    startActivity(intent);
                    Toast.makeText(PrimaryActivity.this, "About us", Toast.LENGTH_LONG).show();

                } else if (item.getItemId() == R.id.action_setting) {
                    Intent intent = new Intent(PrimaryActivity.this, LampActivity.class);
                    startActivity(intent);
                    Toast.makeText(PrimaryActivity.this, "Advicer", Toast.LENGTH_LONG).show();
                }

                return false;
            }

            @Override
            public void onClick(View v)
            {

                int id = v.getId();
                if (id == R.id.card_ligth)
                {
                  // Toast.makeText(PrimaryActivity.this,"The Man ",Toast.LENGTH_LONG).show();
                     intent = new Intent(PrimaryActivity.this,theLampActivity.class);
                    startActivity(intent);

                }
                return;


            }
        }
