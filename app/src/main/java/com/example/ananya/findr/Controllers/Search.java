package com.example.ananya.findr.Controllers;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.example.ananya.findr.R;

import java.util.ArrayList;
import Model.Item;
import Model.LostItem;
import Model.FoundItem;
import Model.Model;

/**
 * Created by bwatson35 on 6/29/17.
 */

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class Search extends AppCompatActivity implements android.widget.SearchView.OnQueryTextListener {

    // Declare Variables
    ListView list;
    ListView list_found;
    ListAdapter adapter;
    FoundAdapter adapter2;
    android.widget.SearchView editsearch;
    ArrayList<LostItem> lostList = new ArrayList<LostItem>();
    ArrayList<FoundItem> foundList = new ArrayList<FoundItem>();
    ArrayList<Item> totalList = new ArrayList<Item>();
    Model model = Model.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        list = (ListView) findViewById(R.id.list_view);

        totalList = model.getAllItems();

        //prints out the total list for debug purposes
        for(Item i: totalList) {
            System.out.print(i);
        }

        // Pass results to ListViewAdapter Class
//        adapter = new ListAdapter(this, totalList);
//        adapter2 = new FoundAdapter(this, foundList);
        adapter = new ListAdapter(this, totalList);
        // Binds the Adapter to the ListView
        list.setAdapter(adapter);
        //list_found.setAdapter(adapter2);
        // Locate the EditText in listview_main.xml
        editsearch = (android.widget.SearchView) findViewById(R.id.search);
        editsearch.setOnQueryTextListener(this);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
                String name = (String) adapter.getItemAtPosition(position);
                Model model = Model.getInstance();
                Item item = model.getItemByName(name);
                model.setCurrentItem(item);
                Intent intent = new Intent(Search.this, ItemDetails.class);
                //based on item add info to intent
                startActivity(intent);
            }
        });
    }



    /**
     * On text submit
     * @param query
     * @return
     */
    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    /**
     * On addition or subtraction of new characters in the query
     * @param newText
     * @return
     */
    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        adapter.filter(text);
        return false;
    }


}
