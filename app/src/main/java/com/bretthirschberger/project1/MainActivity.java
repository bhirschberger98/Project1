package com.bretthirschberger.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private EditText mEmailField;
    private EditText mPasswordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEmailField = findViewById(R.id.email_field_1);
        mPasswordField = findViewById(R.id.password_field_1);
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
