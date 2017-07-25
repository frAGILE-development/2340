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

//Created by Bryce Watson on 6/29/17.


import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Written by Bryce Watson
 */
public class Search extends AppCompatActivity implements android.widget.SearchView.OnQueryTextListener {

    ListView list_found;
    private ListAdapter adapter;
    ArrayList<LostItem> lostList = new ArrayList<LostItem>();
    ArrayList<FoundItem> foundList = new ArrayList<FoundItem>();
    private final Model model = Model.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ListView list = (ListView) findViewById(R.id.list_view);

        @SuppressWarnings("unchecked") ArrayList<Item> totalList = model.getAllItems();

        //prints out the total list for debug purposes
        for(Item i: totalList) {
            System.out.print(i);
        }
        adapter = new ListAdapter(this, totalList);
        // Binds the Adapter to the ListView
        list.setAdapter(adapter);
        //list_found.setAdapter(adapter2);
        // Locate the EditText in listview_main.xml
        android.widget.SearchView editsearch = (android.widget.SearchView) findViewById(R.id.search);
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
     * @param query query
     * @return false
     */
    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    /**
     * On addition or subtraction of new characters in the query
     * @param newText characters being added to new text
     * @return false
     */
    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.filter(newText);
        return false;
    }


}
