package com.example.ananya.findr.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ananya.findr.R;

import model.maps.ModelFacade;
import model.FoundItem;
import model.LostItem;
import model.Model;
import model.persistence.ManagementFacade;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by Bryce Watson on 7/25/2017.
 * A window that allows you to create an item from a clicked on marker in google maps
 */

public class AddItemFromMap extends AppCompatActivity {

    private Marker mSelectedMarker;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Model model = Model.getInstance();

        setContentView(R.layout.add_item_from_map);
        Button add = (Button) findViewById(R.id.button_add_item);
        Button cancel = (Button) findViewById(R.id.button_cancel);
        TextView cord = (TextView) findViewById(R.id.textView_coordinates);
        cord.setText(model.getCurrentLocation().toString());

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddItemFromMap.this, MapsActivity.class);
                startActivity(intent);
            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean errorFlag = false;
                EditText name = (EditText) findViewById(R.id.editText_name);
                EditText description = (EditText) findViewById(R.id.editText_description);
                EditText address = (EditText) findViewById(R.id.editText_address);
                EditText type = (EditText) findViewById(R.id.editText_type);

                if (name.getText().toString().isEmpty() || description.getText().toString().isEmpty() ||
                        type.getText().toString().isEmpty() || !(type.getText().toString()).equalsIgnoreCase("lost") ||
                        !(type.getText().toString().equalsIgnoreCase("found"))){
                    Toast.makeText(AddItemFromMap.this, "Must input something into name, description, and type. Type" +
                            " must be lost or found", Toast.LENGTH_LONG).show();
                    errorFlag = true;
                }

                if (!errorFlag) {
                    Model model = Model.getInstance();
                    ManagementFacade mf = ManagementFacade.getInstance();
                    if (type.getText().toString().equalsIgnoreCase("found")) {
                        FoundItem item = new FoundItem(name.getText().toString(), description.getText().toString(),
                                address.getText().toString());
                        item.setLocation(model.getCurrentLocation());
                        model.addFoundItem(item);
                        model.setCurrentItem(item);
                        mf.addFoundItem(item.getName(), item.getDescription(), item.getAddress());
                        Toast.makeText(AddItemFromMap.this, "Found Item added", Toast.LENGTH_SHORT).show();
                        ModelFacade modelfacade = ModelFacade.getInstance();
                        modelfacade.addReport(item.getName(), item.getDescription(), model.getCurrentLocation());
                        Intent intent = new Intent(AddItemFromMap.this, MapsActivity.class);
                        startActivity(intent);

                    } else if (type.getText().toString().equalsIgnoreCase("lost")) {
                        LostItem item = new LostItem(name.getText().toString(), description.getText().toString(),
                                address.getText().toString());
                        item.setLocation(model.getCurrentLocation());
                        model.addLostItem(item);
                        model.setCurrentItem(item);
                        mf.addLostItem(item.getName(), item.getDescription(), item.getAddress());
                        Toast.makeText(AddItemFromMap.this, "Lost Item added", Toast.LENGTH_SHORT).show();
                        ModelFacade modelfacade = ModelFacade.getInstance();
                        modelfacade.addReport(item.getName(), item.getDescription(), model.getCurrentLocation());
                        Intent intent = new Intent(AddItemFromMap.this, MapsActivity.class);
                        startActivity(intent);

                    }
                }
            }
        });





    }
}
