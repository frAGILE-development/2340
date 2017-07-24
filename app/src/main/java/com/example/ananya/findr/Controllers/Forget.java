package com.example.ananya.findr.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.net.Uri;
import android.util.Log;

import com.example.ananya.findr.R;


import java.util.Arrays;
import java.util.List;

import Model.User;
import Model.Model;
/**
 * Created by jordan on 7/24/2017.
 * Forget password controller
 */

public class Forget extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        Button send = (Button) findViewById(R.id.send);
        Button cancel = (Button) findViewById(R.id.cancel);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Forget.this, Login.class);
                startActivity(intent);
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean errorFlag = false;
                EditText username = (EditText) findViewById(R.id.username);

                //checks username
                if (username.getText().toString().isEmpty()) {
                    Toast.makeText(Forget.this, "Can't Send: Username still required", Toast.LENGTH_SHORT).show();
                    errorFlag = true;
                }
                if (!errorFlag) {
                    Boolean error = false;

                    Model model = Model.getInstance();

                    username.setError(null);

                    String uid = username.getText().toString();
                    User lookup = model.getUserByUsername(uid);

                    if (TextUtils.isEmpty(uid)) {
                        username.setError(getString(R.string.error_field_required));
                    } else if (!uid.equals(lookup.getUsername()) && !uid.equals("user")) {
                        username.setError(getString(R.string.error_invalid_username));
                        error = true;
                    }
                    if (uid.equals(lookup.getUsername()) && !error) {
                        Log.i("SendMailActivity", "Send Button Clicked.");

                        String fromEmail = "findrbot@gmail.com";
                        String fromPassword = "@qwerty01";
                        String toEmails = lookup.getEmail();
                        List<String> toEmailList = Arrays.asList(toEmails
                                .split("\\s*,\\s*"));
                        Log.i("SendMailActivity", "To List: " + toEmailList);
                        String emailSubject = "Findr Password";
                        String emailBody = "Password: " + lookup.getPassword();
                        new SendMailTask(Forget.this).execute(fromEmail,
                                fromPassword, toEmailList, emailSubject, emailBody);
                        Toast.makeText(Forget.this, "email sent", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Forget.this, Login.class);
                        startActivity(intent);
//                        try {
//
//
////                            GMailSender sender = new GMailSender("findrbot@gmail.com", "@qwerty01");
////                            sender.sendMail("Findr Password", ("Password: " + lookup.getPassword()),
////                                    "findrbot@gmail.com", lookup.getEmail());
//                            Toast.makeText(Forget.this, "email sent", Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(Forget.this, Login.class);
//                            startActivity(intent);
//                        } catch (Exception e) {
//                            Log.e("Send Email", e.getMessage(), e);
//                        }
                    } else {
                        Toast.makeText(Forget.this, "Username not found", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }
//    private void sendEmail(String email, String pass) {
//        Log.i("Send email", "");
//        String[] TO = {email};
//        try {
//            GMailSender sender = new GMailSender("findrbot@gmail.com", "@qwerty01");
//            sender.sendMail("Findr Password", ("Password: " + pass), "findrbot@gmail.com", email);
//            Toast.makeText(Forget.this, "email sent", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(Forget.this, Login.class);
//            startActivity(intent);
//        } catch (Exception e) {
//            Log.e("Send Email", e.getMessage(), e);
//        }

//        Intent emailIntent = new Intent(Intent.ACTION_SEND, Uri.parse("mail to: " + email));
//
//        emailIntent.setType("text/plain");
//        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Findr Password");
//        emailIntent.putExtra(Intent.EXTRA_TEXT, ("Password: " + pass));
//
//        startActivity(Intent.createChooser(emailIntent, email));
//        finish();
//        Log.i("Finished sending email", "");

//        try {
//            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
//            Log.i("Finished sending email", "");
//            Intent intent = new Intent(Forget.this, Login.class);
//            startActivity(intent);
//        } catch (android.content.ActivityNotFoundException ex) {
//            Toast.makeText(Forget.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
//        }
//    }


}
