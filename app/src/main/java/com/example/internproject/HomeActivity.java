package com.example.internproject;

import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView menu = findViewById(R.id.navigationMenu);

        Fragment defaultFragment;
        defaultFragment = new LocalFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, defaultFragment).commit();


        menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment newFragment;

                switch (menuItem.getItemId()){
                    case R.id.navigation_local:
                        newFragment = new LocalFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, newFragment).commit();
                        return true;
                    case R.id.navigation_server:
                        return true;
                    case R.id.navigation_more:
                        return true;
                }

                return false;
            }
        });
    }
}
