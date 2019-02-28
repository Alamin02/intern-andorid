package com.example.internproject;

import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
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

        menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                TextView log2 = findViewById(R.id.logger);

                switch (menuItem.getItemId()){
                    case R.id.navigation_local:
                        log2.setText("Local");
                        return true;
                    case R.id.navigation_server:
                        log2.setText("Server");
                        return true;
                    case R.id.navigation_more:
                        log2.setText("More");
                        return true;
                }

                return false;
            }
        });
    }
}
