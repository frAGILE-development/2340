package com.example.ananya.findr.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import FloatingActionButton.FloatingActionButton;
import Model.Model;
import Model.LostItem;
import FloatingActionButton.*;
import com.example.ananya.findr.R;

import java.util.List;

/**
 * Created by Ananya on 6/29/17.
 */

public class ViewLostItems<T extends Comparable<? super T>> extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(
                R.layout.activity_lostitemlist);

        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.list);

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data
        Model model = Model.getInstance();
        Object[] list = model.getLostItems().toArray();
        ArrayAdapter<Object> adapter = new ArrayAdapter<Object>(this,
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

        //menu holding the actions
        final FloatingActionsMenu menuMultipleActions = (FloatingActionsMenu) findViewById(R.id.multiple_actions);
        menuMultipleActions.addButton(searchButton);
        menuMultipleActions.addButton(lostItem);
        menuMultipleActions.addButton(foundItem);
    }
}
