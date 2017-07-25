package com.example.ananya.findr.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import FloatingActionButton.*;
import model.Model;
import model.LostItem;
import com.example.ananya.findr.R;

/**
 * Created by Ananya on 6/29/17.
 * Controller for the list of lost items
 */

public class ViewLostItems<T extends Comparable<? super T>> extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(
                R.layout.activity_lostitemlist);

        // Get ListView object from xml
        ListView listView = (ListView) findViewById(R.id.list);

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data
        Model model = Model.getInstance();
        Object[] list = model.getLostItems().toArray();
        ArrayAdapter<Object> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, list);
        // Assign adapter to ListView
        listView.setAdapter(adapter);


        // ListView Item Click Listener
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
                LostItem item = (LostItem) adapter.getItemAtPosition(position);
                Model model = Model.getInstance();
                model.setCurrentItem(item);
                Intent intent = new Intent(ViewLostItems.this, ItemDetails.class);
                //based on item add info to intent
                startActivity(intent);
            }
        });

        //Floating action button set up
        //Lost item
        FloatingActionButton lostItem = new FloatingActionButton(getBaseContext());
        lostItem.setIcon(R.drawable.ic_action_lost_item);
        lostItem.setTitle("Add a lost item");
        lostItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewLostItems.this, AddLostItem.class);
                startActivity(intent);

            }
        });

        //Found item menu
        FloatingActionButton foundItem = new FloatingActionButton(getBaseContext());
        foundItem.setIcon(R.drawable.ic_action_found_item);
        foundItem.setTitle("Add a found item");
        foundItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewLostItems.this, AddFoundItem.class);
                startActivity(intent);

            }
        });

        //Search menu
        FloatingActionButton searchButton = new FloatingActionButton(getBaseContext());
        searchButton.setIcon(R.drawable.ic_action_search);
        //searchButton.setColorNormal(R.color.white);
        searchButton.setColorPressed(R.color.white_pressed);
        searchButton.setTitle("Search");
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewLostItems.this, Search.class);
                startActivity(intent);

            }
        });

        //Found Items menu
        FloatingActionButton lostItemsList = new FloatingActionButton(getBaseContext());
        lostItemsList.setIcon(R.drawable.ic_action_name_item_list);
        //foundItemsList.setColorPressed(R.color.white_pressed);
        lostItemsList.setTitle("Found Items List");
        lostItemsList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewLostItems.this, ViewFoundItems.class);
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
                Intent intent = new Intent(ViewLostItems.this, Application.class);
                startActivity(intent);

            }
        });

        //menu holding the actions
        final FloatingActionsMenu menuMultipleActions = (FloatingActionsMenu) findViewById(R.id.multiple_actions);
        menuMultipleActions.addButton(searchButton);
        menuMultipleActions.addButton(lostItem);
        menuMultipleActions.addButton(foundItem);
        menuMultipleActions.addButton(lostItemsList);
        menuMultipleActions.addButton(home);
    }
}
