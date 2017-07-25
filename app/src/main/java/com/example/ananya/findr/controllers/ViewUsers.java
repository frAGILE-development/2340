package com.example.ananya.findr.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ananya.findr.R;

import FloatingActionButton.*;
import model.*;

/**
 * Created by Bryce on 6/29/17.
 * Controller for the list of users
 */

public class ViewUsers<T extends Comparable<? super T>> extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(
                R.layout.activity_founditemlist);

        // Get ListView object from xml
        ListView listView = (ListView) findViewById(R.id.list);

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        Model model = Model.getInstance();
        Object[] list = model.getUsers().toArray();
        ArrayAdapter<Object> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, list);


        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
                User user = (User) adapter.getItemAtPosition(position);
                Model model = Model.getInstance();
                model.setCurrentUser(user);
                Intent intent = new Intent(ViewUsers.this, UserDetails.class);
                //based on item add info to intent
                startActivity(intent);
            }
        });
        //////////////////////////////////////////////////////////////////////////////////
        //Floating action button menu set up
        /////////////////////////////////////////////////////////////////////////////////
        FloatingActionButton register = new FloatingActionButton(getBaseContext());
        register.setIcon(R.drawable.ic_register);
        register.setTitle("Add a User");
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewUsers.this, Register.class);
                startActivity(intent);

            }
        });

        FloatingActionButton searchButton = new FloatingActionButton(getBaseContext());
        searchButton.setIcon(R.drawable.ic_action_search);
        //searchButton.setColorNormal(R.color.white);
        searchButton.setColorPressed(R.color.white_pressed);
        searchButton.setTitle("Search");
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewUsers.this, Search.class);
                startActivity(intent);

            }
        });

        //Home Items menu
        FloatingActionButton home = new FloatingActionButton(getBaseContext());
        home.setIcon(R.drawable.ic_home);
//        searchButton.setColorNormal(R.color.white);
//        foundItemsList.setColorPressed(R.color.white_pressed);
        home.setTitle("Home");
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewUsers.this, Application.class);
                startActivity(intent);

            }
        });

        //menu holding the actions
        final FloatingActionsMenu menuMultipleActions = (FloatingActionsMenu) findViewById(R.id.multiple_actions);
        menuMultipleActions.addButton(searchButton);
        menuMultipleActions.addButton(register);
        menuMultipleActions.addButton(home);

//        // ListView Item Click Listener
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//
//                // ListView Clicked item index
//                int itemPosition     = position;
//
//                // ListView Clicked item value
//                String  itemValue    = (String) listView.getItemAtPosition(position);
//
//                // Show Alert
//                Toast.makeText(getApplicationContext(),
//                        "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
//                        .show();
//
//            }
//
//        });
    }
}
