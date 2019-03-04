package com.example.internproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Button loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            EditText emailInput = findViewById(R.id.loginEmail);
            EditText passwordInput = findViewById(R.id.loginPassword);

            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(emailInput.getText().toString())){
                    emailInput.setError("Please Enter an Email!");
                    return;
                }
                else if (TextUtils.isEmpty(passwordInput.getText().toString())){
                    passwordInput.setError("Please Enter Password!");
                    return;
                }

                ParseUser.logInInBackground(emailInput.getText().toString(), passwordInput.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user != null){
                            Toast.makeText(LoginActivity.this, "Welcome Back!", Toast.LENGTH_LONG).show();

                            Intent home = new Intent(LoginActivity.this, HomeActivity.class);
                            LoginActivity.this.startActivity(home);
                            LoginActivity.this.finish();

                        } else {
                            ParseUser.logOut();
                            Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}
