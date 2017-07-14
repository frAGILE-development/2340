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
 * Created by Ananya on 6/21/17.
 * Controller for the login screen
 *
 * Cancel button goes back to the first page of the app,
 * where the option exists to register or log in
 *
 * If proper credentials are presented, user is taken the
 * application main screen where they have the option of adding a lost item,
 * adding a found item, and viewing a list of both
 *
 */

public class Login extends AppCompatActivity {
    Boolean firstTime;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login = (Button) findViewById(R.id.login);
        Button register = (Button) findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean error = false;
                EditText username = (EditText) findViewById(R.id.name);
                EditText password = (EditText) findViewById(R.id.pass);
                Model model = Model.getInstance();
                User lookup = model.getUserByUsername(username.getText().toString());
                if (lookup != null ) {
                    if(lookup.getLockoutStatus()) {
                        error = true;
                        Toast.makeText(Login.this, "Bad Login: Account Locked", Toast.LENGTH_SHORT).show();
                    }
                }
                if ( username.getText().toString().equals(lookup.getUsername())
                        && password.getText().toString().equals(lookup.getPassword()) &
                        !error) {
                    model.setCurrentUser(model.getUserByUsername(username.getText().toString()));
                    Intent intent = new Intent(Login.this, Application.class);
                    startActivity(intent);
                } else if (username.getText().toString().equals("user") && password.getText().toString().equals("pass")) {
                    User defaultUser = (new User("user", "Default", "User", "pass", "defaultUser@gatech.edu",
                           "000000000", "Admin"));
                    model.addUser(defaultUser);
                    model.setCurrentUser(defaultUser);
                    Intent intent = new Intent(Login.this, Application.class);
                    startActivity(intent);
                }
                    else
                 {
                    Toast.makeText(Login.this, "Bad Login: Wrong Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
