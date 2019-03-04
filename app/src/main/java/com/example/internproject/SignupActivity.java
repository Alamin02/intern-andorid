package com.example.internproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // On click listener to 'already have id' text
        TextView signInLink = findViewById(R.id.signInLinkText);

        signInLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(SignupActivity.this, LoginActivity.class);
                SignupActivity.this.startActivity(login);
                SignupActivity.this.finish();
            }
        });

        // On click listener to signup button
        Button signupButton = findViewById(R.id.signup);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser user = new ParseUser();
                EditText usernameInput = findViewById(R.id.inputName);
                EditText emailInput = findViewById(R.id.inputEmail);
                EditText passwordInput = findViewById(R.id.inputPassword);
                EditText matchPassword = findViewById(R.id.matchPassword);

                if (TextUtils.isEmpty(usernameInput.getText().toString())){
                    usernameInput.setError("Please Enter an Username!");
                    return;
                }
                else if (TextUtils.isEmpty(emailInput.getText().toString())){
                    emailInput.setError("Please Enter an Email!");
                    return;
                }
                else if (TextUtils.isEmpty(passwordInput.getText().toString())){
                    passwordInput.setError("Please Enter Password!");
                    return;
                }
                else if (TextUtils.isEmpty(matchPassword.getText().toString())){
                    matchPassword.setError("Please Enter Password Again!");
                    return;
                }

                user.setUsername(usernameInput.getText().toString());
                user.setEmail(emailInput.getText().toString());
                user.setPassword(passwordInput.getText().toString());


                if (!passwordInput.getText().toString().equals(matchPassword.getText().toString())) {
                    Toast.makeText(SignupActivity.this, "Oops! Password Mismatch!", Toast.LENGTH_LONG).show();
                }
                else {
                    user.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                Toast.makeText(SignupActivity.this, "Congrats!", Toast.LENGTH_LONG).show();

                                Intent home = new Intent(SignupActivity.this, HomeActivity.class);
                                SignupActivity.this.startActivity(home);
                                SignupActivity.this.finish();

                            } else {
                                ParseUser.logOut();
                                Toast.makeText(SignupActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
