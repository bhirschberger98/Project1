package com.bretthirschberger.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText mEmailField;
    private EditText mPasswordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEmailField=findViewById(R.id.email_field_1);
        mPasswordField=findViewById(R.id.password_field_1);
    }

    public void goToRegister(View view){
        startActivity(new Intent(this,RegistrationActivity.class));
    }
}
