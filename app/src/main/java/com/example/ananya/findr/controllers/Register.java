package com.example.ananya.findr.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Spinner;

import com.example.ananya.findr.R;

import model.persistence.ManagementFacade;
import model.User;
import model.Model;

/**
 * Created by Bryce Watson on 6/25/17.
 * Controller for registering a new User
 *
 * Ensures 3 things about the account created:
 * 1. Username and password are at least 6 characters
 * 2. Username, password, and Email are all not NULL
 * 3. Passwords in both fields match
 *
 * If admin field is left blank, it will default to a regular user
 */

public class Register extends AppCompatActivity {
    private Spinner accountTypeSpinner;
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button register = (Button) findViewById(R.id.button_register);
        Button cancel = (Button) findViewById(R.id.button_cancel);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean errorFlag = false;
                EditText username = (EditText) findViewById(R.id.editText_username);
                EditText password1 = (EditText) findViewById(R.id.editText_password);
                EditText password2 = (EditText) findViewById(R.id.editText_confirmPassword);
                EditText firstName = (EditText) findViewById(R.id.editText_firstName);
                EditText lastName = (EditText) findViewById(R.id.editText_lastName);
                EditText email = (EditText) findViewById(R.id.editText_email);
                EditText phone = (EditText) findViewById(R.id.editText_phone);
                EditText admin = (EditText) findViewById(R.id.editText_admin);
                //password confirmation check
                if (!password1.getText().toString().equals(password2.getText().toString())) {
                    Toast.makeText(Register.this, "Can't Register: Your passwords do not match", Toast.LENGTH_SHORT).show();
                    errorFlag = true;
                }
                //checks username, both password fields, and email for empty fields
                if (username.getText().toString().isEmpty() || password1.getText().toString().isEmpty() ||
                        password2.getText().toString().isEmpty() || email.getText().toString().isEmpty()) {
                    Toast.makeText(Register.this, "Can't Register: Username, password, and email required", Toast.LENGTH_SHORT).show();
                    errorFlag = true;
                }

                //checks if both username and password are > 6 chars
                if (username.getText().toString().length() < 6 || password1.getText().toString().length() < 6 ||
                        password2.getText().toString().length() < 6) {
                    Toast.makeText(Register.this, "Can't Register: Username and password must be at least 6 characters", Toast.LENGTH_SHORT).show();
                    errorFlag = true;
                }

                //user is registered and added to the model
                if (!errorFlag) {
                    Model model = Model.getInstance();
                    ManagementFacade mf = ManagementFacade.getInstance();
                    User _user = new User(username.getText().toString(), firstName.getText().toString(),
                            lastName.getText().toString(), password2.getText().toString(),
                            email.getText().toString(), "0000000000", "admin" );

                    if (admin.getText().toString().equalsIgnoreCase("admin")) {
                        _user.makeAdmin();
                    } else {
                        _user.demoteToUser();
                    }

                    if (phone.getText().toString().isEmpty()) {
                        _user.setPhoneNumber("0000000000");
                    } else {
                        _user.setPhoneNumber(phone.getText().toString().replaceAll("\\D+", ""));
                        //uses regex to pull non digits out of the string, if not null
                    }
                  
                    model.addUser(_user);
                    model.setCurrentUser(_user);
                    mf.addUser(_user);
                    Intent intent = new Intent(Register.this, Application.class);
                    startActivity(intent);

                }
//                if (username.getText().toString().equals("user") && password.getText().toString().equals("pass")) {
//                    Intent intent = new Intent(Login.this, Application.class);
//                    startActivity(intent);
//                } else {
//                    Toast.makeText(Login.this, "Bad Login: Wrong Credentials", Toast.LENGTH_SHORT).show();
//                }
            }
        });
    }
}
