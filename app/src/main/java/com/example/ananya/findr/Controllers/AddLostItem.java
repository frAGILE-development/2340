package com.example.ananya.findr.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ananya.findr.R;

import Model.Persistence.ManagementFacade;
import Model.Model;
import Model.LostItem;

/**
 * Created by Bryce on 6/29/2017.
 * Controller for adding a lost item by adding to the model and the management facade
 */

public class AddLostItem  extends AppCompatActivity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addlostitem);

        Button ok = (Button) findViewById(R.id.ok);
        Button cancel = (Button) findViewById(R.id.cancel);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddLostItem.this, Application.class);
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

                if (name.getText().toString().isEmpty()|| description.getText().toString().isEmpty() ||
                        address.getText().toString().isEmpty() ){
                    Toast.makeText(AddLostItem.this, "Must input something into all text fields", Toast.LENGTH_SHORT).show();
                    errorFlag = true;
                }

                if (!errorFlag) {
                    Model model = Model.getInstance();
                    ManagementFacade mf = ManagementFacade.getInstance();
                    LostItem item = new LostItem(name.getText().toString(), description.getText().toString(),
                            address.getText().toString());
                    item.setOwner(model.getCurrentUser());
                    model.addLostItem(item);
                    mf.addLostItem(item.getName(), item.getDescription(), item.getAddress());
                    Intent intent = new Intent(AddLostItem.this, Application.class);
                    startActivity(intent);

                }
            }
        });
        }
    }