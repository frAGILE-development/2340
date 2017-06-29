package com.example.ananya.findr.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ananya.findr.R;
import Model.User;
import Model.Model;

/**
 * Created by bwatson35 on 6/25/17.
 */

public class Register extends AppCompatActivity {
    private Spinner accountTypeSpinner;
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        
        accountTypeSpinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,
                User.accountType);
        accountTypeSpinner.setAdapter(adapter);

        Button register = (Button) findViewById(R.id.button_register);
        Button cancel = (Button) findViewById(R.id.button_cancel);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, Homepage.class);
                startActivity(intent);
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User _user = new User();
                Boolean errorFlag = false;
                EditText username = (EditText) findViewById(R.id.editText_username);
                EditText password1 = (EditText) findViewById(R.id.editText_password);
                EditText password2 = (EditText) findViewById(R.id.editText_confirmPassword);
                EditText firstName = (EditText) findViewById(R.id.editText_firstName);
                EditText lastName = (EditText) findViewById(R.id.editText_lastName);
                EditText email = (EditText) findViewById(R.id.editText_email);
                EditText phone = (EditText) findViewById(R.id.editText_phone);
                accountTypeSpinner.setSelection(User.findPosition(_user.getAdmin()));

                //password confirmation check
                if (!password1.getText().toString().equals(password2.getText().toString())) {
                    Toast.makeText(Register.this, "Can't Register: Your passwords do not match", Toast.LENGTH_SHORT).show();
                    errorFlag = true;
                }
                //checks username, both password fields, and email for empty fields
                if (username.getText().toString().equals(null) || password1.getText().toString().equals(null) ||
                        password2.getText().toString().equals(null) || email.getText().toString().equals(null)) {
                    Toast.makeText(Register.this, "Can't Register: Username, password, and email required", Toast.LENGTH_SHORT).show();
                    errorFlag = true;
                }

                //checks if both username and password are > 8 chars
                if (username.getText().toString().length() < 6 || password1.getText().toString().length() < 6 ||
                        password2.getText().toString().length() < 6) {
                    Toast.makeText(Register.this, "Can't Register: Username and password must be at least 6 characters", Toast.LENGTH_SHORT).show();
                    errorFlag = true;
                }

                //user is registered and added to the model
                if (!errorFlag) {
                    Model model = Model.getInstance();
                    _user.setUsername(username.getText().toString());
                    _user.setFullName(firstName.getText().toString(), lastName.getText().toString());
                    _user.setPassword(password2.getText().toString());
                    _user.setEmail(email.getText().toString());

                    if (phone.getText().toString().equals(null)) {
                        _user.setPhoneNumber("0000000000");
                    } else {
                        _user.setPhoneNumber(phone.getText().toString().replaceAll("\\D+", ""));
                        //uses regex to pull non digits out of the string, if not null
                    }
                    if (_user.getAdmin().equals(User.accountType.get(0)) {
                        _user.makeAdmin();
                    } else {
                        _user.demoteToUser();
                    }
                    model.addUser(_user);

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
