package com.bretthirschberger.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity {

    private EditText mFirstNameField;
    private EditText mLastNameField;
    private EditText mDOBField;
    private EditText mEmailField;
    private EditText mPasswordField;
    private EditText mConfirmPasswordField;
    private Button mRegisterButton;
//    private boolean isValidDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mFirstNameField = findViewById(R.id.first_name_field);
        mLastNameField = findViewById(R.id.last_name_field);
        mDOBField = findViewById(R.id.dob_field);
        mEmailField = findViewById(R.id.email_field_2);
        mPasswordField = findViewById(R.id.password_field_2);
        mConfirmPasswordField = findViewById(R.id.confirm_password_field);
        mRegisterButton = findViewById(R.id.register_button);


        mFirstNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (mFirstNameField.getText().toString().trim().length() < 3) {
                    Toast.makeText(getApplicationContext(), "Name Must be at least 3 chars", Toast.LENGTH_SHORT).show();
                }
                if (mFirstNameField.getText().toString().trim().length() > 30) {
                    Toast.makeText(getApplicationContext(), "Name Must be less than 30 chars", Toast.LENGTH_SHORT).show();
                }
                checkValidEntry();
            }
        });
        mLastNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                checkValidEntry();
            }
        });
        mDOBField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkValidEntry();
            }
        });
        mEmailField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                checkValidEntry();
            }
        });
        mPasswordField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                checkValidEntry();
            }
        });
        mConfirmPasswordField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                checkValidEntry();
            }
        });
    }

    public void checkValidEntry() {
        Log.i("Valid", "-------------");
        Log.i("Valid length", (mFirstNameField.getText().toString().length() > 3) + "");
        Log.i("Valid length", (mFirstNameField.getText().toString().length() < 30) + "");
        Log.i("Not empty", !mPasswordField.getText().toString().equals("") + "");
        Log.i("Is same", mPasswordField.getText().toString().equals(mConfirmPasswordField.getText().toString()) + "");
        Log.i("Valid Date", isValidDate() + "");
        Log.i("Valid Email", isValidEmail() + "");
        if (mFirstNameField.getText().toString().trim().length() > 3 &&
                mFirstNameField.getText().toString().trim().length() < 30 &&
                !mPasswordField.getText().toString().equals("") &&
                mPasswordField.getText().toString().equals(mConfirmPasswordField.getText().toString()) &&
                isValidDate() &&
                isValidEmail()) {
            mRegisterButton.setEnabled(true);
        } else {
            mRegisterButton.setEnabled(false);
        }
    }

    public boolean isValidDate() {
        try {
            new SimpleDateFormat("MM/dd/yyyy").parse(mDOBField.getText().toString().trim());
//            isValidDate = true;
//            checkValidEntry();
            return true;
        } catch (ParseException e) {
//            isValidDate = false;
        }
        try {
            new SimpleDateFormat("MM-dd-yyyy").parse(mDOBField.getText().toString().trim());
//            isValidDate = true;
//            checkValidEntry();
            return true;
        } catch (ParseException e) {
//            isValidDate = false;
        }
        try {
            new SimpleDateFormat("MM/dd/yy").parse(mDOBField.getText().toString().trim());
//            isValidDate = true;
//            checkValidEntry();
            return true;
        } catch (ParseException e) {
//            isValidDate = false;
        }
        try {
            new SimpleDateFormat("MM-dd-yy").parse(mDOBField.getText().toString().trim());
//            isValidDate = true;
//            checkValidEntry();
            return true;
        } catch (ParseException e) {
//            isValidDate = false;
        }
        return false;
    }

    public boolean isValidEmail() {
        //Standard regex for emails
        String rfc5322 = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\" +
                ".[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\" +
                "x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\" +
                "x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")" +
                "@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\" +
                ".)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\" +
                "[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\" +
                ".){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\" +
                "x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

        if (Pattern.compile(rfc5322, Pattern.CASE_INSENSITIVE).matcher(mEmailField.getText().toString().trim()).matches()) {
            return true;
        }
        return false;
    }

    public void register(View view) {
        SharedPreferences users= getSharedPreferences("users", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor= users.edit();

//        editor.putString("")
    }
}