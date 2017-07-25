package com.example.ananya.findr.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ananya.findr.R;

import model.FoundItem;
import model.Model;
import model.persistence.ManagementFacade;

/**
 * Created by Bryce on 6/29/2017.
 * Controller for adding a found item by adding to the model and the management facade
 */

public class AddFoundItem extends AppCompatActivity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfounditem);

        Button ok = (Button) findViewById(R.id.ok);
        Button cancel = (Button) findViewById(R.id.cancel);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddFoundItem.this, Application.class);
                startActivity(intent);
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean errorFlag = false;
                EditText name = (EditText) findViewById(R.id.name);
                EditText description = (EditText) findViewById(R.id.description);
                EditText address = (EditText) findViewById(R.id.address);

                if (name.getText().toString().isEmpty() || description.getText().toString().isEmpty() ||
                        address.getText().toString().isEmpty()) {
                    Toast.makeText(AddFoundItem.this, "Must input something into all text fields", Toast.LENGTH_SHORT).show();
                    errorFlag = true;
                }

                if (!errorFlag) {
                    Model model = Model.getInstance();
                    ManagementFacade mf = ManagementFacade.getInstance();
                    FoundItem item = new FoundItem(name.getText().toString(), description.getText().toString(),
                            address.getText().toString());
                    model.addFoundItem(item);
                    mf.addFoundItem(item.getName(), item.getDescription(), item.getAddress());
                    Intent intent = new Intent(AddFoundItem.this, Application.class);
                    startActivity(intent);

                }
            }
        });
        }
    }