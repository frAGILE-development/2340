package com.example.ananya.findr.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ananya.findr.R;

import Model.Model;
import Model.FoundItem;
/**
 * Created by Bryce on 6/29/17.
 */

public class ViewFoundItems<T extends Comparable<? super T>> extends AppCompatActivity {
    ListView listView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(
                R.layout.activity_founditemlist);

        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.list);

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        Model model = Model.getInstance();
        Object[] list = model.getFoundItems().toArray();
        ArrayAdapter<Object> adapter = new ArrayAdapter<Object>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, list);


        // Assign adapter to ListView
        listView.setAdapter(adapter);

//        // ListView Item Click Listener
        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
                FoundItem item = (FoundItem) adapter.getItemAtPosition(position);
                Model model = Model.getInstance();
                model.setCurrentItem(item);
                Intent intent = new Intent(ViewFoundItems.this, ItemDetails.class);
                //based on item add info to intent
                startActivity(intent);
            }
        });
    }
}
