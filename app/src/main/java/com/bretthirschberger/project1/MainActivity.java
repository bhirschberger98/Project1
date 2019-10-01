package com.bretthirschberger.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private EditText mEmailField;
    private EditText mPasswordField;

    private static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        }, SPLASH_TIME_OUT);

        mEmailField = findViewById(R.id.email_field_1);
        mPasswordField = findViewById(R.id.password_field_1);

        //restores
        if (savedInstanceState != null) {
            mEmailField.setText(savedInstanceState.getString("email","ERROR"));
            mPasswordField.setText(savedInstanceState.getString("password","ERROR"));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("email", mEmailField.getText().toString());
        outState.putString("password", mPasswordField.getText().toString());
    }

    public void login(View view) {
        String email = mEmailField.getText().toString().trim();
        if (checkValidLogin()) {
            startActivity(new Intent(this, AccountHome.class).putExtra("email", email));
        } else {
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.login_failed), Toast.LENGTH_SHORT).show();
        }
    }

    public boolean checkValidLogin() {
        String email = mEmailField.getText().toString().trim();
        String password = mPasswordField.getText().toString().trim();
        try (Scanner reader = new Scanner(new File(getFilesDir().getAbsolutePath() + "/users.txt"))) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] userInfo = line.split(",");
                if (userInfo[0].equals(email) && userInfo[3].trim().equals(password)) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;

    }

    public void goToRegister(View view) {
        startActivity(new Intent(this, RegistrationActivity.class));
    }
}
