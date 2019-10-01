package com.bretthirschberger.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AccountHome extends AppCompatActivity {

    private TextView mNameView;
    private TextView mDOBView;
    private TextView mEmailView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_home);
        mNameView = findViewById(R.id.name_view);
        mDOBView = findViewById(R.id.dob_view);
        mEmailView = findViewById(R.id.email_view);
        displayAccountInfo();
    }



    public void displayAccountInfo(){
        String email= getIntent().getStringExtra("email");
        try (Scanner reader = new Scanner(new File(getFilesDir().getAbsolutePath() + "/users.txt"))) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] userInfo = line.split(",");
                Log.i("File Line", line);
                if (userInfo[0].equals(email)) {
                    mEmailView.setText(userInfo[0]);
                    mNameView.setText(userInfo[1]);
                    mDOBView.setText(userInfo[2]);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
